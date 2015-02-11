package org.usfirst.frc.team1305.robot;


import org.usfirst.frc.team1305.robot.commands.claw.ToggleClaw;
import org.usfirst.frc.team1305.robot.commands.drivetrain.ToggleShifter;
import org.usfirst.frc.team1305.robot.commands.drivetrain.ToggleSmoothing;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class OI {
	
	
	// Drivestick Axis referance
	public static final int AXIS_XL = 0; 
	public static final int AXIS_YL = 1;
	public static final int AXIS_XR = 2;
    public static final int AXIS_YR = 3;
    
    //Drive stick button referance
    public static final int Top_button_Toggle_Claw = 3;
    public static final int Left_Turn = 4;
    public static final int Right_Turn = 5;
    
    //Joystick delcaration
	private final Joystick driveStick = new Joystick(1);
	
	//button delcaration for drivestick
    Button toggle_Claw = new JoystickButton(driveStick, Top_button_Toggle_Claw);
    
    
	public OI(){
		
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
	
    public double getDriveYL(){
        SmartDashboard.putNumber("YL", driveStick.getRawAxis(AXIS_YL));
        return driveStick.getRawAxis(AXIS_YL);
    }
    
    public double getDriveXR(){
        SmartDashboard.putNumber("XR", driveStick.getRawAxis(AXIS_XR));
        return driveStick.getRawAxis(AXIS_XR);
    }
    
    public double getDriveYR(){
        SmartDashboard.putNumber("YR", driveStick.getRawAxis(AXIS_YR));
        return driveStick.getRawAxis(AXIS_YR);
    }
	
    public double getDriveAxis(int axis){
    	return driveStick.getRawAxis(axis);    	
    }
    
    
    


}
