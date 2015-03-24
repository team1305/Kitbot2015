package org.usfirst.frc.team1305.robot.commands.newarm;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Coommand which manually controls the arm. This command does not distinguish
 * between manual wrist control and automatic wrist control.
 */
public class ArmManualControl extends Command {
	/**
	 * Coommand which manually controls the arm. This command does not distinguish
	 * between manual wrist control and automatic wrist control.
	 */
    public ArmManualControl() {
       requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//we assume that the arm is not in a preset mode.
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double as = Robot.oi.getShoulderAxis();
    	double ae = Robot.oi.getElbowAxis();
    	double aw = Robot.oi.getWristAxis();
    	Robot.arm.update(as, ae, aw);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.arm.stopMotors();
    }
}
