package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves robot forward in autonomous, calls Drivetrain subsystem.
 */
public class AutonomousMobility extends Command {

public double duration;
public double lmSpeed;
public double rmSpeed;
    public AutonomousMobility(double time, double leftSpeed, double rightSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	duration = time;
    	lmSpeed = leftSpeed;
    	rmSpeed = rightSpeed;
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.autonomousMobility(duration, lmSpeed, rmSpeed);
    }


    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.drivetrain.autonomousMobility(duration, 0.5, 0.5)){
            return true;

        }
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
