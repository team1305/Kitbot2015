package org.usfirst.frc.team1305.robot.commands.drivetrain;


import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Standard scrub drive for scrubs.
 */
public class EncoderTurn extends Command {


	private double targetSpeed;
	private double targetDistance;
    private double leftOriginalDistance;
	private double rightOriginalDistance;
	private static final double TURN_RADIUS = 0.9427;
	private double l;
	private double r;
	

	public EncoderTurn(double angle, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(Drive);
    	requires(Robot.drivetrain);
    	targetDistance = TURN_RADIUS*(Math.PI/180)*angle;
    	SmartDashboard.putNumber("Target Encoder Distance", targetDistance);
    	targetSpeed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	leftOriginalDistance = Robot.drivetrain.getLeftEncDistance();
    	rightOriginalDistance = Robot.drivetrain.getRightEncDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	l = 0;
    	r = 0;
    	double leftDistance =  Robot.drivetrain.getLeftEncDistance() - leftOriginalDistance;
    	double rightDistance = Robot.drivetrain.getRightEncDistance() - rightOriginalDistance;
    	if(targetDistance>=0){
    		if(leftDistance >= -targetDistance){
    			r = -targetSpeed;
    		}
    		if(rightDistance <= targetDistance){
    			l = targetSpeed;
    		}
//    	}else{
//    		if(leftDistance <= -targetDistance){
//    			l = -targetSpeed;
//    		}
//    		if(rightDistance >= -targetDistance){
//    			r = targetSpeed;
//    		}
    	}
    	SmartDashboard.putNumber("Left Value Encoder", l);
    	SmartDashboard.putNumber("Right Value Encoder", r);
    	SmartDashboard.putBoolean("IS THIS WORKING", rightDistance <= targetDistance);
		SmartDashboard.putNumber("Right Distance", rightDistance);
    	Robot.drivetrain.tankDrive(l, r);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putNumber("Left", l);
    	SmartDashboard.putNumber("Right", r);
    	return(l*l<= targetSpeed*targetSpeed/2.0 && r*r<= targetSpeed*targetSpeed/2.0);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.tankDrive(0, 0);
    }

}
