package org.usfirst.frc.team1305.robot;


import org.usfirst.frc.team1305.robot.commands.claw.ToggleClaw;
import org.usfirst.frc.team1305.robot.commands.drivetrain.SetArmPerspective;
import org.usfirst.frc.team1305.robot.commands.drivetrain.SetStackerPerspective;
import org.usfirst.frc.team1305.robot.commands.forks.ToggleForks;
import org.usfirst.frc.team1305.robot.commands.arm.ExtendedPresetCommand;
import org.usfirst.frc.team1305.robot.commands.arm.MaxStackPresetCommand;
import org.usfirst.frc.team1305.robot.commands.arm.MoveElbowCommand;
import org.usfirst.frc.team1305.robot.commands.arm.MoveShoulderCommand;
import org.usfirst.frc.team1305.robot.commands.arm.MoveWristCommand;
import org.usfirst.frc.team1305.robot.commands.arm.ToggleWristAutoManuCommand;
import org.usfirst.frc.team1305.robot.commands.arm.TransportPresetCommand;
import org.usfirst.frc.team1305.robot.commands.arm.ArmDefaultCommand;

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
    
    public static final int ARM_AXIS_YR = 5;
    public static final int ARM_AXIS_YL = 1;
    public static final int ARM_AXIS_XR = 4;
    public static final int ARM_AXIS_R_PUSH = 10;
    
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
    private final boolean invertDriveStick = true;

	private final Joystick driveStick = new Joystick(0);
	private final Joystick armStick = new Joystick(1);
	
	public static final String ARM_PRESET_EXTENDED = "EXTENDED";
	public static final String ARM_PRESET_TRANSPORT = "TRANSPORT";
	public static final String ARM_PRESET_MAX_STACK = "MAX_STACK";

	//driver stick functions
    Button forkToggle = new JoystickButton(driveStick, BUTTON_RB);
    Button armPerspective = new JoystickButton(driveStick, BUTTON_Y);
    Button stackPerspective = new JoystickButton(driveStick, BUTTON_A);
    
    
    //arm stick functions
    Button claw = new JoystickButton(armStick, BUTTON_RB);
    Button shoulderButton = new JoystickButton(armStick, BUTTON_X);
    Button elbowButton = new JoystickButton(armStick, BUTTON_Y);
    Button wristButton = new JoystickButton(armStick, BUTTON_B);
    Button armIdleButton = new JoystickButton(armStick, BUTTON_A);
    Button presetButton = new JoystickButton(armStick, BUTTON_LB);
    Button manualOverride = new JoystickButton(armStick, LEFT_JOYSTICK);
    Button toggleWristAutoManu = new JoystickButton(armStick, ARM_AXIS_R_PUSH);

    
	public OI(){
			//drive stick command assignments
			forkToggle.whenPressed(new ToggleForks());
			armPerspective.whileHeld(new SetArmPerspective());
			stackPerspective.whileHeld(new SetStackerPerspective());

			//arm stick command assignments
			elbowButton.whenPressed(new MoveElbowCommand());
			shoulderButton.whenPressed(new MoveShoulderCommand());
			wristButton.whenPressed(new MoveWristCommand());
			manualOverride.whileHeld(new ExtendedPresetCommand());
			armIdleButton.whileHeld(new TransportPresetCommand());
			presetButton.whileHeld(new MaxStackPresetCommand());
			claw.toggleWhenPressed(new ToggleClaw());
			toggleWristAutoManu.whenPressed(new ToggleWristAutoManuCommand());
			
			

	}
	
	public static final int ToggleSmoothing() {
		return 0;
	}

	//getAxis functions for drivestick
	public double getDriveXL(){     
        //SmartDashboard.putNumber("XL", driveStick.getRawAxis(AXIS_XL));
        return driveStick.getRawAxis(AXIS_XL);
	}
	
	public double getDriveX(){     
        //SmartDashboard.putNumber("X", driveStick.getRawAxis(AXIS_X));
        return driveStick.getRawAxis(AXIS_X);
    }
	
    public double getDriveY(){
        SmartDashboard.putNumber("Y", driveStick.getRawAxis(AXIS_Y));
        if (invertDriveStick)
        {
        	return driveStick.getRawAxis(AXIS_Y) * -1;
        }
        else
        {
        	return driveStick.getRawAxis(AXIS_Y);
        }
    }
    

    
//    public double getShoulderYL(){
//    	SmartDashboard.putNumber("Shoulder YL", armStick.getRawAxis(ARM_AXIS_YL));
//    	if (invertArmStick)
//        {
//    		return armStick.getRawAxis(ARM_AXIS_YL) * -1;
//        }
//    	else
//    	{
//    		return armStick.getRawAxis(ARM_AXIS_YL);
//    	}
//    }
    public double getWristXR(){
    	SmartDashboard.putNumber("Wrist XR", armStick.getRawAxis(ARM_AXIS_XR));
    	if (invertArmStick)
        {
    		return armStick.getRawAxis(ARM_AXIS_XR) * -1;
        }
    	else
    	{
    		return armStick.getRawAxis(ARM_AXIS_XR);
    	}
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
    	//SmartDashboard.putNumber("Arm YR", armStick.getRawAxis(ARM_AXIS_YR));
    	if (invertArmStick)
        {
    		return armStick.getRawAxis(ARM_AXIS_YR) * -1;
        }
    	else
    	{
    		return armStick.getRawAxis(ARM_AXIS_YR);
    	}
    }
    
//    public double getArmRightY(){
//    	SmartDashboard.putNumber("Arm Right Y", armStick.getRawAxis(ARM_AXIS_YR));
//    	if (invertArmStick)
//        {
//    		return armStick.getRawAxis(ARM_AXIS_YR) * -1;
//        }
//    	else
//    	{
//    		return armStick.getRawAxis(ARM_AXIS_YR);
//    	}
//    }
//    
//    public double getArmRightX(){
//    	SmartDashboard.putNumber("Arm Right X", armStick.getRawAxis(ARM_AXIS_XR));
//    	if (invertArmStick)
//        {
//    		return armStick.getRawAxis(ARM_AXIS_XR) * -1;
//        }
//    	else
//    	{
//    		return armStick.getRawAxis(ARM_AXIS_XR);
//    	}
//    }

    public double getStackerRX(){
        //SmartDashboard.putNumber("Stacker YR", driveStick.getRawAxis(STACKER_AXIS_YL));
        //return armStick.getRawAxis(STACKER_AXIS_YL);
    	return driveStick.getRawAxis(5);
    }
	
    public double getStackerZ(){
        //SmartDashboard.putNumber("Stacker YR", driveStick.getRawAxis(STACKER_AXIS_YL));
        //return armStick.getRawAxis(STACKER_AXIS_YL);
    	return driveStick.getRawAxis(4);
    }
    
    public double getDriveAxis(int axis){
    	return driveStick.getRawAxis(axis);    	
    }
    
    
    


}
