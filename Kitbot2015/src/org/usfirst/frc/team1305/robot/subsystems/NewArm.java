package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * New arm class which uses proper PID controllers to control the joints.
 */
public class NewArm extends Subsystem {
	//Max and min extents for joints.
	private final double SHOULDER_MIN = 0.0;
	private final double SHOULDER_MAX = 0.0;
	private final double ELBOW_MIN    = 0.0;
	private final double ELBOW_MAX    = 0.0;
	private final double WRIST_MIN    = 0.0;
	private final double WRIST_MAX    = 0.0;
	//PID Constants
	private final double P_s = 0.0;
	private final double I_s = 0.0;
	private final double D_s = 0.0;
	
	private final double P_e = 0.0;
	private final double I_e = 0.0;
	private final double D_e = 0.0;
	
	private final double P_w = 0.0;
	private final double I_w = 0.0;
	private final double D_w = 0.0;
	
	//pot-reading to angle constant values
	//for best accuracy these values should be as far apart as possible.
	private final double SHOULDER_ANGLE1   = 0.0;
	private final double SHOULDER_READING1 = 0.0;
	private final double SHOULDER_ANGLE2   = 0.0;
	private final double SHOULDER_READING2 = 0.0;

	private final double ELBOW_ANGLE1  	   = 0.0;
	private final double ELBOW_READING1	   = 0.0;
	private final double ELBOW_ANGLE2  	   = 0.0;
	private final double ELBOW_READING2	   = 0.0;

	private final double WRIST_ANGLE1 	   = 0.0;
	private final double WRIST_READING1	   = 0.0;
	private final double WRIST_ANGLE2  	   = 0.0;
	private final double WRIST_READING2	   = 0.0;

	
	//motors, sensors, and PID objects
	private AnalogInput pot_s = new AnalogInput(RobotMap.ANALOG_POT_SHOULDER);
	private AnalogInput pot_e = new AnalogInput(RobotMap.ANALOG_POT_ELBOW);
	private AnalogInput pot_w = new AnalogInput(RobotMap.ANALOG_POT_WRIST);
	
	private CANTalon motor_s  = new CANTalon(RobotMap.CAN_DEVICE_SHOULDER);
	private CANTalon motor_e  = new CANTalon(RobotMap.CAN_DEVICE_ELBOW);
	private CANTalon motor_w  = new CANTalon(RobotMap.CAN_DEVICE_WRIST);
	
	private PIDController pid_s = new PIDController(P_s, I_s, D_s, pot_s, motor_s);
	private PIDController pid_e = new PIDController(P_e, I_e, D_e, pot_e, motor_e);
	private PIDController pid_w = new PIDController(P_w, I_w, D_w, pot_w, motor_w);
	
	//computed m and b constants for the pivots
	private double m_s;
	private double b_s;
	private double m_e;
	private double b_e;
	private double m_w;
	private double b_w;
	
	private boolean isAutoWrist = false;
	private boolean isPresetActive = false;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Set the state of the auto-wrist leveller
     * @param state true if auto-wrist is to be enabled. False if disabled.
     */
    public void setAutoWrist(boolean state){
    	this.isAutoWrist = state;
    }
    
    /**
     * Get the state of the auto-wrist leveller
     * @return true if the auto wrist leveller is active, false if disabled.
     */
    public boolean getAutoWrist(){
    	return this.isAutoWrist;
    }
    
    /**
     * Return the preset-state of the arm.
     * @return true if a preset is being applied, false if
     * arm is under maual control.
     */
    public boolean getPresetState(){
    	return this.isPresetActive;
    }
    
}

