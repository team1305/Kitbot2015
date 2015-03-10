package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.TripleTalon;
import org.usfirst.frc.team1305.robot.commands.drivetrain.Drive;
import org.usfirst.frc.team1305.robot.commands.drivetrain.PacmanDrive;
import org.usfirst.frc.team1305.robot.commands.drivetrain.SmoothDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CANJaguar.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
	CANTalon ml1 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_L1);
	CANTalon ml2 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_L2);
	CANTalon mr1 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_R1);
	CANTalon mr2 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_R2);
	
	
	private Timer robotSetTimer = new Timer();
	private static final double ROBOT_SET_DURATION = 3;
	private static final double ROBOT_DANCE_DURATION = 1;
    private int currentState = 0;
	
    
    
	private RobotDrive drive = new RobotDrive(ml1, ml2, mr1, mr2);
	
	//true if arm-perspective, false if stacker-perspective
	private boolean armPerspective = false;
	public boolean isLowGear = false;
	


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new SmoothDrive());
//    	setDefaultCommand(new PacmanDrive());
    }
    
    //function for arcadedrive
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
    
    //function for tankdrive
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
     * Set whether the driving perspective should be of the stacker or the arm
     * @param value true if the perspective is of arm, false if of stacker.
     */
    public void setArmPerspective(boolean value){
    	armPerspective = value;
    }
    /**
     * Get the current driving perspective
     * @return true if arm perspecive, false otherwise.
     */
    public boolean getArmPerspective(){
    	return armPerspective;
    }
    
    public void toggleGear(){
    	this.isLowGear = ! this.isLowGear;
    }
    public void setLowGear(boolean value){
    	this.isLowGear = value;
    }
    public boolean getLowGear(){
    	return this.isLowGear;
    }
    
    public boolean autonomousMobility(){
    	switch (currentState){
        case 0:
        	ml1.changeControlMode(CANTalon.ControlMode.Voltage);
        	ml2.changeControlMode(CANTalon.ControlMode.Voltage);
        	mr1.changeControlMode(CANTalon.ControlMode.Voltage);
        	mr2.changeControlMode(CANTalon.ControlMode.Voltage);
            robotSetTimer.start();
            
            currentState++;
            break;
        case 1:
            if (robotSetTimer.get()>=ROBOT_SET_DURATION)
            {
                
                currentState++;
            }
            drive.tankDrive(-8,-8);
            break;
        case 2:
            drive.tankDrive(0,0);
            currentState = 0;
            robotSetTimer.stop();
            robotSetTimer.reset();
            ml1.changeControlMode(CANTalon.ControlMode.PercentVbus);
        	ml2.changeControlMode(CANTalon.ControlMode.PercentVbus);
        	mr1.changeControlMode(CANTalon.ControlMode.PercentVbus);
        	mr2.changeControlMode(CANTalon.ControlMode.PercentVbus);
            return true; 
    }
    return false;
    }
    
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

