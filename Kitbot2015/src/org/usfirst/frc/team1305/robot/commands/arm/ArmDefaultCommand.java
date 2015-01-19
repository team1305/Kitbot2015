package org.usfirst.frc.team1305.robot.commands.arm;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArmDefaultCommand extends Command {

	double desiredAngle;
	double shoulderPotCalc;
	double elbowPotCalc;
	boolean isElbow;
	
	//double elbowVoltageYIntercept = 0.065;
	//double shoulderVoltageYIntercept = -.019;
	//double elbowVoltageSlope = 0.175;
	//double shoulderVoltageSlope = 0.184;
	
	double elbowVoltageYIntercept = 1.25;
	double shoulderVoltageYIntercept = 0.15;
	double elbowVoltageSlope = -0.21;
	double shoulderVoltageSlope = 0.21;
	
    public ArmDefaultCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isElbow = true;
    	desiredAngle = 45;
    	SmartDashboard.putNumber("What angle do you want", desiredAngle);
    	SmartDashboard.putBoolean("Elbow?", isElbow);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	desiredAngle = SmartDashboard.getNumber("What angle do you want");
    	isElbow = SmartDashboard.getBoolean("Elbow?");
    	
    	SmartDashboard.putNumber("Shoulder Pot Calc", getShoulderPotCalc(desiredAngle));
    	SmartDashboard.putNumber("Shoulder Pot Actual", Robot.arm.getShoulderPot());
    	SmartDashboard.putNumber("Elbow Pot Calc", getElbowPotCalc(desiredAngle));
    	SmartDashboard.putNumber("Elbow Pot Actual", Robot.arm.getElbowPot());
    }

    private double getShoulderPotCalc(double targetAngle){
    	//check if targetAngle == 0 so can put breakpoint
    	//on "return targetAngle" without breakpoint firing before
    	//we've even set the angle
    	if (isElbow | targetAngle == 0.0)
    	{
    		return 0;
    	}
    	else
    	{
    		return  (targetAngle/45 * shoulderVoltageSlope) + shoulderVoltageYIntercept;
    	}
    }
    
    private double getElbowPotCalc(double targetAngle){
    	if (isElbow & targetAngle!= 0.0)
    	{
    		//y = mx + b
    		return  (targetAngle/45 * elbowVoltageSlope) + elbowVoltageYIntercept;
    	}
    	else
    	{
    		return 0;
    	}
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
}
