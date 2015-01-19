package org.usfirst.frc.team1305.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.arm.ArmDefaultCommand;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

/**
 *
 */
public class Arm extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private AnalogPotentiometer potShoulder = new AnalogPotentiometer(RobotMap.ANALOG_VEX_POT_SHOULDER);
	private AnalogPotentiometer potElbow = new AnalogPotentiometer(RobotMap.ANALOG_VEX_POT_ELBOW);
	
	
	public Arm(){
		
	}
	
	// Called just before this Command runs the first time
    protected void initialize() {
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ArmDefaultCommand());
    }
    
    public double getShoulderPot(){
    	return potShoulder.get();
    }
    
    public double getElbowPot(){
    	return potElbow.get();
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

