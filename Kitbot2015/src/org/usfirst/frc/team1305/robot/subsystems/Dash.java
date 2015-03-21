package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.commands.dash.DashUpdate;
import org.usfirst.frc.team1305.robot.subsystems.NewArm.ArmMode;
import org.usfirst.frc.team1305.robot.subsystems.NewArm.Preset;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem which collects all important data and reports it to
 * the SmartDashboard. Polls public interfaces and calls other commands in
 * order to find required data.
 */
public class Dash extends Subsystem {
	
	//=========================================================================
	//=========== Subsystem Variables =========================================
	//=========================================================================
	
	//**NewArm**
	Preset arm_preset;
	ArmMode arm_mode;
	
	double arm_shoulderAngle;
	double arm_shoulderReading;
	double arm_elbowAngle;
	double arm_elbowReading;
	double arm_wristAngle;
	double arm_wristReading;
	double arm_calculatedWristAngle;
	double arm_calculatedWristReading;
	
	//**Claw**
	boolean claw_isOpen;
	boolean claw_limitState;
	
	//**Drivetrain**
	boolean drive_isarmPerspective;
	boolean drive_isLowGear;
	
	//**Elevator**
	
	
	//**Forks**
	boolean forks_isOpen;
	boolean forks_LimitState;
	boolean forks_isDeployed;
	
	//**Gyroscope**
	double gyro_rawAngle;
	double gyro_constrainedAngle;

    public void initDefaultCommand() {
        setDefaultCommand(new DashUpdate());
    }
    
    public void update(){
    	//**NewArm**
    	//arm preset
    	if(arm_preset == null){
    		SmartDashboard.putString("Arm Preset", arm_preset.name);
    	}
    	else{
    		SmartDashboard.putString("Arm Preset",  "Null");
    	}
    	
    	//arm mode
    	String armMode = "Null";
    	switch(arm_mode){
		case automaticWrist:
			armMode = "Automatic Wrist";
			break;
		case manualWrist:
			armMode = "Manual Wrist";
			break;
		case preset:
			armMode = "Preset Mode";
			break;
    	}
    	SmartDashboard.putString("Arm Mode", armMode);
    	
    	//numerical values
    	SmartDashboard.putNumber("Arm Shoulder angle", arm_shoulderAngle);
    	SmartDashboard.putNumber("Arm Shoulder Reading", arm_shoulderReading);
    	
    	SmartDashboard.putNumber("Arm Elbow angle", arm_elbowAngle);
    	SmartDashboard.putNumber("Arm Elbow Reading", arm_elbowReading);
    	
    	SmartDashboard.putNumber("Arm Wrist angle", arm_wristAngle);
    	SmartDashboard.putNumber("Arm Wrist Reading", arm_wristReading);
    	
    	SmartDashboard.putNumber("Arm Calculated Wrist Angle", arm_calculatedWristAngle);
    	SmartDashboard.putNumber("Arm Calculated Wrist Reading", arm_calculatedWristReading);
    	
    	
    	//**claw**
    	if(claw_isOpen) SmartDashboard.putString("Claw State", "Open");
    	else 			SmartDashboard.putString("Claw State", "Closed");
    	
    	if(claw_limitState) SmartDashboard.putString("Claw trigger", "Triggered");
    	else 				SmartDashboard.putString("Claw Trigger", "Not Triggered");
    	
    	
    	//**Drivetrain**
    	if(drive_isarmPerspective) SmartDashboard.putString("Drive Perspective", "Arm");
    	else					   SmartDashboard.putString("Drive Perspective", "Stacker");
    	
    	if(drive_isLowGear) SmartDashboard.putString("Drive gear", "Low Gear");
    	else				SmartDashboard.putString("Drive gear", "High gear");
    	
    }
    
}

