package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;

import com.sun.javafx.css.CalculatedValue;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * New arm class which uses proper PID controllers to control the joints.
 */
public class Arm extends Subsystem {
	//Represents the possible states of the arm subsystem
	public static enum ArmMode{
		automaticWrist,
		manualWrist,
		preset
	}
	//represents the three joints and the virtual joint for getAngle and getRaw
	public static enum Joint{
		shoulder,
		elbow,
		wrist,
		calculatedWrist
	}
	//class representing a single preset.
	public static class Preset{
		public final double shoulderAngle;
		public final double elbowAngle;
		public final String name;
		public Preset(double shoulderAngle, double elbowAngle, String name){
			this.shoulderAngle = shoulderAngle;
			this.elbowAngle = elbowAngle;
			this.name = name;
		}
	
	
		/*
		 * ====================================================================
		 * ==== Arm Presets go here ===========================================
		 * ====================================================================
		 * Arm presets are specified by their desired angles for both the 
		 * shoulder and the elbow in terms of degrees, meeasured in the usual
		 * way. 
		 * 
		 * Therefore, shoulder angle is measured to be the usually acute angle 
		 * b/n the back of the robot and the underside of the first beam.
		 * 
		 * The elbow angle is the bottom angle, measured between the first and 
		 * second beams.
		 */
		public static final Preset PRESET_TRANSPORT = new Preset(0, 0, "Transport");
		public static final Preset PRESET_EXTENDED  = new Preset(0, 0, "Extended");
		public static final Preset PRESET_MAXSTACK  = new Preset(0, 0, "Max Stack");
		//=====================================================================
	}
	
	//Max and min extents for joints: potientiometer readings.
	private final double SHOULDER_MIN = 0.107;
	private final double SHOULDER_MAX = 0.48;
	private final double ELBOW_MIN    = 0.317;
	private final double ELBOW_MAX    = 0.637;
	private final double WRIST_MIN    = 0.169;
	private final double WRIST_MAX    = 0.39;
	//PID Constants
	private final double P_s = 24.0;
	private final double I_s = 0.0;
	private final double D_s = 0.0;
	
	private final double P_e = 24.0;
	private final double I_e = 0.0;
	private final double D_e = 0.0;
	
	private final double P_w = 24.0;
	private final double I_w = 0.0;
	private final double D_w = 0.0;
	
	/*
	 * ========================================================================
	 * ======== POT READINGS AND ANGLES =======================================
	 * ========================================================================
	 * These need to be updated whenever the geometry of the arm changes
	 * If they aren't, then you're gonna have a bad time.
	 * The Pot readings should be the values obtained with pot.pidGet().
	 */
	private final double SHOULDER_ANGLE1   = 34;
	private final double SHOULDER_READING1 = 0.107;
	private final double SHOULDER_ANGLE2   = 98;
	private final double SHOULDER_READING2 = 0.496;

	private final double ELBOW_ANGLE1  	   = 135;
	private final double ELBOW_READING1	   = 0.317;
	private final double ELBOW_ANGLE2  	   = 23;
	private final double ELBOW_READING2	   = 0.637;

	private final double WRIST_ANGLE1 	   = 116;
	private final double WRIST_READING1	   = 0.169;
	private final double WRIST_ANGLE2  	   = 238;
	private final double WRIST_READING2	   = 0.391;
	//=========================================================================
	
	//Motor directions. 1 if +motor => +pot, false if +mot => -pot
	private final int WRIST_MOTORDIR    = 1;
	private final int ELBOW_MOTORDIR    = 1;
	private final int SHOULDER_MOTORDIR = 1;
	
