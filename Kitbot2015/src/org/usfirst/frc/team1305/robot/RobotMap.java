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



	//Motor PWM
	public static final int PWM_CAMERA_SERVO = 0;

	//Analog Ports
	public static final int ANALOG_GYRO 		= 0;
	public static final int ANALOG_POT_SHOULDER = 1;
	public static final int ANALOG_POT_ELBOW    = 2;
	public static final int ANALOG_POT_WRIST    = 3;

	//Digital Ports
	public static final int DIO_LEFT_ENC_A 		= 0;
	public static final int DIO_LEFT_ENC_B 		= 1;
	public static final int DIO_RIGHT_ENC_A		= 2;
	public static final int DIO_RIGHT_ENC_B		= 3;
	public static final int DIO_SMASHER_WINCH_LIMIT = 8;
	public static final int DIO_CLAW_TRIGGER    = 9;
	public static final int DIO_SMASHER_TRIGGER = 7;
	public static final int DIO_LEFT_ENC_A 		= 0;
	public static final int DIO_LEFT_ENC_B 		= 1;
	public static final int DIO_RIGHT_ENC_A		= 2;
	public static final int DIO_RIGHT_ENC_B		= 3;

	//Solenoid ports
	public static final int SOL_CLAW    = 2;

	//CAN Devices Numbers
	public static final int CAN_DEVICE_SMASHER  = 15;//1;
	public static final int CAN_DEVICE_WRIST    = 6;//17;
	public static final int CAN_DEVICE_SHOULDER = 7;//12;
	public static final int CAN_DEVICE_ELBOW    = 8;//16;

	public static final int CAN_DEVICE_DRIVE_L1 = 13;//1;
	public static final int CAN_DEVICE_DRIVE_L2 = 14;//2;
	public static final int CAN_DEVICE_DRIVE_R1 = 19;//9;
	public static final int CAN_DEVICE_DRIVE_R2 = 11;//10;
}
