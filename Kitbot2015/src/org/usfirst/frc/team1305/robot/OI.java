package org.usfirst.frc.team1305.robot;

import org.usfirst.frc.team1305.robot.commands.ToggleShifter;
import org.usfirst.team1305.robot2014.commands.ToggleSmoothing;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class OI {
	
	public static final int ToggleSmoothing() {
		return 0;
	}
	public static final int AXIS_XL = 0; 
	public static final int AXIS_YL = 1;
	public static final int AXIS_XR = 2;
    public static final int AXIS_YR = 3;
    
    public static final int Right_Bumper = 6;


	private final Joystick driveStick = new Joystick(1);
	
    Button shift = new JoystickButton(driveStick, Right_Bumper);
    
    
	public OI(){
		
		shift.whenPressed(new ToggleShifter());
		
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
	
    
    
    


}