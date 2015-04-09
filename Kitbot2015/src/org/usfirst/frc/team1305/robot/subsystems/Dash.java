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

    public void initDefaultCommand() {
        setDefaultCommand(new DashUpdate());
    }
    
    private void update_arm(){
    	Preset arm_preset 				   = Robot.arm.getPreset();
    	ArmMode arm_mode				   = Robot.arm.getMode();
    	double arm_calculatedWristAngle   = Robot.arm.getAngle(Joint.calculatedWrist);
    	double arm_calculatedWristReading = Robot.arm.getRaw(Joint.calculatedWrist);
    	double arm_elbowAngle             = Robot.arm.getAngle(Joint.elbow);
    	double arm_elbowReading           = Robot.arm.getRaw(Joint.elbow);
    	double arm_shoulderAngle          = Robot.arm.getAngle(Joint.shoulder);
    	double arm_shoulderReading        = Robot.arm.getRaw(Joint.shoulder);
    	double arm_wristAngle			   = Robot.arm.getAngle(Joint.wrist);
    	double arm_wristReading		   = Robot.arm.getRaw(Joint.wrist);
    	
    	
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
    	
    	SmartDashboard.putNumber("Arm shoulder motor", Robot.arm.getMotor(Joint.shoulder));
    	SmartDashboard.putNumber("Arm elbow motor", Robot.arm.getMotor(Joint.elbow));
    	SmartDashboard.putNumber("Arm wrist motor", Robot.arm.getMotor(Joint.wrist));
    }
    
    private void update_claw(){
    	boolean claw_isOpen = !Robot.claw.getClosed();
    	boolean claw_limitState = Robot.claw.getTrigger();
    	
    	if(claw_isOpen) SmartDashboard.putString("Claw State", "Open");
    	else            SmartDashboard.putString("Claw State", "Closed");
    	
    	if(claw_limitState) SmartDashboard.putString("Claw Trigger", "Triggered");
    	else				SmartDashboard.putString("Claw Trigger", "Not triggered");
    }
    
    private void update_drivetrain(){
    	boolean drive_isarmPerspective = Robot.drivetrain.getArmPerspective();
    	boolean drive_isLowGear = Robot.drivetrain.getLowGear();
    	double drive_tiltAngle = Robot.drivetrain.getTilt();
    	
    	if(drive_isarmPerspective) SmartDashboard.putString("Drive Perspective", "Arm");
    	else					   SmartDashboard.putString("Drive Perspective", "Stacker");
    	
    	if(drive_isLowGear) SmartDashboard.putString("Drive gear", "Low Gear");
    	else				SmartDashboard.putString("Drive gear", "High gear");
    	
    	SmartDashboard.putNumber("Drive tilt", drive_tiltAngle);

    }
    
    
    
    private void update_gyroscope(){
    	double gyro_rawAngle = Robot.gyroscope.getRawAngle();
    	double gyro_constrainedAngle = Robot.gyroscope.getAngle();
    	
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

