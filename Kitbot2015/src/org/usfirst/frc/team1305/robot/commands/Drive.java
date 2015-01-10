package org.usfirst.frc.team1305.robot.commands;

import java.util.concurrent.CountDownLatch;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1305.robot.Robot;

/**
 *
 */
public class Drive extends Command {

	private int s = 0;
	private double v = 0;
	
    public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double XL = Robot.oi.getDriveXL();
    	double YL = Robot.oi.getDriveYL();
    	if (s == 3){
    		if(v/YL < 0){
    			Robot.drivetrain.ArcadeDrive(0, 0);
    			
    			v = YL;
    			s = 0;
    			SmartDashboard.putString("Safey Buffer :", "Engaged");
    			SmartDashboard.putNumber("Safey Buffer count :", s);
    		}else{
    			Robot.drivetrain.ArcadeDrive(YL, XL);
    			v = YL;
    			s = 0;
    			SmartDashboard.putString("Safey Buffer :", "DisEngaged");
    			SmartDashboard.putNumber("Safey Buffer count :", s);
    		}
    	}else{
    		Robot.drivetrain.ArcadeDrive(YL, XL);
    		s = s + 1;
    		SmartDashboard.putString("Safey Buffer :", "DisEngaged");
			SmartDashboard.putNumber("Safey Buffer count :", s);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