	//Arm geometry constants
	//lengths of the arm segments
	//All units are in FEET.
	public final double GEO_LINK1_LENGTH = 0.0;
	public final double GEO_LINK2_LENGTH = 0.0;
	//horizontal distance from the front of the robot to the shoulder pivot
	public final double GEO_X0 = 0.0;
	//vertical distance from the floor to the shoulder pivot.
	public final double GEO_Y0 = 0.0;
	
	
	//motors, sensors, and PID objects
	private AnalogPotentiometer pot_s = new AnalogPotentiometer(RobotMap.ANALOG_POT_SHOULDER);
	private AnalogPotentiometer pot_e = new AnalogPotentiometer(RobotMap.ANALOG_POT_ELBOW);
	private AnalogPotentiometer pot_w = new AnalogPotentiometer(RobotMap.ANALOG_POT_WRIST);
	
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
	private ArmMode mode = ArmMode.manualWrist;
	private Preset preset = new Preset(shoulder_pot2angle(pot_s.pidGet()),
			                           elbow_pot2angle(pot_e.pidGet()),
			                           "Null");
		
	public Arm(){
		//compute the m's and b's
		m_s = (SHOULDER_ANGLE2 - SHOULDER_ANGLE1) / (SHOULDER_READING2 - SHOULDER_READING1);
		b_s = SHOULDER_ANGLE2 - m_s * SHOULDER_READING2;
		m_e = (ELBOW_ANGLE2 - ELBOW_ANGLE1) / (ELBOW_READING2 - ELBOW_READING1);
		b_e = ELBOW_ANGLE2 - m_e * ELBOW_READING2;
		m_w = (WRIST_ANGLE2 - WRIST_ANGLE1) / (WRIST_READING2 - WRIST_READING1);
		b_w = WRIST_ANGLE2 - m_w * WRIST_READING2;
		
		//set up parameters for the pids
		pid_s.setInputRange(SHOULDER_MIN, SHOULDER_MAX);
		pid_e.setInputRange(ELBOW_MIN, ELBOW_MAX);
		pid_w.setInputRange(WRIST_MIN, WRIST_MAX);
		
		//Make sure the motors spin in the right direction.
		pid_s.setPID(P_s * SHOULDER_MOTORDIR,
					 I_s * SHOULDER_MOTORDIR,
					 D_s * SHOULDER_MOTORDIR);
		
		pid_e.setPID(P_e * ELBOW_MOTORDIR,
					 I_e * ELBOW_MOTORDIR,
					 D_e * ELBOW_MOTORDIR);
		
		pid_w.setPID(P_w * WRIST_MOTORDIR,
				 	 I_w * WRIST_MOTORDIR,
				 	 D_w * WRIST_MOTORDIR);
		
		//enable the proper pids.
		setMode(mode);

	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
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
     * Value returned is the pot value, not the angle.
     */
    public double computeAutoWristPotValue(){
    	//by trigonometry, we have that theta_w = theta_s + theta_e
    	double theta_s = shoulder_pot2angle(pot_s.pidGet());
    	double theta_e = elbow_pot2angle(pot_e.pidGet());
    	return wrist_angle2pot(theta_s + theta_e);
    }
    
    /**
     * Function which handles applying manual values to motors as well as 
     * keeping the wrist level in the preset and autowrist modes. 
     * 
     * This function should be called from all commands that use the arm
     * subsystem. 
     * 
     *  The function will act differently depending on which mode is activated:
     *  - If we are in automaticWrist mode, then the shoulder and elbow are set
     *  manually using an axis value. The target wrist value is calculated and
     *  applied to the PID controller.
     *  - If we are in manualWrist mode, then manual axis values are passed to 
     *  all three motors. 
     *  - If we are in preset mode, then the pre-delcared preset is applied to 
     *  the shoulder and elbow motors, and the desired wrist position is
     *  calculated and then applied.
     * 
     * @param shoulderAxis Value to apply to the shoulder motor. This value
     * is ignored when in preset mode.
     * @param elbowAxis Value to apply to the elbow motor. This value is 
     * ignored when in preset mode.
     * @param wristAxis Value to apply to the wrist motor. This value is ignored 
     * except when in manualWrist mode.
     */
    public void update(double shoulderAxis, double elbowAxis, double wristAxis){
    	//first, flip the values if necessary to line up with pot direction
    	shoulderAxis *= SHOULDER_MOTORDIR;
    	elbowAxis    *= ELBOW_MOTORDIR;
    	wristAxis    *= WRIST_MOTORDIR;
    	
    	//second, we must handle the cases where the joints are at the end of 
    	//their travels. 
    	if(shoulderAxis < 0 && pot_s.pidGet() < SHOULDER_MIN ) shoulderAxis = 0;
    	if(shoulderAxis > 0 && pot_s.pidGet() > SHOULDER_MAX ) shoulderAxis = 0;
    	if(elbowAxis    < 0 && pot_e.pidGet() < ELBOW_MIN)     elbowAxis    = 0;
    	if(elbowAxis    > 0 && pot_e.pidGet() > ELBOW_MAX)     elbowAxis    = 0;
    	if(wristAxis    < 0 && pot_w.pidGet() < ELBOW_MIN)     wristAxis    = 0;
    	if(wristAxis    > 0 && pot_w.pidGet() > ELBOW_MAX)     wristAxis    = 0;
    	
    	//now dispatch based on current mode.
    	switch(mode){  
		case automaticWrist:
			motor_s.set(shoulderAxis);
			motor_e.set(elbowAxis);
			pid_w.setSetpoint(computeAutoWristPotValue());
			break;
		case manualWrist:
			motor_s.set(shoulderAxis);
			motor_e.set(elbowAxis);
			motor_w.set(wristAxis);
			break;
		case preset:
			pid_s.setSetpoint(shoulder_angle2pot(preset.shoulderAngle));
			pid_e.setSetpoint(elbow_angle2pot(preset.elbowAngle));
			pid_w.setSetpoint(computeAutoWristPotValue());
			break;
    	}
    
    }
    
   /**
    * Set the mode of the arm subsystem.
    * @param mode The mode to set.
    */
    public void setMode(ArmMode mode){
    	stopMotors();
    	this.mode = mode;
    	switch(mode){
    	case automaticWrist:
    		pid_w.setSetpoint(pot_w.pidGet());
    		pid_w.enable();
    		pid_s.disable();
    		pid_e.disable();
    		break;
		case manualWrist:
			pid_w.disable();
			pid_s.disable();
			pid_e.disable();
			break;
		case preset:
			pid_w.setSetpoint(pot_w.pidGet());
			pid_s.setSetpoint(pot_s.pidGet());
			pid_e.setSetpoint(pot_e.pidGet());
			pid_w.enable();
			pid_s.enable();
			pid_e.enable();
			break;	
    	}
    }
    
    /**
     * Get the current mode of the arm.
     * @return the current mode of the arm.
     */
    public ArmMode getMode(){
    	return this.mode;
    }
    
    /**
     * Set the desired preset
     * @param p the preset to go to.
     */
    public void setPreset(Preset p){
    	this.preset = p;
    }
    
    /**
     * Get the selected preset.
     * @return the current preset.
     */
    public Preset getPreset(){
    	return this.preset;
    }
    
    /**
     * stop all motors
     */
    public void stopMotors(){
    	motor_s.set(0);
    	motor_e.set(0);
    	motor_w.set(0);
    }
    
    /**
     * Get the current position of the arm as a preset
     * @return A preset object representing the current position of hte arm.
     */
    public Preset getCurrentPosition(){
    	double s = shoulder_pot2angle(pot_s.get());
    	double e = elbow_pot2angle(pot_e.get());
    	return new Preset(s, e, "Current Position");
    }
    
    /**
     * Get the angle of the specified joint
     * @param j The joint to get
     * @return the angle in degrees.
     */
    public double getAngle(Joint j){
    	switch(j){
		case calculatedWrist:
			return wrist_pot2angle(computeAutoWristPotValue());
		case elbow:
			return elbow_pot2angle(pot_e.get());
		case shoulder:
			return shoulder_pot2angle(pot_s.get());
		case wrist:
			return wrist_pot2angle(pot_w.get());
		default:
			return Double.NaN;    	
    	}
    }
    
    /**
     * Get the raw potientiometer value for the specified joint
     * @param j the joint to get
     * @return The potientiometer value 
     */
    public double getRaw(Joint j){
    	switch(j){
		case calculatedWrist:
			return computeAutoWristPotValue();
		case elbow:
			return pot_e.get();
		case shoulder:
			return pot_s.get();
		case wrist:
			return pot_w.get();
		default:
			return Double.NaN;
    	}
    }
}
    
    
    


