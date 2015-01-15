
package org.usfirst.frc.team1305.robot.commands.powerpanel;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1305.robot.Robot;

/**
 *
 */
public class getPowerMetric extends Command {

    public getPowerMetric() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.powerPanel);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.powerPanel.ClearSticky();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.powerPanel.powerMetric();
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
