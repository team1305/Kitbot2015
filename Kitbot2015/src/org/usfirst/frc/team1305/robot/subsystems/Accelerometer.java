package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.commands.AccelerometerDefaultCommand;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Accelerometer extends Subsystem {
    
    private edu.wpi.first.wpilibj.interfaces.Accelerometer a = new BuiltInAccelerometer();
    Gyro g = new Gyro(0);
    
    public Accelerometer(){
    	//sensitivity from the gyro datasheet
    	g.setSensitivity(0.007);
    	//find the zero voltage
    	g.initGyro();
    	
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new AccelerometerDefaultCommand());
    }
    
    public double getX(){
    	return a.getX();
    }
    
    public double getY(){
    	return a.getY();
    }
    
    public double getZ(){
    	return a.getZ();
    }
    public double getAngle(){
    	return g.getAngle();
    }
    
    public double getRate(){
    	return g.getRate();
    }
    
}

