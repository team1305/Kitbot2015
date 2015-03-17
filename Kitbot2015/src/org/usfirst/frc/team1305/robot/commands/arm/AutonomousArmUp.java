package org.usfirst.frc.team1305.robot.commands.arm;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves robot forward in autonomous, calls Drivetrain subsystem.
 */
public class AutonomousArmUp extends Command {

public double duration;
    public AutonomousArmUp(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	duration = time;
    	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }


    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.arm.autonomousArmUp(duration)){
            return true;

        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.StopElbow();
    	Robot.arm.StopShoulder();
    	Robot.arm.StopWrist();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
