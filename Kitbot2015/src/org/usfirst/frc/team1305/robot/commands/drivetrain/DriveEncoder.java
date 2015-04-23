package org.usfirst.frc.team1305.robot.commands.drivetrain;


import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Standard scrub drive for scrubs.
 */
public class DriveEncoder extends Command {

	private static final double ERROR_SCALING_CONSTANT = 2;	
	Timer t = new Timer();
	private boolean using_timeout = false;
	private double timeout;
	
	private double targetDistance;
	private double targetSpeed;
	private double leftOriginalDistance;
	private double rightOriginalDistance;
	private double averageDistance;
	private boolean isDrivingBackwards;
	
	/**
	 * Drive forward the specified distance, at the specified speed.
	 * @param distance the distance to cover, in feet.
	 * @param speed the speed to drive at, in range [0.1, 1]
	 */
    public DriveEncoder(double distance, double speed) {
    	requires(Robot.drivetrain);
    	targetDistance = distance;
    	//constrain speed
    	if(speed < 0.1) targetSpeed = 0.1;
    	else if(speed > 1) targetSpeed = 1.0;
    	else targetSpeed = speed;
    	
    	if(distance <= 0) isDrivingBackwards = true;
    	else              isDrivingBackwards = false;
    	
    }
    
    /**
	 * Drive forward the specified distance, at the specified speed, with a specified timeout
	 * @param distance the distance to cover, in feet. A negative distance will move backward.
	 * @param speed the speed to drive at, in range [0.1, 1]
	 * @param timeout the maximum time to try driving.
	 */
    public DriveEncoder(double distance, double speed, double timeout){
    	this(distance, speed);
    	using_timeout = true;
    	this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	leftOriginalDistance = Robot.drivetrain.getLeftEncDistance();
    	rightOriginalDistance = Robot.drivetrain.getRightEncDistance();
    	if(using_timeout) t.start();
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
    	if(using_timeout && t.get() > timeout)
    		return true;
    	else if(isDrivingBackwards == false)
    		return(averageDistance >= targetDistance);
    	else
    		return(averageDistance <= targetDistance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive_raw(0, 0);
    	t.stop();
    	t.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.tankDrive_raw(0, 0);
    	t.stop();
    	t.reset();
    }

}