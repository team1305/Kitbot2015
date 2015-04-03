package org.usfirst.frc.team1305.robot.commands.drivetrain;


import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Standard scrub drive for scrubs.
 */
public class DriveEncoder extends Command {

	private double targetDistance;
	private double targetSpeed;
	private double leftOriginalDistance;
	private double rightOriginalDistance;
	private double averageDistance;
	private static final double ERROR_SCALING_CONSTANT = 4;
	private boolean isDrivingBackwards;
	
    public DriveEncoder(double distance, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(Drive);
    	requires(Robot.drivetrain);
    	targetDistance = distance;
    	targetSpeed = speed;
    	if(distance <= 0){
    		isDrivingBackwards = true;
    	}else{
    		isDrivingBackwards = false;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	leftOriginalDistance = Robot.drivetrain.getLeftEncDistance();
    	rightOriginalDistance = Robot.drivetrain.getRightEncDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftDistance =  Robot.drivetrain.getLeftEncDistance() - leftOriginalDistance;
    	double rightDistance = Robot.drivetrain.getRightEncDistance() - rightOriginalDistance;
        averageDistance = (leftDistance + rightDistance)/2.0;
    	SmartDashboard.putNumber("Average Encoder Distance", averageDistance);
    	double delta = rightDistance - leftDistance;
    	SmartDashboard.putNumber("Encoder Delta", delta);
    	if(isDrivingBackwards == false)
    		Robot.drivetrain.tankDrive_raw(targetSpeed-delta*ERROR_SCALING_CONSTANT, targetSpeed+delta*ERROR_SCALING_CONSTANT);
    	else
    		Robot.drivetrain.tankDrive_raw(-targetSpeed-delta*ERROR_SCALING_CONSTANT, -targetSpeed+delta*ERROR_SCALING_CONSTANT);
	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(isDrivingBackwards == false)
    		return(averageDistance >= targetDistance);
    	else
    		return(averageDistance <= targetDistance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive_raw(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.tankDrive_raw(0, 0);
    }

}