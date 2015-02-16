package org.usfirst.frc.team1305.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.stacker.MoveStackerCommand;

import edu.wpi.first.wpilibj.CANTalon;

/**
 *
 */
public class Stacker extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon stackerMotor = new CANTalon(RobotMap.CAN_DEVICE_LIFT);
	private CANTalon leftForkMotor = new CANTalon(RobotMap.CAN_DEVICE_LEFT_FORK);
	private CANTalon rightForkMotor = new CANTalon(RobotMap.CAN_DEVICE_RIGHT_FORK);
	private final double FORK_CLOSE_SPEED = 1;
	private final double FORK_MOTOR_CURRENT_THRESHOLD = 7.5;
	
	public Stacker(){
		
	}
	
	private void updateSmartDashboard()
    {
		SmartDashboard.putNumber("Stacker Output Current", stackerMotor.getOutputCurrent());
		SmartDashboard.putNumber("Left Fork Output Current", leftForkMotor.getOutputCurrent());
		SmartDashboard.putNumber("Right Fork Output Current", rightForkMotor.getOutputCurrent());
		SmartDashboard.putNumber("StackerMotor", stackerMotor.get());
    }
	// Called just before this Command runs the first time
    protected void initialize() {
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new MoveStackerCommand());
    }
    
    public void MoveStacker(double yAxis, double xAxis){
    	
    	stackerMotor.set(yAxis);
    	leftForkMotor.set(xAxis);
    	rightForkMotor.set(xAxis);
    	updateSmartDashboard();
    }
    
    public void CloseForks(){
    	
    	leftForkMotor.set(FORK_CLOSE_SPEED);
    	rightForkMotor.set(FORK_CLOSE_SPEED);
    	updateSmartDashboard();
    }
    
    public void StopForks(){
    	leftForkMotor.set(0);
    	rightForkMotor.set(0);
    	updateSmartDashboard();
    }
    
    public boolean IsVoltageOverThreshold()
    {
    	return (leftForkMotor.getOutputCurrent() + leftForkMotor.getOutputCurrent()) / 2 >= FORK_MOTOR_CURRENT_THRESHOLD;
    }
}

