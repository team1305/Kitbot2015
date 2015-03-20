package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveSmooth;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Handles all base movement of the robot.
 */
public class Drivetrain extends Subsystem {

	CANTalon ml1 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_L1);
	CANTalon ml2 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_L2);
	CANTalon mr1 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_R1);
	CANTalon mr2 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_R2);

	private RobotDrive drive = new RobotDrive(ml1, ml2, mr1, mr2);

	// true if arm-perspective, false if stacker-perspective
	private boolean armPerspective = false;
	public boolean isLowGear = false;



    @Override
	public void initDefaultCommand() {
    	setDefaultCommand(new DriveSmooth());
//    	setDefaultCommand(new PacmanDrive());
    }

    /**
     * Handles manual driving when smooth drive is not active.
     * @param moveValue Y-value of joystick passed to method.
     * @param rotateValue X-value of joystick passed to method.
     */
    public void arcadeDrive(double moveValue, double rotateValue){
    	//check for low gear
    	if(isLowGear){
    		moveValue /= 2.0;
    		rotateValue /= 2.0;
    	}
    	//check perspective and apply
    	if(armPerspective){
    		drive.arcadeDrive(-moveValue/1.7, rotateValue/2);
    	}
    	else{
    		drive.arcadeDrive(moveValue/1.7, rotateValue/2);
    	}
    }

    /**
     * Handles all movement on the robot base.
     *
     * Takes commands from both smooth driving and normal driving,
     * if overridden manually.  leftValue and rightValue are passed from
     * the command Drive.
     * @param leftValue Handles left base movement of robot.
     * @param rightValue Handles right base movement of robot.
     */
    public void tankDrive(double leftValue, double rightValue){
    	if(isLowGear){
    		leftValue /= 1.6;
    		rightValue /= 1.6;
    	}
    	if(armPerspective){
        	drive.tankDrive(-rightValue/1.3, -leftValue/1.3);
    	}
    	else{
        	drive.tankDrive(leftValue/1.3, rightValue/1.3);
    	}
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
    }



}

