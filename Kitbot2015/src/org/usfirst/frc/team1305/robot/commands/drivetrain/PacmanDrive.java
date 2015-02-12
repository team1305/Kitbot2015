package org.usfirst.frc.team1305.robot.commands.drivetrain;

import org.usfirst.frc.team1305.robot.Constants;
import org.usfirst.frc.team1305.robot.DriveTrainPIDOutput;
import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PacmanDrive extends Command {
	
	PIDController rotateController;
	DriveTrainPIDOutput dtpo = new DriveTrainPIDOutput();
	
	double moveValue;

    public PacmanDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.gyroscope);
    	
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	rotateController = new PIDController(Constants.PACMAN_P,
												Constants.PACMAN_I,
												Constants.PACMAN_D,
												Robot.gyroscope,
												dtpo);
		rotateController.setInputRange(0, 360.0);
		rotateController.setOutputRange(-1, 1);
		rotateController.setPercentTolerance(Constants.PACMAN_TOLERANCE);
		rotateController.setContinuous();
    	rotateController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//first we get the x and y values for the joystick
    	double x = -Robot.oi.getDriveX();
    	double y = Robot.oi.getDriveY();
    	SmartDashboard.putNumber("Pacdrive: Joystick X", x);
    	SmartDashboard.putNumber("Pacdrive: Joystick Y", y);
    	SmartDashboard.putNumber("Pacdrive: Angle", Robot.gyroscope.getAngle());
    	
    	//now we get the angle of the stick as well as the magnitude
    	double angle = getAngle(x, y);
    	double magnitude = getMagnitude(x, y);
    	SmartDashboard.putNumber("Pacdrive: stick angle", angle);
    	SmartDashboard.putNumber("Pacdrive: stick magnitude", magnitude);
    	//feed the rotate value into the PID object
    	rotateController.setSetpoint(angle);
    	
    	//Now get the result from the PIDController
    	double computedTurn = rotateController.get();
    	SmartDashboard.putNumber("Pacdrive: computed stick turn value", computedTurn);
    	
    	//if the angle is off too much, then we don't try to drive foreward
    	double computedForeward = magnitude*(1.0 - Math.abs(computedTurn));
    	
    	//Now we send the magnitude and the computedAngle to the standard  RobotDrive
    	Robot.drivetrain.arcadeDrive(computedForeward, computedTurn*magnitude);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	rotateController.disable();
    	rotateController.free();
    	Robot.drivetrain.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.arcadeDrive(0, 0);
    	
    	rotateController.disable();
    	rotateController.free();
    }
    
    /**
     * Get the angle the stick is making with the positive-vertical.
     * 
     * 0 degrees is considered to be stick full-foreward
     * @param x x-value of stick
     * @param y y-value of stick
     * 
     * @return the stick angle in degrees, constrained to [0, 360).
     */
    private double getAngle(double x, double y){
    	double theta;
    	theta = Math.atan2(x, y) + Math.PI;
    	//now return the angle, scaled to degrees.
    	return theta * 180 / Math.PI;
    }
    /**
     * Get the Magnitude of the stick displacement.
     * The result should be a value between the values of 0 and 1
     * @param x
     * @param y
     * @return Magnitude of the stick displacement, constrained to [0, 1]
     */
    private double getMagnitude(double x, double y){
    	//pythagorean formula used here. 
    	//we also need to divide by sqrt(2) to properly scale the output.
    	double base = x*x + y*y;
    	return Math.sqrt(base/2.0);
    }
}
