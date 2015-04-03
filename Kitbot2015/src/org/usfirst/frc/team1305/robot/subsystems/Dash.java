package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.commands.dash.DashUpdate;
import org.usfirst.frc.team1305.robot.subsystems.Arm.ArmMode;
import org.usfirst.frc.team1305.robot.subsystems.Arm.Joint;
import org.usfirst.frc.team1305.robot.subsystems.Arm.Preset;

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
	double  drive_tiltAngle;
	
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
    
    private void update_arm(){
    	arm_preset 				   = Robot.arm.getPreset();
    	arm_mode				   = Robot.arm.getMode();
    	arm_calculatedWristAngle   = Robot.arm.getAngle(Joint.calculatedWrist);
    	arm_calculatedWristReading = Robot.arm.getRaw(Joint.calculatedWrist);
    	arm_elbowAngle             = Robot.arm.getAngle(Joint.elbow);
    	arm_elbowReading           = Robot.arm.getRaw(Joint.elbow);
    	arm_shoulderAngle          = Robot.arm.getAngle(Joint.shoulder);
    	arm_shoulderReading        = Robot.arm.getRaw(Joint.shoulder);
    	arm_wristAngle			   = Robot.arm.getAngle(Joint.wrist);
    	arm_wristReading		   = Robot.arm.getRaw(Joint.wrist);
    	
    	//arm preset
    	if(arm_preset != null){
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
    }
    
    private void update_claw(){
    	claw_isOpen = !Robot.claw.getClosed();
    	claw_limitState = Robot.claw.getTrigger();
    	
    	if(claw_isOpen) SmartDashboard.putString("Claw State", "Open");
    	else            SmartDashboard.putString("Claw State", "Closed");
    	
    	if(claw_limitState) SmartDashboard.putString("Claw Trigger", "Triggered");
    	else				SmartDashboard.putString("Claw Trigger", "Not triggered");
    }
    
    private void update_drivetrain(){
    	drive_isarmPerspective = Robot.drivetrain.getArmPerspective();
    	drive_isLowGear = Robot.drivetrain.getLowGear();
    	drive_tiltAngle = Robot.drivetrain.getTilt();
    	
    	if(drive_isarmPerspective) SmartDashboard.putString("Drive Perspective", "Arm");
    	else					   SmartDashboard.putString("Drive Perspective", "Stacker");
    	
    	if(drive_isLowGear) SmartDashboard.putString("Drive gear", "Low Gear");
    	else				SmartDashboard.putString("Drive gear", "High gear");
    	
    	SmartDashboard.putNumber("Drive tilt", drive_tiltAngle);

    }
    
    
    
    private void update_gyroscope(){
    	gyro_rawAngle = Robot.gyroscope.getRawAngle();
    	gyro_constrainedAngle = Robot.gyroscope.getAngle();
    	
    	SmartDashboard.putNumber("Gyroscope Raw Angle", gyro_rawAngle);
    	SmartDashboard.putNumber("Gyroscope Angle", gyro_constrainedAngle);

    }
    public void update(){
    	update_arm();
    	update_claw();
    	update_drivetrain();
    	update_gyroscope();
    }
    
}

