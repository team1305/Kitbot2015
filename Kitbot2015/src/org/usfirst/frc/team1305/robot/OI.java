package org.usfirst.frc.team1305.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class OI {
    
	public static final int AXIS_XL = 0;
	public static final int AXIS_YL = 1;
	public static final int AXIS_XR = 2;
    public static final int AXIS_YR = 3;


	private final Joystick driveStick = new Joystick(1);
	
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