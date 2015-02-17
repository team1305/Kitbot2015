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
	


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new SmoothDrive());
//    	setDefaultCommand(new PacmanDrive());
    }
    
    //function for arcadedrive
    public void arcadeDrive(double moveValue, double rotateValue){
    	drive.arcadeDrive(moveValue/1.7, rotateValue/2);
    }
    
    //function for tankdrive
    public void tankDrive(double leftValue, double rightValue){
    	drive.tankDrive(leftValue/1.4, rightValue/1.4);
//    	SmartDashboard.putNumber("LeftDrive", leftValue);
//    	SmartDashboard.putNumber("RightDrive", rightValue);
    }
    public void drive(double move, double rotate){
    	drive.drive(move/2, rotate/2);
    }

    		
    
}

