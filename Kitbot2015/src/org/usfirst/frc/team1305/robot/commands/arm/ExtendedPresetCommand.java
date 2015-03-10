package org.usfirst.frc.team1305.robot.commands.arm;

import org.usfirst.frc.team1305.robot.OI;
import org.usfirst.frc.team1305.robot.Robot;



import org.usfirst.frc.team1305.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExtendedPresetCommand extends Command {

	
    public ExtendedPresetCommand() {
    	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arm.ArmPresets(Arm.ARM_PRESET_EXTENDED);
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
    	Robot.arm.StopElbow();
    	Robot.arm.StopShoulder();
    	Robot.arm.StopWrist();
    }
}
