package org.usfirst.frc.team1305.robot.commands.EJSmasher;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Manually retract the smasher.
 */
public class SmasherManualDeploy extends Command {

    public SmasherManualDeploy() {
    	requires(Robot.ejSmasher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ejSmasher.winch_down();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ejSmasher.winch_stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.ejSmasher.winch_stop();
    }
}
