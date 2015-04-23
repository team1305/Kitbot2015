package org.usfirst.frc.team1305.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	//Analog Ports
	public static final int ANALOG_GYRO 		= 0;
	public static final int ANALOG_POT_SHOULDER = 1;
	public static final int ANALOG_POT_ELBOW    = 2;
	public static final int ANALOG_POT_WRIST    = 3;

	//Digital Ports
	public static final int DIO_LEFT_ENC_A 		    = 0;
	public static final int DIO_LEFT_ENC_B 		    = 1;
	public static final int DIO_RIGHT_ENC_A		    = 2;
	public static final int DIO_RIGHT_ENC_B		    = 3;
	public static final int DIO_SMASHER_WINCH_LIMIT = 8;
	public static final int DIO_CLAW_TRIGGER        = 9;

	//Solenoid ports
	public static final int SOL_SMASHER_TRIGGER = 1;
	public static final int SOL_CLAW            = 2;

	//CAN Devices Numbers
	public static final int CAN_DEVICE_SMASHER  = 5;//1;
	public static final int CAN_DEVICE_WRIST    = 6;//17;
	public static final int CAN_DEVICE_SHOULDER = 7;//12;
	public static final int CAN_DEVICE_ELBOW    = 8;//16;

	public static final int CAN_DEVICE_DRIVE_L1 = 1;//1;
	public static final int CAN_DEVICE_DRIVE_L2 = 2;//2;
	public static final int CAN_DEVICE_DRIVE_R1 = 9;//9;
	public static final int CAN_DEVICE_DRIVE_R2 = 10;//10;
}
