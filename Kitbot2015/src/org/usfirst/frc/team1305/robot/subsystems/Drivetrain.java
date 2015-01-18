package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.TripleTalon;
import org.usfirst.frc.team1305.robot.commands.drivetrain.Drive;
import org.usfirst.frc.team1305.robot.commands.drivetrain.SmoothDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private TripleTalon mL = new TripleTalon(RobotMap.PWM_DRIVE_LEFT_1,
											RobotMap.PWM_DRIVE_LEFT_2,
											RobotMap.PWM_DRIVE_LEFT_3);
	private TripleTalon mR = new TripleTalon(RobotMap.PWM_DRIVE_RIGHT_1,
											RobotMap.PWM_DRIVE_RIGHT_2,
											RobotMap.PWM_DRIVE_RIGHT_3);
	
	private RobotDrive drive = new RobotDrive(mL, mR);
	
	private Solenoid Shifter = new Solenoid(1);
	private boolean isHighGear = false;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new SmoothDrive());
    }
    
    public void arcadeDrive(double moveValue, double rotateValue){
    	drive.arcadeDrive(moveValue, rotateValue);
    }
    
    public void tankDrive(double leftValue, double rightValue){
    	drive.tankDrive(leftValue, rightValue);
    }
    
    public void SwitchGear(){
    	if (isHighGear == false){
    		isHighGear = true;
    		SmartDashboard.putString("Gear Status:", "High");
    		drive.arcadeDrive(0.0, 0.0);
    		Shifter.set(true);
    	}
    	else{
    		isHighGear = false;
    		SmartDashboard.putString("Gear Status:", "Low");
    		drive.arcadeDrive(0.0, 0.0);
    		Shifter.set(false);
    	}
    		
    }
}

