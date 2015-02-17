package org.usfirst.frc.team1305.robot.commands.drivetrain;

import org.usfirst.frc.team1305.robot.Constants;
import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.RobotMap;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;

/**
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
 * @author Paul Belanger
 */
public class SmoothDrive extends Command {
	
	private final double MAX_RATE = Constants.DRIVESMOOTHING_MAX_RATE;
	//values that will be assigned to the subsystem
	private double leftValue = 0;
	private double rightValue = 0;
	//variables used for actual drive smoothing.
	private double l0 = 0;
	private double r0 = 0;
	private double l1 = 0;
	private double r1 = 0;
	private long t0 = 0;
	private long t1 = 0;
	//intermediate variables
	private double moveValue = 0;
	private double rotateValue = 0;
	
    public SmoothDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.tankDrive(0.0, 0.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//first get the current time from the FPGA
    	t1 = Utility.getFPGATime();
    	//get move and rotate values from the drive stick
    	moveValue = -Robot.oi.getDriveAxis(RobotMap.AXIS_DRIVETRAIN_MOVE);
    	rotateValue = Robot.oi.getDriveAxis(RobotMap.AXIS_DRIVETRAIN_ROTATE);
    	
    	//Decode the move and rotate values into left- and right- tank drive 
    	//values. This if-else block is taken from WPILib RobotDrive.java
        if (moveValue > 0.0) {
            if (rotateValue > 0.0) {
                l1 = moveValue - rotateValue;
                r1 = Math.max(moveValue, rotateValue);
            } else {
                l1 = Math.max(moveValue, -rotateValue);
                r1 = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                l1 = -Math.max(-moveValue, rotateValue);
                r1 = moveValue + rotateValue;
            } else {
                l1 = moveValue - rotateValue;
                r1 = -Math.max(-moveValue, -rotateValue);
            }
        }
        
        //compute dt and delta-L, deltaR
        double dt = ((double) (t1 - t0)) / 10e6; //in seconds
        double dl = l1 - l0; //in percent
        double dr = r1 - r0; //in percent
        //now compute the slopes, units percent per second
        //if you've taken physics, this could also be considered
        //"stick velocity", and is the slope in the "stick-position vs time" 
        //graph.
        double dldt = dl / dt;
        double drdt = dr / dt;
        
        //now for each, if slope exceeds maximum allowed, then we clip to the 
        //max. Otherwise assign as normal.
        //left side
        if(Math.abs(dldt) <= MAX_RATE){
        	leftValue = l1;
        }
        else{
        	//account for forewards/backwards
        	if(dl >= 0.0) leftValue = l0 + dt * MAX_RATE;
        	else leftValue = l0 - dt * MAX_RATE;
        }
        //and right side
        if(Math.abs(drdt) <= MAX_RATE){
        	rightValue = r1;
        }
        else{
        	if(dr >= 0.0) rightValue = r0 + dt * MAX_RATE;
        	else rightValue = r0 - dt * MAX_RATE;
        }
        
        //now we set up the variables for the next run.
        t0 = t1;
        l0 = leftValue;
        r0 = rightValue;
        //and apply the values
        Robot.drivetrain.tankDrive(leftValue, rightValue);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	t0 = 0;
    	t1 = 0;
    	l0 = 0;
    	l1 = 0;
    	r0 = 0;
    	r1 = 1;
    	Robot.drivetrain.tankDrive(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	t0 = 0;
    	t1 = 0;
    	l0 = 0;
    	l1 = 0;
    	r0 = 0;
    	r1 = 1;
    	Robot.drivetrain.tankDrive(0.0, 0.0);
    }
    
    
}
//TODO: Possible jerk if the stick is full-foreward when the command initializes
//Can fix by skipping an execution-step if t0 == 0