package org.usfirst.frc.team1305.robot;

import org.usfirst.frc.team1305.robot.commands.drivetrain.ToggleShifter;
import org.usfirst.frc.team1305.robot.commands.drivetrain.ToggleSmoothing;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class OI {
	
	
	
	public static final int AXIS_X = 0; 
	public static final int AXIS_Y = 1;

    

	private final Joystick driveStick = new Joystick(1);
	
    
	public OI(){
		//button assignments go here
		
	}

	public double getDriveX(){     
        SmartDashboard.putNumber("XL", driveStick.getRawAxis(AXIS_X));
        return driveStick.getRawAxis(AXIS_X);
    }
	
    public double getDriveY(){
        SmartDashboard.putNumber("YL", driveStick.getRawAxis(AXIS_Y));
        return driveStick.getRawAxis(AXIS_Y);
    }
    
//    public double getDriveXR(){
//        SmartDashboard.putNumber("XR", driveStick.getRawAxis(AXIS_XR));
//        return driveStick.getRawAxis(AXIS_XR);
//    }
//    
//    public double getDriveYR(){
//        SmartDashboard.putNumber("YR", driveStick.getRawAxis(AXIS_YR));
//        return driveStick.getRawAxis(AXIS_YR);
//    }
	
    public double getDriveAxis(int axis){
    	return driveStick.getRawAxis(axis);    	
    }
    
    
    


}
