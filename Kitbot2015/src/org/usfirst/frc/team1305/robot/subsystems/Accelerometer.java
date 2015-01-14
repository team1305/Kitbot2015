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
    Gyro g = new Gyro(0);
    
    private int AMV ;
    public int Turncount = 0;
    
    public Accelerometer(){
    	//sensitivity from the gyro datasheet
    	g.setSensitivity(0.0068);
    	//find the zero voltage
    	g.initGyro();
    	
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new AccelerometerDefaultCommand());
    }
    
    public double getAccelX(){
    	return a.getX();
    }
    
    public double getAccelY(){
    	return a.getY();
    }
    
    public double getAccelZ(){
    	return a.getZ();
    }
    public double getGyroAngle(){
    	return g.getAngle();
    }
    
    public double getGyroRate(){
    	return g.getRate();
    }
    public void Snapturn(){
    	AMV = (int)Math.round(g.getAngle());
    	if(Turncount >= 1){
    		while(AMV % 90 < 88 ){
    			AMV = (int)Math.round(g.getAngle());
    			Robot.drivetrain.arcadeDrive(0, 0.4);
    			AMV = (int)Math.round(g.getAngle());
    		}
    		Turncount = Turncount - 1;
    	}
    	else if (Turncount < 0) {
			while(AMV % 90 > 2){
				AMV = (int)Math.round(g.getAngle());
				Robot.drivetrain.arcadeDrive(0, -0.4);
				AMV = (int)Math.round(g.getAngle());
			}
			Turncount = Turncount + 1;
    	}
    }
    public void LTV(){
    	Turncount = Turncount - 1;
    }
    public void RTV(){
    	Turncount = Turncount + 1;
    }
}

