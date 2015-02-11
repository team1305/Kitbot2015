package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.commands.accelerometer.AccelerometerDefaultCommand;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Accelerometer extends Subsystem {
    
    private edu.wpi.first.wpilibj.interfaces.Accelerometer a = new BuiltInAccelerometer();
    

    
    public Accelerometer(){
    	
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new AccelerometerDefaultCommand());
    }
    
    //functions to get values from accelerometer axis's
    public double getAccelX(){
    	return a.getX();
    }
    
    public double getAccelY(){
    	return a.getY();
    }
    
    public double getAccelZ(){
    	return a.getZ();
    }
    
}

