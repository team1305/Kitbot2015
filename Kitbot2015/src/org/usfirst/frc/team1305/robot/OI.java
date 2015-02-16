package org.usfirst.frc.team1305.robot;


import org.usfirst.frc.team1305.robot.commands.claw.ToggleClaw;
import org.usfirst.frc.team1305.robot.commands.drivetrain.ToggleShifter;
import org.usfirst.frc.team1305.robot.commands.drivetrain.ToggleSmoothing;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class OI {
	
	//TODO: clean up axis names and decide final controller configuration

	// xbox axis reference
	public static final int AXIS_XL = 0; 
	public static final int AXIS_YL = 1;
	public static final int AXIS_XR = 2;
    public static final int AXIS_YR = 3;

	//Attack 3 axis reference
	public static final int AXIS_X = 0; 
	public static final int AXIS_Y = 1;


    
    //Drive stick button reference
    public static final int BTN_TOGGLE_CLAW = 3;
    public static final int BTN_LEFT_TURN = 4;
    public static final int BTN_RIGHT_TURN = 5;
    
    //Joystick delcaration
	private final Joystick driveStick = new Joystick(1);
	
	//button delcaration for drivestick
    Button toggle_Claw = new JoystickButton(driveStick, BTN_TOGGLE_CLAW);
    
    
	public OI(){
		//button assignments go here
		
		//assign ToggleClaw command to toggle_Claw button
		toggle_Claw.whenReleased(new ToggleClaw());

		
	}
	
	public static final int ToggleSmoothing() {
		return 0;
	}

	//getAxis functions for drivestick
	public double getDriveXL(){     
        SmartDashboard.putNumber("XL", driveStick.getRawAxis(AXIS_XL));
        return driveStick.getRawAxis(AXIS_XL);
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
