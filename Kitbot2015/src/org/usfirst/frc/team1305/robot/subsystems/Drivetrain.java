package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.TripleTalon;
import org.usfirst.frc.team1305.robot.commands.Drive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

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
	
	private Solenoid Leftshifter = new Solenoid(0);
	private Solenoid Rightshifter = new Solenoid(1);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
    
    public void ArcadeDrive(double moveValue, double rotateValue){
    	drive.arcadeDrive(moveValue, rotateValue);
    }
}

