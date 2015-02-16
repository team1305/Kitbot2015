package org.usfirst.frc.team1305.robot.commands.elevator;

import org.usfirst.frc.team1305.robot.Constants;
import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class PreviousPreset extends Command {

    public PreviousPreset() {
        requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.setMode(true);
    	int preset = Robot.elevator.getPreset();
    	Robot.elevator.setPreset(preset-1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double dh = Robot.elevator.getHeight() - Robot.elevator.getPresetValue();
        double pd = Math.abs(dh / Robot.elevator.getPresetValue()) * 100.0;
        if( dh < Constants.ELEVATOR_PRESET_TOLERANCE ) return true;
        else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}