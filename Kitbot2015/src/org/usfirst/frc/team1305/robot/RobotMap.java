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
	public static int AXIS_DRIVETRAIN_MOVE = OI.AXIS_Y;
	public static int AXIS_DRIVETRAIN_ROTATE = OI.AXIS_X;
	
	//Motor PWM
	public static final int PWM_DRIVE_RIGHT_1 = 0;
	public static final int PWM_DRIVE_RIGHT_2 = 1;
	public static final int PWM_DRIVE_RIGHT_3 = 2;
	public static final int PWM_DRIVE_LEFT_1 = 3;
	public static final int PWM_DRIVE_LEFT_2 = 4;
	public static final int PWM_DRIVE_LEFT_3 = 5;
	
	//Analog Ports
	public static final int ANALOG_GYRO_RATE = 0;
}
