package org.usfirst.frc.team1305.robot;

import edu.wpi.first.wpilibj.Utility;

/**
 * Class which implements the smooth driving algorithm. 
 * It is used in Drivetrain and possibly other areas.
 * 
 * SmoothDrive - Drive with smoothing by attempting to clip maximum
 * stick-velocity to some upper bound.
 *
 * Maximum rate is defined in Constants.DRIVESMOOTHING_MAX_RATE
 *
 * For those of you studying Calculus, this borrows concepts from first-order
 * differentials
 *
 * Algorithm is as follows:
 * 1. Take the current stick position and compare it to the previous stick
 * 	position.
 * 2. By taking the difference in time between these two measurements into
 * 	account, we can find the slope of the stick position with respect to time,
 * 	which, as you have learned in Grade 11 physics, is the velocity of the
 * 	stick.
 * 3. Compare this velocity to some previously-defined max stick-velocity.
 * 4a. If we are under the max stick velocity, pass the new stick position
 * 	values along normally.
 * 4b. If we are exceeding the max stick velocity, then change the new stick
 * 	position to be the maximum that would be possible under the maximum stick
 * 	velocity. This part applies concepts of first-order differential equations,
 * 	where ds/dt is replaced with our maximum stick-velocity
 * 5. The corrected stick values are passed along to the motor controllers and
 * 	remembered for the next iteration of the loop.
 *
 * @author Paul Belanger & Mac Willis
 */
public class AxisSmoother {

	private static double max_rate = 33.3; // units %/second
	//values that will be assigned to the subsystem
	private double outval = 0;
	//variables used for actual drive smoothing.
	private double p0 = 0;
	private double p1 = 0;
	private long t0 = 0;
	private long t1 = 0;
	/**
	 * Axis smoothing with the specified maximum rate 
	 * @param max_rate The maximum dx\p/dt
	 */
	public AxisSmoother(double max_rate){
		this.max_rate = max_rate;
		t0 = Utility.getFPGATime();
	}
	
	/**
	 * Calculate an output value given the specified input value.
	 * @param inValue value to add to the calculation
	 * @return comuted constrained value.
	 */
	public double process(double inValue){
		//first get the current time from the FPGA
    	t1 = Utility.getFPGATime();

        //compute dt and dp
        double dt = (t1 - t0) / 10e6; //in seconds
        double dp = p1 - p0; //in percent
        //now compute the slopes, units percent per second
        //if you've taken physics, this could also be considered
        //"stick velocity", and is the slope in the "stick-position vs time"
        //graph.
        double dpdt = dp / dt;

        //now, if slope exceeds maximum allowed, then we clip to the
        //max. Otherwise assign as normal.
        if(Math.abs(dpdt) <= max_rate){
        	outval = p1;
        }
        else{
        	//account for forewards/backwards
        	if(dp >= 0.0) outval = p0 + dt * max_rate;
        	else outval = p0 - dt * max_rate;
        }

        //now we set up the variables for the next run.
        t0 = t1;
        p0 = outval;
        //and apply the values
        return outval;
	}
	
	/**
	 * resets the smoother, placing all values back to zero.
	 */
	public void reset(){
		p0 = 0;
		p1 = 0;
		t0 = 0;
		t1 = 0;
	}
	
	
}
