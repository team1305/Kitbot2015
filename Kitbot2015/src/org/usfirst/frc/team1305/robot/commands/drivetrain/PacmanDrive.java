package org.usfirst.frc.team1305.robot.commands.drivetrain;

import org.usfirst.frc.team1305.robot.Constants;
import org.usfirst.frc.team1305.robot.DriveTrainPIDOutput;
import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

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
    	
    	rotateController = new PIDController(Constants.PACMAN_P,
    											Constants.PACMAN_I,
    											Constants.PACMAN_D,
    											Robot.gyroscope,
    											dtpo);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//first we get the x and y values for the joystick
    	double x = Robot.oi.getDriveX();
    	double y = Robot.oi.getDriveY();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    /**
     * Get the angle the stick is making with the positive-vertical.
     * 
     * 0 degrees is considered to be stick full-foreward
     * @param x x-value of stick
     * @param y y-value of stick
     */
    private void getAngle(double x, double y){
    	double theta = //TODO: finish this.
    }
    
}
