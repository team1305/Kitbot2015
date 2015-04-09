package org.usfirst.frc.team1305.robot.commands.drivetrain;

import org.usfirst.frc.team1305.robot.Robot;


/**
 * Drive forward with the encoder in the usual way, and additionally return early if 
 * the claw trigger has been triggered.
 */
public class DriveEncoderUntilClawTrig extends DriveEncoder {

    /**
	 * Drive forward the specified distance, at the specified speed, with a specified timeout
	 * @param distance the distance to cover, in feet. A negative distance will move backward.
	 * @param speed the speed to drive at, in range [0.1, 1]
	 * @param timeout the maximum time to try driving.
	 */
	public DriveEncoderUntilClawTrig(double distance, double speed, double timeout) {
		super(distance, speed, timeout);
	}
	
	/**
	 * Drive forward the specified distance, at the specified speed.
	 * @param distance the distance to cover, in feet.
	 * @param speed the speed to drive at, in range [0.1, 1]
	 */
	public DriveEncoderUntilClawTrig(double distance, double speed){
		super(distance, speed);
	}
	
	@Override
	protected boolean isFinished(){
		if(Robot.claw.getTrigger() == true) return true;
		else return super.isFinished();
	}
}
