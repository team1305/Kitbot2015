package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Moves robot forward in autonomous, calls Drivetrain subsystem.
 */
public class AutonomousGyroTurn extends Command {

	
	private double offset;
	private double originalReading;
	private final double TOLERANCE = 0.5;

	/**
	 * A positive degree offset is clockwise, and negative is counterclockwise
	 */
    public AutonomousGyroTurn(double degreeOffset) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	offset = degreeOffset;
    	requires(Robot.drivetrain);
    	requires(Robot.gyroscope);
    	originalReading = Robot.gyroscope.getRawAngle();
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.gyroscope.getRawAngle()-(originalReading+offset) >= 50*TOLERANCE){
    		Robot.drivetrain.tankDrive(0.6, -0.6);
    	}else if(Robot.gyroscope.getRawAngle()-(originalReading+offset) <= -50*TOLERANCE){
    		Robot.drivetrain.tankDrive(-0.6, 0.6);
    	}else if(Robot.gyroscope.getRawAngle()-(originalReading+offset) >= 30*TOLERANCE){
    		Robot.drivetrain.tankDrive(0.4, -0.4);
    	}else if(Robot.gyroscope.getRawAngle()-(originalReading+offset) <= -30*TOLERANCE){
    		Robot.drivetrain.tankDrive(-0.4, 0.4);
    	}else if(Robot.gyroscope.getRawAngle()-(originalReading+offset) >= TOLERANCE){
    		Robot.drivetrain.tankDrive(0.2, -0.2);
    	}else if(Robot.gyroscope.getRawAngle()-(originalReading+offset) <= TOLERANCE){
    		Robot.drivetrain.tankDrive(-0.2, 0.2);
    	}else{
    		Robot.drivetrain.tankDrive(0, 0);
    	}
    	SmartDashboard.putNumber("Absolute Gyro", Robot.gyroscope.getRawAngle());
    	SmartDashboard.putNumber("Original Gyro", originalReading);
    	SmartDashboard.putNumber("Gyro Diff", Robot.gyroscope.getRawAngle()-(originalReading+offset));
    }


    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Math.abs(Robot.gyroscope.getRawAngle()-(originalReading+offset)) <= TOLERANCE){
    		
            return true;

        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
