package org.usfirst.frc.team1305.robot.commands.arm;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArmDefaultCommand extends Command {

	
    public ArmDefaultCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("ArmDefaultCommand is Initialized");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arm.MoveElbow(Robot.oi.getElbowYR());
    	Robot.arm.MoveShoulder(Robot.oi.getShoulderYL());
    	Robot.arm.AutoMoveWrist();
    }

    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.MoveElbow(0);
    	Robot.arm.MoveShoulder(0);
    	Robot.arm.MoveWrist(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.arm.MoveElbow(0);
    	Robot.arm.MoveShoulder(0);
    	Robot.arm.MoveWrist(0);
    }
}
