package org.usfirst.frc.team1305.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	public Stacker(){
		
	}
	
	// Called just before this Command runs the first time
    protected void initialize() {
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new MoveStackerCommand());
    }
    
    public void MoveStacker(double yAxis, double xAxis){
//    	SmartDashboard.putNumber("StackerMotor", yAxis);
    	stackerMotor.set(yAxis);
    	leftForkMotor.set(xAxis);
    	rightForkMotor.set(xAxis);
    }
    
}

