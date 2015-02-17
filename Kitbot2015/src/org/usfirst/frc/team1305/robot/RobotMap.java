package org.usfirst.frc.team1305.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	//Axis and button layouts

	public static final int AXIS_DRIVETRAIN_MOVE = OI.AXIS_Y;
	public static final int AXIS_DRIVETRAIN_ROTATE = OI.AXIS_X;

	
	//Motor PWM
	public static final int PWM_CAMERA_SERVO = 0;
	
	//Analog Ports
	public static final int ANALOG_GYRO = 0;
	
	public static final int ANALOG_POT_WRIST = 3;
	public static final int ANALOG_POT_ELBOW = 2;
	public static final int ANALOG_POT_SHOULDER = 1;
	
	//right & left fork CAN motors need to be removed - replaced by solenoid
	public static final int CAN_DEVICE_RIGHT_FORK = 4;
	public static final int CAN_DEVICE_LEFT_FORK = 3;
	
	public static final int SOL_FORK = 0;
	public static final int SOL_CLAW = 1;
	
		
	public static final int CAN_DEVICE_LIFT = 5;
	public static final int CAN_DEVICE_WRIST = 6;
	public static final int CAN_DEVICE_SHOULDER = 7;
	public static final int CAN_DEVICE_ELBOW = 8;
	

	public static final int CAN_DEVICE_DRIVE_L1 = 1;
	public static final int CAN_DEVICE_DRIVE_L2 = 2;
	public static final int CAN_DEVICE_DRIVE_R1 = 9;
	public static final int CAN_DEVICE_DRIVE_R2 = 10;
}
