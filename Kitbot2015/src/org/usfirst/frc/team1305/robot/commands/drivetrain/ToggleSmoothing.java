package org.usfirst.frc.team1305.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 * @author Root 1
 */
public class ToggleSmoothing extends Command {
    
    public ToggleSmoothing() {
        Object Drive = null;
        requires(Drive);
  }

    private void requires(Object drive) {
    }

	// Called just before this Command runs the first time
    protected void initialize() {
        Drive.toggleSmoothing();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}