package org.usfirst.frc.team1305.robot.commands.arm;

import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.subsystems.Arm;
import org.usfirst.frc.team1305.robot.subsystems.Arm.ArmMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Toggle the wrist between the automatic mode and he manual mode.
 */
public class ArmToggleAutoWrist extends Command {
    public ArmToggleAutoWrist() {
       requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.arm.getMode() == ArmMode.automaticWrist){
    		Robot.arm.setMode(Arm.ArmMode.manualWrist);
    	}
    	else if(Robot.arm.getMode() == ArmMode.manualWrist){
    		Robot.arm.setMode(ArmMode.automaticWrist);
    	}
    	//otherwise we're actually in preset mode and shouldn't do anything.
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
