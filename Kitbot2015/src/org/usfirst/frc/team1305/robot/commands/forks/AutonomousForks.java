package org.usfirst.frc.team1305.robot.commands.forks;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves robot forward in autonomous, calls Drivetrain subsystem.
 */
public class AutonomousForks extends Command {

    public AutonomousForks() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.forks);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.forks.AutonomousForks();
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
