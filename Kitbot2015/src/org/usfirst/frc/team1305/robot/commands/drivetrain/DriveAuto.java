package org.usfirst.frc.team1305.robot.commands.drivetrain;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves robot forward in autonomous, calls Drivetrain subsystem.
 * 
 * @deprecated use DriveEncoder command
 */
public class DriveAuto extends Command {

	public double duration;
	public double lmSpeed;
	public double rmSpeed;
	
	Timer t = new Timer();
	
	/**
	 * @deprecated use DriveEncoder command
	 * @param time
	 * @param leftSpeed
	 * @param rightSpeed
	 */
    public DriveAuto(double time, double leftSpeed, double rightSpeed) {
    	requires(Robot.drivetrain);
    	duration = time;
    	lmSpeed = leftSpeed;
    	rmSpeed = rightSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	t.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.tankDrive_raw(lmSpeed, rmSpeed);
    }


    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return t.get() > duration;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.stop();
    }
}
