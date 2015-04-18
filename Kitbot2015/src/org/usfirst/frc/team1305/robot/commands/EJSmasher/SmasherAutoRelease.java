package org.usfirst.frc.team1305.robot.commands.EJSmasher;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Deploys the smasher in autonomous mode. This command assumes that the winch wire has already been unwound
 * the proper amount.
 */
public class SmasherAutoRelease extends Command {
	
	private final double RELEASE_FALLING_TIME = 1.1;
	
	private Timer t = new Timer();

    public SmasherAutoRelease() {
    	requires(Robot.ejSmasher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	t.reset();
    	t.start();
    	Robot.ejSmasher.trigger_release();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return t.get() >= RELEASE_FALLING_TIME;
    }

    // Called once after isFinished returns true
    protected void end() {
    	t.stop();
    	t.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	t.stop();
    	t.reset();
    }
}
