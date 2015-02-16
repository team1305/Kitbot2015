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
    	//Robot.arm.MoveShoulder(Robot.oi.getShoulderYL());
		Robot.arm.MoveShoulder(Robot.oi.getShoulderYL());
		
		//arm subsystem will only allow one of these two commands to control the wrist
		Robot.arm.MoveWrist(Robot.oi.getWristXR());    	
    	Robot.arm.MoveWristAutomatically();
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
