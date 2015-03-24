package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.AxisSmoother;
import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveSmooth;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;

/**
 * Handles all base movement of the robot.
 */
public class Drivetrain extends Subsystem {
	private final double SMOOTHING_MAX_RATE = 33.3;
	
	private final double LOWGEAR_MULTIPLIER = 0.6;
	private final double DRIVE_MULTIPLIER   = 0.75;

	CANTalon ml1 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_L1);
	CANTalon ml2 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_L2);
	CANTalon mr1 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_R1);
	CANTalon mr2 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_R2);
	
	BuiltInAccelerometer accel = new BuiltInAccelerometer();

	private RobotDrive drive = new RobotDrive(ml1, ml2, mr1, mr2);
	
	private AxisSmoother leftSmoother = new AxisSmoother(SMOOTHING_MAX_RATE);
	private AxisSmoother rightSmoother = new AxisSmoother(SMOOTHING_MAX_RATE);

	// true if arm-perspective, false if stacker-perspective
	private boolean armPerspective = false;
	public boolean isLowGear = false;

	public Drivetrain() {
		accel.setRange(Range.k2G);
	}


    @Override
	public void initDefaultCommand() {
    	setDefaultCommand(new DriveSmooth());
//    	setDefaultCommand(new PacmanDrive());
    }

    /**
     * Handles manual driving from regular drive commands.
     * @param moveValue Y-value of joystick passed to method.
     * @param rotateValue X-value of joystick passed to method.
     * @param smoothing whether or not to use smoothing on the value.
     */
    public void arcadeDrive(double moveValue, double rotateValue, boolean smoothing){
    	double left;
    	double right;
    	//Decode the move and rotate values into left- and right- tank drive
    	//values. This if-else block is taken from WPILib RobotDrive.java
        if (moveValue > 0.0) {
            if (rotateValue > 0.0) {
                left = moveValue - rotateValue;
                right = Math.max(moveValue, rotateValue);
            } else {
                left = Math.max(moveValue, -rotateValue);
                right = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                left = -Math.max(-moveValue, rotateValue);
                right = moveValue + rotateValue;
            } else {
                left = moveValue - rotateValue;
                right = -Math.max(-moveValue, -rotateValue);
            }
        }
        //now pass on to tankDrive
        tankDrive(left, right, smoothing);
    }

    /**
     * Handles all regular movement from the robot commands
     * @param leftValue Handles left base movement of robot.
     * @param rightValue Handles right base movement of robot.
     * @param smoothing whether or not to use smooting on the value.
     */
    public void tankDrive(double leftValue, double rightValue, boolean smoothing){
    	//do smoothing calculations
    	if(smoothing){
    		leftValue = leftSmoother.process(leftValue);
    		rightValue = rightSmoother.process(rightValue);
    	}
    	//constant multiple
    	leftValue *= DRIVE_MULTIPLIER;
    	rightValue *= DRIVE_MULTIPLIER;
    	//computing low gear
    	if(isLowGear){
    		leftValue /= LOWGEAR_MULTIPLIER;
    		rightValue /= LOWGEAR_MULTIPLIER;
    	}
    	if(armPerspective){
        	drive.tankDrive(-rightValue, -leftValue);
    	}
    	else{
        	drive.tankDrive(leftValue, rightValue);
    	}
    }
    
    /**
     * Drive the robot without any sort of special processing. 
     * @param leftValue
     * @param rightValue
     */
    public void tankDrive_raw(double leftValue, double rightValue){
    	drive.tankDrive(leftValue, rightValue);
    }
    /**
     * Set whether the driving perspective should be of the stacker or the arm.
     * Values are passed from SetArmPerspective and SetStackerPerspective.
     * @param value true if the perspective is of arm, false if of stacker.
     */
    public void setArmPerspective(boolean value){
    	armPerspective = value;
    }
    /**
     * Gets the current driving perspective.
     * @return true if arm perspective, false if stacker perspective.
     */
    public boolean getArmPerspective(){
    	return armPerspective;
    }

    /**
     * Toggles digital low gear on/off.
     * Triggered through ToggleGear function,
     * values are held in tankDrive method.
     */
    public void toggleGear(){
    	this.isLowGear = ! this.isLowGear;
    }

    /**
     * Sets digital low gear on/off explicitly.
     * @param value true if low gear, false if high gear.
     */
    public void setLowGear(boolean value){
    	this.isLowGear = value;
    }

    /**
     * Queries to determine whether low gear is enabled.
     * @return True if low gear, false if high gear.
     */
    public boolean getLowGear(){
    	return this.isLowGear;
    }
    
    /**
     * stop all robot movement
     */
    public void stop(){
    	drive.tankDrive(0.0, 0.0);
    	leftSmoother.reset();
    	rightSmoother.reset();
    }
    
    /**
     * Compute the tilt of the robot, in the forward-backwards orientation.
     * 
     * If the robot is tilting so that the arm is down, the returned angle 
     * @return The tilt of the robot, in degrees from the vertical.
     */
    public double getTilt(){
    	// z is the unit vector pointing down the vertical axis of the robot
    	// z = [0, 1]
    	// u is the vector pointing down the true "down" direction
    	// u = [accel.getY, accel.getZ]
    	// the dot product z.u is the value u.getZ
    	double ZdotU = accel.getZ();
    	// magnitude of Z is 1, 
    	// compute the magnitude of U
    	double u1 = accel.getY();
    	double u2 = accel.getZ();
    	double magU = Math.sqrt(u1*u1 + u2*u2);
    	// now compute theta
    	double theta = Math.acos(ZdotU / (magU * 1)) * 180.0 / Math.PI;
    	// now if the y direction is positive, report a positive angle, otherwise negative
    	return Math.signum(u1) * theta;
    }



}

