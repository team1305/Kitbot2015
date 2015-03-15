package org.usfirst.frc.team1305.robot;

import org.usfirst.frc.team1305.robot.commands.arm.ExtendedPresetCommand;
import org.usfirst.frc.team1305.robot.commands.arm.MaxStackPresetCommand;
import org.usfirst.frc.team1305.robot.commands.arm.ToggleWristAutoManuCommand;
import org.usfirst.frc.team1305.robot.commands.arm.TransportPresetCommand;
import org.usfirst.frc.team1305.robot.commands.claw.ToggleClaw;
import org.usfirst.frc.team1305.robot.commands.drivetrain.SetArmPerspective;
import org.usfirst.frc.team1305.robot.commands.drivetrain.SetStackerPerspective;
import org.usfirst.frc.team1305.robot.commands.drivetrain.ToggleGear;
import org.usfirst.frc.team1305.robot.commands.elevator.ElevatorDown;
import org.usfirst.frc.team1305.robot.commands.elevator.ElevatorUp;
import org.usfirst.frc.team1305.robot.commands.forks.ToggleForks;
import org.usfirst.frc.team1305.robot.commands.forks.ToggleStackerDeployment;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	// xbox axis reference
	public static final int AXIS_XL = 0;
	public static final int AXIS_YL = 1;
	public static final int AXIS_XR = 2;
    public static final int AXIS_YR = 3;
    //Attack 3 axis reference
	public static final int AXIS_X = 0;
	public static final int AXIS_Y = 1;
    //F310 Button reference
    public static final int BUTTON_A = 1;
    public static final int BUTTON_B = 2;
    public static final int BUTTON_X = 3;
    public static final int BUTTON_Y = 4;
    public static final int BUTTON_LB = 5;
    public static final int BUTTON_RB = 6;
    public static final int LEFT_JOYSTICK_CLICK = 9;
    public static final int RIGHT_JOYSTICK_CLICK = 10;

    private final boolean invertArmStick = true;
    private final boolean invertDriveStick = true;

	private final Joystick driveStick = new Joystick(0);
	private final Joystick armStick = new Joystick(1);

	public static boolean usingAttack3;


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
    Button presetButton;
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
//		if(DriverStation.getInstance().getStickAxisCount(0) == 3){ //if attack 3 joystick
		    armPerspective   = new JoystickButton(driveStick, 7);
		    stackPerspective = new JoystickButton(driveStick, 6);
		    toggleGear       = new JoystickButton(driveStick, 5);
		    forkDeployment   = new JoystickButton(driveStick, 4);
		    stackerMoveDown  = new JoystickButton(driveStick, 3);
		    stackerMoveUp    = new JoystickButton(driveStick, 2);
		    forkOpenClose    = new JoystickButton(driveStick, 1);

			forkDeployment.whenPressed(new ToggleStackerDeployment());
			armPerspective.whileHeld  (new SetArmPerspective());
			stackPerspective.whileHeld(new SetStackerPerspective());
			toggleGear.whenPressed    (new ToggleGear());
			stackerMoveDown.whileHeld (new ElevatorDown());
			stackerMoveUp.whileHeld   (new ElevatorUp());
			forkOpenClose.whenPressed (new ToggleForks());

//			usingAttack3 = true;
//
//		}else{
//			//driver stick functions
//			forkOpenClose    = new JoystickButton(driveStick, BUTTON_RB);
//		    armPerspective   = new JoystickButton(driveStick, BUTTON_Y);
//		    stackPerspective = new JoystickButton(driveStick, BUTTON_A);
//		    toggleGear       = new JoystickButton(driveStick, LEFT_JOYSTICK_CLICK);
//		    forkDeployment   = new JoystickButton(driveStick, BUTTON_LB);
//
//		    forkOpenClose.whenPressed (new ToggleForks());
//		    forkDeployment.whenPressed(new ToggleStackerDeployment());
//			armPerspective.whileHeld  (new SetArmPerspective());
//			stackPerspective.whileHeld(new SetStackerPerspective());
//			toggleGear.whenPressed    (new ToggleGear());
//
//			usingAttack3 = false;
//		}



		    //arm stick functions
		    Button claw                  = new JoystickButton(armStick, BUTTON_RB);
		    Button toggleWristAutoManu   = new JoystickButton(armStick, RIGHT_JOYSTICK_CLICK);
		    Button transportPresetButton = new JoystickButton(armStick, BUTTON_Y);
		    Button maxStackPresetButton  = new JoystickButton(armStick, BUTTON_X);
		    Button extendedPresetButton  = new JoystickButton(armStick, BUTTON_B);

			extendedPresetButton.whileHeld (new ExtendedPresetCommand());
			transportPresetButton.whileHeld(new TransportPresetCommand());
			maxStackPresetButton.whileHeld (new MaxStackPresetCommand());
			claw.toggleWhenPressed         (new ToggleClaw());
			toggleWristAutoManu.whenPressed(new ToggleWristAutoManuCommand());



	}

	//getAxis functions for drivestick
	public double getDriveXL(){
        return driveStick.getRawAxis(AXIS_XL);
	}

	public double getDriveX(){
        return driveStick.getRawAxis(AXIS_X);
    }

    public double getDriveY(){
        if (invertDriveStick)
        {
        	return driveStick.getRawAxis(AXIS_Y) * -1;
        }
        else
        {
        	return driveStick.getRawAxis(AXIS_Y);
        }
    }

    public double getWristXR(){
    	if (invertArmStick)
        {
    		return armStick.getRawAxis(4) * -1;
        }
    	else
    	{
    		return armStick.getRawAxis(4);
    	}
    }

    public double getShoulderYL(){
    	if (invertArmStick)
        {
    		return armStick.getRawAxis(1) * -1;
        }
    	else
    	{
    		return armStick.getRawAxis(1);
    	}
    }

    public double getElbowYR(){
    	if (invertArmStick)
        {
    		return armStick.getRawAxis(5) * -1;
        }
    	else
    	{
    		return armStick.getRawAxis(5);
    	}
    }

    public double getArmYR(){
    	if (invertArmStick)
        {
    		return armStick.getRawAxis(5) * -1;
        }
    	else
    	{
    		return armStick.getRawAxis(5);
    	}
    }

    public double getStackerRX(){
    	return driveStick.getRawAxis(5);
    }

    public double getStackerZ(){
    	return driveStick.getRawAxis(4);
    }

    public double getDriveAxis(int axis){
    	return driveStick.getRawAxis(axis);
    }

}
