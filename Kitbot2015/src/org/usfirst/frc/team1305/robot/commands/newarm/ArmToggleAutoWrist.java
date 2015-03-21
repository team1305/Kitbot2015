package org.usfirst.frc.team1305.robot.commands.newarm;

import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.subsystems.NewArm;
import org.usfirst.frc.team1305.robot.subsystems.NewArm.ArmMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Toggle the wrist between the automatic mode and he manual mode.
 */
public class ArmToggleAutoWrist extends Command {
    public ArmToggleAutoWrist() {
       requires(Robot.newArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.newArm.getMode() == ArmMode.automaticWrist){
    		Robot.newArm.setMode(NewArm.ArmMode.manualWrist);
    	}
    	else if(Robot.newArm.getMode() == ArmMode.manualWrist){
    		Robot.newArm.setMode(ArmMode.automaticWrist);
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
