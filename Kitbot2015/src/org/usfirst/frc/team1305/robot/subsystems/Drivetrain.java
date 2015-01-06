package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Talon mL1 = new Talon(RobotMap.PWM_Drive_Back_Left);
	private Talon mL2 = new Talon(RobotMap.PWM_Drive_Front_Left);
	private Talon mR1 = new Talon(RobotMap.PWM_Drive_Back_Right);
	private Talon mR2 = new Talon(RobotMap.PWM_Drive_Front_Right);
	private RobotDrive drive = new RobotDrive(mL1, mL2, mR1, mR2);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand();
    }
    
    public void ArcadeDrive(double moveValue, double rotateValue){
    	drive.arcadeDrive(moveValue, rotateValue);
    }
}

