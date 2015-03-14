package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves robot forward in autonomous, calls Drivetrain subsystem.
 */
public class AutonomousBin extends Command {

public double lmSpeed;
public double rmSpeed;
    public AutonomousBin(double leftSpeed, double rightSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	lmSpeed = leftSpeed;
    	rmSpeed = rightSpeed;
    	requires(Robot.drivetrain);
    	requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }


    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.drivetrain.autonomousBin(lmSpeed, rmSpeed)){
            return true;

        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.claw.toggleGrab();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
