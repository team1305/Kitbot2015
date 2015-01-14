package org.usfirst.frc.team1305.robot;

import org.usfirst.frc.team1305.robot.commands.accelerometer.LeftTurnIncrament;
import org.usfirst.frc.team1305.robot.commands.accelerometer.RightTurnIncrament;
import org.usfirst.frc.team1305.robot.commands.drivetrain.ToggleShifter;
import org.usfirst.frc.team1305.robot.commands.drivetrain.ToggleSmoothing;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class OI {
	
	
	
	public static final int AXIS_XL = 0; 
	public static final int AXIS_YL = 1;
	public static final int AXIS_XR = 2;
    public static final int AXIS_YR = 3;
    
    //public static final int Top_button_Shift = 3;
    public static final int Left_Turn = 4;
    public static final int Right_Turn = 5;

	private final Joystick driveStick = new Joystick(1);
	
   // Button shift = new JoystickButton(driveStick, Top_button_Shift);
    Button TurnLeft = new JoystickButton(driveStick, Left_Turn);
    Button TurnRight = new JoystickButton(driveStick, Right_Turn);
    
	public OI(){
		
	//	shift.whenPressed(new ToggleShifter());
		TurnLeft.whenReleased(new LeftTurnIncrament());
		TurnRight.whenReleased(new RightTurnIncrament());
		
	}
	
	public static final int ToggleSmoothing() {
		return 0;
	}

	
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
