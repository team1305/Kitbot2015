package org.usfirst.frc.team1305.robot.commands.drivetrain;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves the robot forward until the claw is triggered. A timeout is optional.
 * @deprecated use {@link DriveEncoderUntilClawTrig}
 */
public class DriveUntilClawTrig extends Command {

	private double lmSpeed;
	private double rmSpeed;
	private double timeout;
	private boolean usingTimeout = false;
	private Timer t = new Timer();

	/**
	 * Drive the robot forward until the claw trigger is triggered.
	 * 
	 * Automatically closes the claw when finished.
	 * @param leftSpeed left wheel speed
	 * @param rightSpeed right wheel speed
	 * @deprecated use {@link DriveEncoderUntilClawTrig}
	 */
    public DriveUntilClawTrig(double leftSpeed, double rightSpeed) {
    	requires(Robot.drivetrain);
    	requires(Robot.claw);
    	lmSpeed = leftSpeed;
    	rmSpeed = rightSpeed;

    }
    
    /**
     * Drive the robot forward until the claw trigger is triggered or the 
     * timeout expires, whichever comes first,.
     * 
     * Automatically closes the claw when finished.
     * @param leftSpeed left wheel speed
     * @param rightSpeed right wheel speed
     * @param timeout maximum time to spend in command
     */
    public DriveUntilClawTrig(double leftSpeed, double rightSpeed, double timeout) {
    	requires(Robot.drivetrain);
    	requires(Robot.claw);
    	lmSpeed = leftSpeed;
    	rmSpeed = rightSpeed;
    	this.timeout = timeout;
    	usingTimeout = true;

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(usingTimeout) t.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.tankDrive_raw(lmSpeed, rmSpeed);
    }


    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(usingTimeout && t.get() > timeout) return true;
    	else if(Robot.claw.getTrigger()) return true;
    	else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.claw.close();
    	Robot.drivetrain.stop();
    	t.stop();
    	t.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.stop();
    	t.stop();
    	t.reset();
    }
}
