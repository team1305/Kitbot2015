package org.usfirst.frc.team1305.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Wait extends Command {
	Timer t = new Timer();
	double interval;
    public Wait(double numSecs) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	interval = numSecs;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	t.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (t.get() >= interval);
    }

    // Called once after isFinished returns true
    protected void end() {
    	t.stop();
    	t.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	t.stop();
    	t.reset();
    }
}
