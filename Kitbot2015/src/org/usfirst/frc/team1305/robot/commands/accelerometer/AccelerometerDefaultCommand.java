package org.usfirst.frc.team1305.robot.commands.accelerometer;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AccelerometerDefaultCommand extends Command {

    public AccelerometerDefaultCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.accelerometer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//puts accelerometer values to dashboard
    	//SmartDashboard.putNumber("Accel X", Robot.accelerometer.getAccelX());
    	//SmartDashboard.putNumber("Accel Y", Robot.accelerometer.getAccelY());
    	//SmartDashboard.putNumber("Accel Z", Robot.accelerometer.getAccelZ());
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
