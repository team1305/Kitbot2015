package org.usfirst.frc.team1305.robot.commands.arm;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElbowCommandblabllah extends Command {


    public MoveElbowCommandblabllah() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO:  replace getArmYR with getElbowYR
    	//Robot.arm.MoveElbow(Robot.oi.getElbowYR());
    	Robot.arm.MoveElbow(Robot.oi.getArmYR());
    }

    private double calcShoulderPot(double targetAngle){
    	return 0;
        }


    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.StopElbow();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.arm.StopElbow();
    }
}
