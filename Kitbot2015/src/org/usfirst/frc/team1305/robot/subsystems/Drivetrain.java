package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.drivetrain.SmoothDrive;

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

	// Timer handles all drive auto movement.
	private Timer robotSetTimer = new Timer();
	private static final double ROBOT_SET_DURATION = 1;
	private static final double ROBOT_DANCE_DURATION = 1;
	private static final double ROBOT_DELAY = 0.25;
    private int currentState = 0;



	private RobotDrive drive = new RobotDrive(ml1, ml2, mr1, mr2);

	// true if arm-perspective, false if stacker-perspective
	private boolean armPerspective = false;
	public boolean isLowGear = false;



    @Override
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new SmoothDrive());
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
     * Handles forward movement over bump into auto zone in auto.
     *
     * Sets speed using tankDrive method, uses constant ROBOT_SET_DURATION
     * to determine length of drive.  robotSetTimer causes method to proceed.
     * @return Returns true when finished, false while running.
     */
    public boolean autonomousMobility(double duration, double leftSpeed, double rightSpeed){
    	switch (currentState){
        case 0:
            robotSetTimer.start();

            currentState++;
            break;
        case 1:
            if (robotSetTimer.get()>= duration)
            {

                currentState++;
            }
            drive.tankDrive(leftSpeed,rightSpeed);
            break;
        case 2:
            drive.tankDrive(0,0);
            currentState = 0;
            robotSetTimer.stop();
            robotSetTimer.reset();
            break;
    }
    if(currentState == 2){
  		return true;
   	}else{
   		return false;
   	}    
}

    /**
     * Drives forward until sneezeguard sensor is triggered.
     * @param leftSpeed Controls speed of left motors
     * @param rightSpeed Controls speed of right motors
     * @return Returns true when finished
     */
    public boolean autonomousTote(double leftSpeed, double rightSpeed){
    	switch (currentState){
        case 0:
            robotSetTimer.start();

            currentState++;
            break;
        case 1:
            if (robotSetTimer.get()>= 2)
            {

                currentState++;
            }else if(Robot.forks.trigger.get() == false){
	    		currentState++;
            }else{
            	drive.tankDrive(leftSpeed, rightSpeed);
            }
            break;
        case 2:
            drive.tankDrive(0,0);
            currentState = 0;
            robotSetTimer.stop();
            robotSetTimer.reset();
            return true;
    } 
    	return false;
    }
    
    /**
     * Drives forward until claw sensor is triggered.
     * @param leftSpeed Controls speed of left motors
     * @param rightSpeed Controls speed of right motors
     * @return Returns true when finished
     */
    public boolean autonomousBin(double leftSpeed, double rightSpeed){
    	while(Robot.claw.trigger.get() == true){
    		drive.tankDrive(leftSpeed, rightSpeed);
    	}
    	if(Robot.claw.trigger.get() == false){
    		return true;
    	}else{
    		return false;
    	}
    }
    

    /**
     * Mainly just to show off in autonomous, honestly.
     * @return False while running, true when complete.
     */
    public boolean autonomousDance(){
    	switch (currentState){
        case 0:
            robotSetTimer.start();

            currentState++;
            break;
        case 1:
            if (robotSetTimer.get()>=ROBOT_DANCE_DURATION)
            {

                currentState++;
                robotSetTimer.reset();
                robotSetTimer.start();
            }
            drive.tankDrive(-0.5,0.5);
            break;
        case 2:
        	if (robotSetTimer.get()>=ROBOT_DANCE_DURATION)
            {
                currentState++;
                robotSetTimer.reset();
                robotSetTimer.start();
            }
            drive.tankDrive(0.5,-0.5);
        	break;
        case 3:
        	if (robotSetTimer.get()>=ROBOT_DANCE_DURATION)
            {
                currentState++;
                robotSetTimer.reset();
                robotSetTimer.start();
            }
            drive.tankDrive(-0.3,0.3);
        	break;
        case 4:
            drive.tankDrive(0,0);
            currentState = 0;
            robotSetTimer.stop();
            robotSetTimer.reset();
            return true;
    }
    return false;
    }

}

