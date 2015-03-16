package org.usfirst.frc.team1305.robot.commands.drivetrain;

import org.usfirst.frc.team1305.robot.DriveTrainPIDOutput;
import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * rotate the robot a specified number of degrees. An optional timeout may be 
 * specified.
 */
public class GyroRotate extends Command {
	//PID constants
	//TODO: figure out constants.
	private final double P = 1.0;
	private final double I = 0.05;
	private final double D = 0.5;

	private final double AUTO_TIMEOUT_PERIOD = 0.5; //seconds
	private final double TOLERANCE           = 1.0; //degrees
	
	private PIDController pid;
	
	//timeout timer
	private Timer t = new Timer();
	
	private double originalAngle;
	private double offset;
	private double timeout;
	private boolean usingAutoTimeout;

	/**
	 * Rotate the robot a specified number of degrees. The command will never
	 * time out and will 
	 * @param offset The number of degrees to turn. A positive value corresponds
	 * to clockwise, and a negative value is counterclockwise.
	 */
    public GyroRotate(double offset) {
    	requires(Robot.drivetrain);
    	this.offset        = offset;
    	this.originalAngle = Robot.gyroscope.getRawAngle();
    	this.timeout       = 0.0;
    	usingAutoTimeout   = true;
    	
    	pid = new PIDController(P, 
    							I, 
    							D, 
    							Robot.gyroscope, 
    							new DriveTrainPIDOutput());
    	pid.setOutputRange(-0.6,  0.6);
    }
    
	/**
	 * Rotate the robot a specified number of degrees. The command will never
	 * time out and will 
	 * @param offset The number of degrees to turn. A positive value corresponds
	 * to clockwise, and a negative value is counterclockwise.
	 * @param timeout the maximum time the program will operate for.
	 */
    public GyroRotate(double offset, double timeout) {
    	requires(Robot.drivetrain);
    	this.offset        = offset;
    	this.originalAngle = Robot.gyroscope.getRawAngle();
    	this.timeout 	   = timeout;
    	usingAutoTimeout   = false;
    	
    	pid = new PIDController(P,
    							I,
    							D, 
    							Robot.gyroscope, new DriveTrainPIDOutput());
    	pid.setOutputRange(-0.6,  0.6);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	pid.setSetpoint(originalAngle + offset);
    	pid.enable();
    	t.reset();
    	t.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//take the computed output value of the pid controller and apply
    	//to the wheels.
    	double val = pid.get();
    	Robot.drivetrain.tankDrive(val, -val);
    	
    	//if we leave the tolerance zone, then we reset the auto timer
    	if(usingAutoTimeout && Math.abs(pid.getError()) > TOLERANCE ){
    		t.reset();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//if we're using auto timeout and we've gone long enough without 
    	//leaving the tolerance zone
        if(usingAutoTimeout && t.get() > AUTO_TIMEOUT_PERIOD) return true;
        
        //if the manual timer has expired
        else if(!usingAutoTimeout && t.get() > timeout) return true;
        
        else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	t.stop();
    	pid.disable();
    	t.reset();
    	Robot.drivetrain.tankDrive(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	t.stop();
    	pid.disable();
    	t.reset();
    	Robot.drivetrain.tankDrive(0.0, 0.0);
    }
}
