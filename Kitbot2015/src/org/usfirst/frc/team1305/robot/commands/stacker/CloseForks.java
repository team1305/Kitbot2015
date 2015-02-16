package org.usfirst.frc.team1305.robot.commands.stacker;

import org.usfirst.frc.team1305.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CloseForks extends Command {

	private static final double DELAY_BEFORE_FORK_VOLTAGE_TIMER = 0.5;
	private Timer voltageDelayTimer = new Timer();

    public CloseForks() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.stacker);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	voltageDelayTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.stacker.CloseForks();
    	
    }

    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (voltageDelayTimer.get() > DELAY_BEFORE_FORK_VOLTAGE_TIMER)
    	{
    		return Robot.stacker.IsVoltageOverThreshold();
    	}
    	else
    	{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.stacker.StopForks();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.stacker.StopForks();
    }
}
