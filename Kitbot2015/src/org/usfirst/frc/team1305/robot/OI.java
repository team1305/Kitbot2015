package org.usfirst.frc.team1305.robot;

import org.usfirst.frc.team1305.robot.commands.drivetrain.ToggleShifter;
import org.usfirst.frc.team1305.robot.commands.arm.ExtendedPresetCommand;
import org.usfirst.frc.team1305.robot.commands.arm.MaxStackPresetCommand;
import org.usfirst.frc.team1305.robot.commands.arm.MoveElbowCommand;
import org.usfirst.frc.team1305.robot.commands.arm.MoveShoulderCommand;
import org.usfirst.frc.team1305.robot.commands.arm.MoveWristCommand;
import org.usfirst.frc.team1305.robot.commands.arm.TransportPresetCommand;
import org.usfirst.frc.team1305.robot.commands.arm.ArmDefaultCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class OI {
	
	
	
	public static final int DRIVE_AXIS_XL = 0; 
	public static final int DRIVE_AXIS_YL = 1;
	public static final int DRIVE_AXIS_XR = 2;
    public static final int DRIVE_AXIS_YR = 3;
    
    public static final int ARM_AXIS_YR = 1;
    public static final int ARM_AXIS_YL = 3;
    public static final int STACKER_AXIS_YL = 3;
    
    //public static final int ARM_AXIS_YR = 3;
    //public static final int STACKER_AXIS_YL = 1;
    

    public static final int BUTTON_RB = 6;
    public static final int BUTTON_LB = 5;
    public static final int BUTTON_X = 3;
    public static final int BUTTON_Y = 4;
    public static final int BUTTON_B = 2;
    public static final int BUTTON_A = 1;
    public static final int LEFT_JOYSTICK = 9;
    private final boolean invertArmStick = true;

	private final Joystick driveStick = new Joystick(0);
	private final Joystick armStick = new Joystick(1);
	
	
    Button shift = new JoystickButton(driveStick, BUTTON_RB);
    Button claw = new JoystickButton(armStick, BUTTON_RB);
    Button shoulderButton = new JoystickButton(armStick, BUTTON_X);
    Button elbowButton = new JoystickButton(armStick, BUTTON_Y);
    Button wristButton = new JoystickButton(armStick, BUTTON_B);
    Button armIdleButton = new JoystickButton(armStick, BUTTON_A);
    Button presetButton = new JoystickButton(armStick, BUTTON_LB);
    Button manualOverride = new JoystickButton(armStick, LEFT_JOYSTICK);

    
    
	public OI(){
		//SmartDashboard.putBoolean("Joystick is", manualOverride.get());
		//shift.whenPressed(new ToggleShifter());
		//if(manualOverride.get() == true){
			elbowButton.whenPressed(new MoveElbowCommand());
			shoulderButton.whenPressed(new MoveShoulderCommand());
			wristButton.whenPressed(new MoveWristCommand());
		//}else{
			manualOverride.whileHeld(new ExtendedPresetCommand());
			armIdleButton.whileHeld(new TransportPresetCommand());
			presetButton.whileHeld(new MaxStackPresetCommand());
		//}
		claw.whenPressed(new ToggleShifter());

	}
	
	public static final int ToggleSmoothing() {
		return 0;
	}

	
	public double getDriveXL(){     
        SmartDashboard.putNumber("XL", driveStick.getRawAxis(DRIVE_AXIS_XL));
        return driveStick.getRawAxis(DRIVE_AXIS_XL);
    }
	
    public double getDriveYL(){
        SmartDashboard.putNumber("YL", driveStick.getRawAxis(DRIVE_AXIS_YL));
        return driveStick.getRawAxis(DRIVE_AXIS_YL);
    }
    
    public double getDriveXR(){
        SmartDashboard.putNumber("XR", driveStick.getRawAxis(DRIVE_AXIS_XR));
        return driveStick.getRawAxis(DRIVE_AXIS_XR);
    }
    
    public double getDriveYR(){
        SmartDashboard.putNumber("YR", driveStick.getRawAxis(DRIVE_AXIS_YR));
        return driveStick.getRawAxis(DRIVE_AXIS_YR);
    }
    
    public double getShoulderYL(){
    	SmartDashboard.putNumber("Shoulder YL", armStick.getRawAxis(ARM_AXIS_YL));
    	if (invertArmStick)
        {
    		return armStick.getRawAxis(ARM_AXIS_YL) * -1;
        }
    	else
    	{
    		return armStick.getRawAxis(ARM_AXIS_YL);
    	}
    }
    
    public double getElbowYR(){
    	SmartDashboard.putNumber("Elbow YR", armStick.getRawAxis(ARM_AXIS_YR));
    	if (invertArmStick)
        {
    		return armStick.getRawAxis(ARM_AXIS_YR) * -1;
        }
    	else
    	{
    		return armStick.getRawAxis(ARM_AXIS_YR);
    	}
    }
    
    public double getArmYR(){
    	SmartDashboard.putNumber("Arm YR", armStick.getRawAxis(ARM_AXIS_YR));
    	if (invertArmStick)
        {
    		return armStick.getRawAxis(ARM_AXIS_YR) * -1;
        }
    	else
    	{
    		return armStick.getRawAxis(ARM_AXIS_YR);
    	}
    }
    
    public double getStackerYL(){
        SmartDashboard.putNumber("Stacker YR", armStick.getRawAxis(STACKER_AXIS_YL));
        return armStick.getRawAxis(STACKER_AXIS_YL);
    }
	
    public double getDriveAxis(int axis){
    	return driveStick.getRawAxis(axis);    	
    }
    
    
    


}
