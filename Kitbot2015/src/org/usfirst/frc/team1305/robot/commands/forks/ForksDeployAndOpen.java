package org.usfirst.frc.team1305.robot.commands.forks;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * opens and deploys the forks for the beginning of auto mode.
 * 
 * There is a 1.5 second delay between deploying and opening
 */
public class ForksDeployAndOpen extends Command {
	Timer t = new Timer();
	private final double DEPLOYMENT_DELAY = 1.5;
    public ForksDeployAndOpen() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.forks);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.forks.deployStacker();
    	t.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return t.get() > DEPLOYMENT_DELAY;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.forks.openForks();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
