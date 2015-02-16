package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.Constants;
import org.usfirst.frc.team1305.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gyroscope extends Subsystem implements PIDSource {
    
	private Gyro gyro = new Gyro(RobotMap.ANALOG_GYRO);

	
	public Gyroscope(){
		gyro.setSensitivity(Constants.GYRO_YAW_RATE);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	public double pidGet() {
		return this.getAngle();
	}
	
	public double getRate(){
		return gyro.getRate();
	}
	/**
	 * Get the angle of the gyroscope, constrained to the range [0, 360]
	 * @return the measured angle of the gyro.
	 */
	public double getAngle(){
		//if the value is greater than 360, subtract 360 until the value lies in the primary zone.
		int angle = (int) gyro.getAngle();
		int fullTurns = angle / 360;
		//if the angle is negative, we subtract that angle from 360 to get angle in range [0, 360)
		if( angle < 0) fullTurns -= 1;
		
		return gyro.getAngle() - 360.0 * fullTurns;
	}
	
	public void gyroInit(){
		gyro.initGyro();
	}
}

