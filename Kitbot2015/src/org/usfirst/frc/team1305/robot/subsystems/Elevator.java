package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.OI;
import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.elevator.ManualElevator;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Stacker subsystem that controls both the height adjustment and the forks.
 * The string pot for the BK stacker needs to be plugged in to the stackerTalon
 */
public class Elevator extends Subsystem {
    //constants
	//TODO: find values for these
	private final double BASE_HEIGHT = 0.0;
	private final double SCALING_VALUE = 0.0;

	private final double STACKER_P = 0.0;
	private final double STACKER_I = 0.0;
	private final double STACKER_D = 0.0;

	private double ELEVATOR_MOTOR_UP_SPEED = 1;
	private double ELEVATOR_MOTOR_DOWN_SPEED = 1;

	public static final double ELEVATOR_PRESET_TOLERANCE = 3.00; //percent

	//preset values for the stacker, in ascending order
	private final double[] PRESETS = {0.0, 1.0, 2.0, 3.0};
	//preset index. A value of -1 means we're not in a preset mode.
	private int presetIndex = -1;


	//talon objects
	private CANTalon stackerTalon = new CANTalon(RobotMap.CAN_DEVICE_LIFT);


    protected void initialize() {
    	//we use an analog potientiometer to control the stacker height.
    	stackerTalon.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
    	//talon defaults to position control mode.
    	stackerTalon.changeControlMode(CANTalon.ControlMode.Position);

    	//set so we don't move initially.
    	//we shouldn't get the error
    	setHeight(getHeight());

    	stackerTalon.setP(STACKER_P);
    	stackerTalon.setI(STACKER_I);
    	stackerTalon.setD(STACKER_D);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ManualElevator());
    }

    /**
     * Move the arm manually using an axis value from [-1, 1]
     *
     * Only works in Percentage Mode, gets value from ManualElevator
     * @param axis movement value form [-1, 1]
     */
    public void manualHeight(double axis){
    	if((stackerTalon.getControlMode().equals(CANTalon.ControlMode.PercentVbus)
    			&& OI.usingAttack3 == false)){
    		stackerTalon.set(axis);
    	}
    }

    /**
     * Get the height of the BK-Stacker forks from the ground.
     * @return height of stacker forks in inches from the ground
     */
    public double getHeight(){
    	//value is in range [0, 1023]
    	int value = stackerTalon.getAnalogInRaw();
    	return BASE_HEIGHT + value * SCALING_VALUE;
    }
    /**
     * Set the desired height of the stacker
     * Only works in Position mode.
     * @param height the desired height of the stacker in inches from the ground.
     */
    public void setHeight(double height){
    	int value = (int)( (height - BASE_HEIGHT)/(SCALING_VALUE) );
    	//only set if we're in position control mode
    	if(stackerTalon.getControlMode().equals(CANTalon.ControlMode.Position)){
    		stackerTalon.set(value);
    	}
    }
    /**
     * Change the mode of the stacker talon
     * @param positionMode If true, use PID-positional mode. If false, use raw pecentage mode.
     */
    public void setMode(boolean positionMode){
    	if(positionMode){
    		stackerTalon.changeControlMode(CANTalon.ControlMode.Position);
    		presetIndex = -1;
    	}
    	else{
    		stackerTalon.changeControlMode(CANTalon.ControlMode.PercentVbus);
    		presetIndex = -1;
    		stackerTalon.set(0.0);
    	}
    }
    /**
     * Return the current state of the stacker talon.
     * @return True if the stacker is in position mode, false if the stacker is in raw percentage mode.
     */
    public boolean getMode(){
    	if(stackerTalon.getControlMode().equals(CANTalon.ControlMode.Position)){
    		return true;
    	}
    	else return false;
    }
    /**
     * Set the stacker to a specified preset
     * This routine will only have an effect if the talon is in position mode.
     * @param preset The index of the preset to go to. Must be in range [0, PRESETS.length]
     */
    public void setPreset(int preset){
    	//do nothing if we're in percent mode
    	if(getMode() == false) return;
    	if(preset < 0 || preset >= PRESETS.length) return;
    	//dereference the preset height and send to the talon
		setHeight(PRESETS[preset]);
    }
    public int getPreset(){
    	return presetIndex;
    }
    /**
     * Returns the desired height of the current elevator preset
     * @return desired height in inches or NaN if preset is not set.
     */
    public double getPresetValue(){
    	if(presetIndex < 0 || presetIndex >= PRESETS.length){
    		return Double.NaN;
    	}
    	else{
    		return PRESETS[presetIndex];
    	}
    }

    /**
     * Elevator goes up at set speed, for attack 3 controls.
     */
    public void elevatorUp(){
    	stackerTalon.set(ELEVATOR_MOTOR_UP_SPEED);
    }

    /**
     * Elevator goes down at a set speed, for attack 3 controls.
     */
    public void elevatorDown(){
    	stackerTalon.set(-ELEVATOR_MOTOR_DOWN_SPEED);
    }



}

