package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.TripleTalon;
import org.usfirst.frc.team1305.robot.commands.drivetrain.Drive;
import org.usfirst.frc.team1305.robot.commands.drivetrain.PacmanDrive;
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
	//triple talon leftside
	private TripleTalon mL = new TripleTalon(RobotMap.PWM_DRIVE_LEFT_1,
											RobotMap.PWM_DRIVE_LEFT_2,
											RobotMap.PWM_DRIVE_LEFT_3);
	//triple talon rightside
	private TripleTalon mR = new TripleTalon(RobotMap.PWM_DRIVE_RIGHT_1,
											RobotMap.PWM_DRIVE_RIGHT_2,
											RobotMap.PWM_DRIVE_RIGHT_3);
	
	//uses triple talon to assign left and right side to drivetrian
	private RobotDrive drive = new RobotDrive(mL, mR);
	
	//things for shifter on B3
	private Solenoid Shifter = new Solenoid(1);
	private boolean isHighGear = false;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
//    	setDefaultCommand(new SmoothDrive());
    	setDefaultCommand(new PacmanDrive());
    }
    
    //function for arcadedrive
    public void arcadeDrive(double moveValue, double rotateValue){
    	drive.arcadeDrive(moveValue, rotateValue);
    }
    
    //function for tankdrive
    public void tankDrive(double leftValue, double rightValue){
    	drive.tankDrive(leftValue, rightValue);
    }
    public void drive(double move, double rotate){
    	drive.drive(move, rotate);
    }
    
    //function to shift gears on B3
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

