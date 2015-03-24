package org.usfirst.frc.team1305.robot.commands.newarm;

import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.subsystems.Arm.ArmMode;
import org.usfirst.frc.team1305.robot.subsystems.Arm.Preset;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Coommand which manually controls the arm. This command does not distinguish
 * between manual wrist control and automatic wrist control.
 */
public class ArmDisplacementControl extends Command {
	
	private final double MAXIMUM_MOVEMENT_RATE = 0.5; //feet per second
	
	private double x;
	private double y;
	private long t_x;
	private long t_y;
	
	private final double x_0 = Robot.arm.GEO_X0;
	private final double y_0 = Robot.arm.GEO_Y0;
	
	private final double l1 = Robot.arm.GEO_LINK1_LENGTH;
	private final double l2 = Robot.arm.GEO_LINK2_LENGTH;
	/**
	 * Coommand which manually controls the arm. This command does not distinguish
	 * between manual wrist control and automatic wrist control.
	 */
    public ArmDisplacementControl() {
       requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Put the arm in Preset Mode and tell it to 
    	Robot.arm.setPreset(Robot.arm.getCurrentPosition());
    	Robot.arm.setMode(ArmMode.preset);
    	//we assume that the arm is not in a preset mode.
    	t_x = Utility.getFPGATime(); //in microseconds.
    	t_y = Utility.getFPGATime(); //in microseconds.
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//First we get the desired x and y values from the axes
    	calculateX(Robot.oi.getArmAxis(Robot.oi.AXIS_XL));
    	calculateY(Robot.oi.getArmAxis(Robot.oi.AXIS_YL));
    	//compute c and theta_0
    	double c = Math.sqrt(Math.pow(x + x_0, 2) + Math.pow(y - y_0, 2)); //in feet
    	double theta_0 = Math.atan((y - y_0)/(x + x_0)) * 180.0 / Math.PI; //in degrees
    	//compute theta_1
    	double theta_1 = Math.acos((l2*l2 - c*c - l1*l1)/(-2.0*c*l1)) * 180.0 / Math.PI; //in degrees
    	//compute the shoulder angle, theta_s
    	double theta_s = theta_0 + theta_1;
    	//compute phi
    	double phi = Math.acos((c*c - l2*l2 - l1*l1)/(-2*l2*l1)) * 180.0/Math.PI; //degrees
    	
    	//now the desired shoulder angle is theta_s and elbow angle is phi
    	Preset p = new Preset(theta_s, phi, "Displacement x:" + x + " y:" + y);
    	//assign the computed preset.
    	
    	Robot.arm.setPreset(p);
    	//call update to actually move the arm.
    	Robot.arm.update(0,  0,  0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.arm.stopMotors();
    }
    
    /**
     * Compute the new desired x position for the arm based on axis input
     * @param axis the axis input in range [-1, 1]
     * @return The computed new value
     */
    private double calculateX(double axis){
    	//get the current time
    	long t = Utility.getFPGATime();
    	//compute the time interval since the last time this command has been run
    	double dt = (double) (t - t_x) / 1e6; //in seconds
    	//compute the new value for x
    	double newX = x + axis * dt * MAXIMUM_MOVEMENT_RATE;
    	
    	//save the new values for the next time the function is run
    	x = newX;
    	t_x = t;
    	return newX;
    }
    
    /**
     * Compute the new desired y position for the arm based on axis input
     * @param axis the axis input in range [-1, 1]
     * @return The computed new value
     */
    private double calculateY(double axis){
    	//get the current time
    	long t = Utility.getFPGATime();
    	//compute the time interval since the last time this command has been run
    	double dt = (double) (t - t_y) / 1e6; //in seconds
    	//compute the new value for y
    	double newY = y + axis * dt * MAXIMUM_MOVEMENT_RATE;
    	
    	//save the new values for the next time the function is run
    	y = newY;
    	t_y = t;
    	return newY;
    }
}
