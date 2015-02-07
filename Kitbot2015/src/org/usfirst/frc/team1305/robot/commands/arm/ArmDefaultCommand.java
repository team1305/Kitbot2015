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
	int desiredArmPreset;
	String newClawDirectionRequest;
	String prevClawDirectionRequest;
	
	//double elbowVoltageYIntercept = 0.065;
	//double shoulderVoltageYIntercept = -.019;
	//double elbowVoltageSlope = 0.175;
	//double shoulderVoltageSlope = 0.184;
	
	double elbowVoltageYIntercept = 1.25;
	double shoulderVoltageYIntercept = 0.15;
	double elbowVoltageSlope = -0.21;
	double shoulderVoltageSlope = 0.21;
	String SDTAG_WHAT_ANGLE = "What angle do you want";
	String SDTAG_IS_ELBOW = "Elbow?";
	String SDTAG_SHOULDER_POT_CALC = "Shoulder Pot Calc";
	String SDTAG_SHOULDER_POT_ACT = "Shoulder Pot Actual";
	String SDTAG_ELBOW_POT_CALC = "Elbow Pot Calc";
	String SDTAG_ELBOW_POT_ACT = "Elbow Pot Actual";
	String SDTAG_GOTO_ARM_PRESET = "Go to Arm Position (1 - 4)";
	String SDTAG_CLAW_UP_DOWN_IN_OUT = "Send claw UP, DOWN, IN or OUT";
	
	
	
    public ArmDefaultCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isElbow = true;
    	desiredAngle = 45;
    	desiredArmPreset = 1;
    	newClawDirectionRequest = "UPDOWN";
    	SmartDashboard.putNumber(SDTAG_WHAT_ANGLE, desiredAngle);
    	SmartDashboard.putBoolean(SDTAG_IS_ELBOW, isElbow);
    	//SmartDashboard.putNumber(SDTAG_GOTO_ARM_PRESET, 1);
    	SmartDashboard.putString(SDTAG_CLAW_UP_DOWN_IN_OUT, newClawDirectionRequest);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
//    	desiredAngle = SmartDashboard.getNumber(SDTAG_WHAT_ANGLE);
//    	isElbow = SmartDashboard.getBoolean(SDTAG_IS_ELBOW);
//    	desiredArmPreset = (int) SmartDashboard.getNumber(SDTAG_GOTO_ARM_PRESET);
//    	newClawDirectionRequest = SmartDashboard.getString(SDTAG_CLAW_UP_DOWN_IN_OUT);
//    	
//    	if (newClawDirectionRequest != prevClawDirectionRequest)
//    	{
//    		//user wants us to do something (use in lieu of joystick for now)
//    		if (newClawDirectionRequest == "UP")
//    		{
//    			Robot.arm.MoveArmUpDown(1);
//    		}
//    		else if (newClawDirectionRequest == "DOWN")
//    		{
//    			Robot.arm.MoveArmUpDown(-1);
//    		}
//    		else if (newClawDirectionRequest == "IN")
//    		{
//    			Robot.arm.MoveArmInOut(-1);
//    		}
//    		else if (newClawDirectionRequest == "OUT")
//    		{
//    			Robot.arm.MoveArmInOut(1);
//    		}
//    	}
//    	SmartDashboard.putNumber(SDTAG_SHOULDER_POT_CALC, calcShoulderPot(desiredAngle));
//    	SmartDashboard.putNumber(SDTAG_SHOULDER_POT_ACT, Robot.arm.getShoulderPot());
//    	SmartDashboard.putNumber(SDTAG_ELBOW_POT_CALC, calcElbowPot(desiredAngle));
//    	SmartDashboard.putNumber(SDTAG_ELBOW_POT_ACT, Robot.arm.getElbowPot());
//
//    	SmartDashboard.putNumber(SDTAG_GOTO_ARM_PRESET, Robot.arm.getElbowPot());
    }

    private double calcShoulderPot(double targetAngle){
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
    
    private double calcElbowPot(double targetAngle){
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
