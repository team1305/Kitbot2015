package org.usfirst.frc.team1305.robot;

import org.usfirst.frc.team1305.robot.commands.EJSmasher.SmasherAutoRetract;
import org.usfirst.frc.team1305.robot.commands.EJSmasher.SmasherManualDeploy;
import org.usfirst.frc.team1305.robot.commands.EJSmasher.SmasherManualRetract;
import org.usfirst.frc.team1305.robot.commands.arm.ArmGoPreset;
import org.usfirst.frc.team1305.robot.commands.arm.ArmToggleAutoWrist;
import org.usfirst.frc.team1305.robot.commands.claw.ClawToggle;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveSetArmPerspective;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveSetStackerPerspective;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveToggleGear;
import org.usfirst.frc.team1305.robot.subsystems.Arm.Preset;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	// xbox axis reference

    //Attack 3 axis reference
    public static class ATTACK3{
    	private ATTACK3(){
    		return; // prevent instances with private constructor
    	}
    	//on the attack 3, buttons are labelled with their number.
		public static final int AXIS_X = 0;
		public static final int AXIS_Y = 1;
    }
    //F310 Button reference
	public static class F310{
		private F310(){
			return; // prevent instances with private contstructor
		}
	    public static final int BUTTON_A = 1;
	    public static final int BUTTON_B = 2;
	    public static final int BUTTON_X = 3;
	    public static final int BUTTON_Y = 4;
	    public static final int BUTTON_LB = 5;
	    public static final int BUTTON_RB = 6;
	    public static final int LEFT_JOYSTICK_CLICK = 9;
	    public static final int RIGHT_JOYSTICK_CLICK = 10;
	    
		public static final int AXIS_XL = 0;
		public static final int AXIS_YL = 1;
		public static final int AXIS_XR = 4;
	    public static final int AXIS_YR = 5;
	}

    //axis definitions for the arm 
    public static final int ARM_SHOULDER_AXIS = F310.AXIS_YL;
    public static final int ARM_ELBOW_AXIS = F310.AXIS_YR;
    public static final int ARM_WRIST_AXIS = F310.AXIS_XR;
    
    //axis definitions for driving
    public static final int DRIVE_MOVEMENT_AXIS = ATTACK3.AXIS_Y;
    public static final int DRIVE_STEER_AXIS = ATTACK3.AXIS_X;
    
    private final boolean invertArmStick = true;
    private final boolean invertDriveStick = true;

	private final Joystick driveStick = new Joystick(0);
	private final Joystick armStick = new Joystick(1);

	//driver stick functions
    Button forkOpenClose;
    Button armPerspective;
    Button stackPerspective;
    Button toggleGear;
    Button forkDeployment;
    Button stackerMoveUp;
    Button stackerMoveDown;

    //arm stick functions
    Button clawOpenClose;
    Button toggleWristAutoManu;

    Button transportPresetButton;
    Button maxStackPresetButton;
    Button extendedPresetButton;


    /**
     * Polls drivestation for # of axis, since Attack 3 Joysticks only have
     * 3 axis the code will automatically switch to controls for that joystick.
     * Otherwise, defaults to F310 joystick controls.  Arm controls remain
     * same throughout.
     */
	public OI(){
	    armPerspective   = new JoystickButton(driveStick, 7);
	    stackPerspective = new JoystickButton(driveStick, 6);
	    toggleGear       = new JoystickButton(driveStick, 5);
	    forkDeployment   = new JoystickButton(driveStick, 4);
	    stackerMoveDown  = new JoystickButton(driveStick, 3);
	    stackerMoveUp    = new JoystickButton(driveStick, 2);
	    forkOpenClose    = new JoystickButton(driveStick, 1);

		armPerspective.whileHeld  (new DriveSetArmPerspective());
		stackPerspective.whileHeld(new DriveSetStackerPerspective());
		toggleGear.whenPressed    (new DriveToggleGear());
		stackerMoveDown.whileHeld(new SmasherManualDeploy());
		stackerMoveUp.whileHeld(new SmasherManualRetract());
		forkDeployment.whenPressed(new SmasherAutoRetract());
		


	    //arm stick functions
	    clawOpenClose         = new JoystickButton(armStick, F310.BUTTON_RB);
	    toggleWristAutoManu   = new JoystickButton(armStick, F310.RIGHT_JOYSTICK_CLICK);
	    transportPresetButton = new JoystickButton(armStick, F310.BUTTON_Y);
	    maxStackPresetButton  = new JoystickButton(armStick, F310.BUTTON_X);
	    extendedPresetButton  = new JoystickButton(armStick, F310.BUTTON_B);

		clawOpenClose.whenPressed      (new ClawToggle());
		toggleWristAutoManu.whenPressed(new ArmToggleAutoWrist());
		extendedPresetButton.whileHeld (new ArmGoPreset(Preset.PRESET_EXTENDED));
		transportPresetButton.whileHeld(new ArmGoPreset(Preset.PRESET_TRANSPORT));
		maxStackPresetButton.whileHeld (new ArmGoPreset(Preset.PRESET_MAXSTACK));
	}
	
	//axis functions for the arm
	public double getShoulderAxis(){
		return armStick.getRawAxis(ARM_SHOULDER_AXIS);
	}
	
	public double getElbowAxis(){
		return armStick.getRawAxis(ARM_ELBOW_AXIS);
	}
	
	public double getWristAxis(){
		return armStick.getRawAxis(ARM_WRIST_AXIS);
	}
	//axis functions for driving
	
	public double getMoveAxis(){
		return driveStick.getRawAxis(DRIVE_MOVEMENT_AXIS);
	}
	
	public double getSteerAxis(){
		return driveStick.getRawAxis(DRIVE_STEER_AXIS);
	}
	
	//general utility.
    public double getDriveAxis(int axis){
    	return driveStick.getRawAxis(axis);
    }
    public double getArmAxis(int axis){
    	return armStick.getRawAxis(axis);
    }

}
