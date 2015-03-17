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
	
	/*
	 * ======== POT READINGS AND ANGLES ========
	 * These need to be updated whenever the geometry of the arm changes
	 * If they aren't, then you'll have a bad time.
	 */
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
	//==========================================
	
	//Motor directions. 1 if +motor implies +pot, false otherwise.
	private final int WRIST_MOTORDIR    = 1;
	private final int ELBOW_MOTORDIR    = 1;
	private final int SHOULDER_MOTORDIR = 1;

	
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
	//computation of values done in constructor.
	private double m_s;
	private double b_s;
	private double m_e;
	private double b_e;
	private double m_w;
	private double b_w;
	
	//internal state
	private boolean isAutoWrist = false;
	private boolean isPresetActive = false;
	
	public NewArm(){
		//compute the m's and b's
		m_s = (SHOULDER_ANGLE2 - SHOULDER_ANGLE1) / (SHOULDER_READING2 - SHOULDER_READING1);
		b_s = SHOULDER_ANGLE2 - m_s * SHOULDER_READING2;
		m_e = (ELBOW_ANGLE2 - ELBOW_ANGLE1) / (ELBOW_READING2 - ELBOW_READING1);
		b_e = ELBOW_ANGLE2 - m_e * ELBOW_READING2;
		m_w = (WRIST_ANGLE2 - WRIST_ANGLE1) / (WRIST_READING2 - WRIST_READING1);
		b_w = WRIST_ANGLE2 - m_w * WRIST_READING2;

	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Set the state of the auto-wrist leveller
     * Handles turning on the PID
     * @param state true if auto-wrist is to be enabled. False if disabled.
     */
    public void setAutoWrist(boolean state){
    	this.isAutoWrist = state;
    	if(state == true){
    		pid_w.enable();
    		pid_w.setSetpoint(computeAutoWristPotValue());
    	}
    	else{
    		pid_w.disable();
    	}
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
    
    /**
     * Compute the angle of the shoulder joint given it's pot value.
     * 
     * @param potvalue the value of the potientiometer
     * @return the computed angle in degrees, acute angle measured 
     * from back of robot
     */
    private double shoulder_pot2angle(double potvalue){
    	return m_s * potvalue + b_s;
    }
    
    /**
     * Convert an angle to a string pot reading for the shoulder joint
     * 
     * @param angle the angle of the shoulder joint, measured from the back
     * horizontal, acute angle
     * @return The compted pot value corresponding to this angle for the shoulder.
     */
    private double shoulder_angle2pot(double angle){
    	return (angle - b_s) / m_s;
    }
    
    /**
     * Compute the bottom-angle of the elbow given a pot reading
     * 
     * @param potvalue the value of the string pot
     * @return the computed angle, in degrees, of the bottom of the elbow pivot.
     */
    private double elbow_pot2angle(double potvalue){
    	return m_e * potvalue + b_e;
    }
    
    /**
     * Comvert an angle to the corresponding pot reading for the elbow
     * 
     * @param angle the angle to compute, in degrees
     * @return the computed elbow pot value
     */
    private double elbow_angle2pot(double angle){
    	return (angle - b_e) / m_e;
    }
    
    /**
     * Compute the angle of the wrist, given the pot value.
     * 
     * @param potvalue the value of the pot
     * @return the angle at the top of the wrist pivot, in degrees.
     */
    private double wrist_pot2angle(double potvalue){
    	return m_w * potvalue + b_w;
    }
    
    /**
     * Convert wrist angle to pot reading
     * 
     * @param angle angle in degrees of the top of the wrist pivot.
     * @return the wrist pot value.
     */
    private double wrist_angle2pot(double angle){
    	return (angle - b_w) / m_w;
    }
    
    /**
     * Compute the angle required at the wrist 
     * 
     * @return the wrist pot value required to hold the wrist horizontal.
     */
    public double computeAutoWristPotValue(){
    	//by trigonometry, we have that theta_w = theta_s + theta_e
    	double theta_s = shoulder_pot2angle(pot_s.getValue());
    	double theta_e = elbow_pot2angle(pot_e.getValue());
    	return wrist_angle2pot(theta_s + theta_e);
    }
    
    /**
     * handles updating the automatic wrist value. Needs to be called in both the manual 
     * movement commands as well as setpoint commands.
     */
    public void wristUpdate(){
    	pid_w.setSetpoint(computeAutoWristPotValue());
    }
    
    public void shoulder_manual(double axisval){
    	//make sure we don't go past limits ever
    	if((axisval > 0 && pot_s.getValue() > SHOULDER_MAX) ||
    	   (axisval < 0 && pot_s.getValue() < SHOULDER_MIN)){
    		motor_s.set(0.0);
    	}
    	else{
    		motor_s.set(SHOULDER_MOTORDIR * axisval);
    	}
    }

}

