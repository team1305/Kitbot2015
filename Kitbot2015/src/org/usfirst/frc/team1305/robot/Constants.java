package org.usfirst.frc.team1305.robot;

/**
 * Constants - Robot-wide constants for PIDs, etc.
 * @author paul
 *
 */
public class Constants {
	//for SmoothDrive, units %/s
	public static final double DRIVESMOOTHING_MAX_RATE = 23.0;
	
	//for Gyroscope subsystem
	public static final double GYRO_YAW_RATE = 0.003; // unit v/*C/second
	
	//for PacmanDrive
	public static final double PACMAN_P = 0.0046;
	public static final double PACMAN_I = 0.00001;
	public static final double PACMAN_D = 0.000001;
	public static final double PACMAN_TOLERANCE = 0.25; //percent

}
