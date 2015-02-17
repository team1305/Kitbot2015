package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.TripleTalon;
import org.usfirst.frc.team1305.robot.commands.drivetrain.Drive;
import org.usfirst.frc.team1305.robot.commands.drivetrain.PacmanDrive;
import org.usfirst.frc.team1305.robot.commands.drivetrain.SmoothDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
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

	private RobotDrive drive = new RobotDrive(ml1, ml2, mr1, mr2);
	
	//true if arm-perspective, false if stacker-perspective
	private boolean armPerspective = false;
	private boolean isLowGear = false;
	


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
    		leftValue /= 2.0;
    		rightValue /= 2.0;
    	}
    	if(armPerspective){
        	drive.tankDrive(-rightValue/1.4, -leftValue/1.4);
    	}
    	else{
        	drive.tankDrive(leftValue/1.4, rightValue/1.4);
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
    

    		
    
}

