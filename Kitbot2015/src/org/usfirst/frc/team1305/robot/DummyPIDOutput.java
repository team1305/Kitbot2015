package org.usfirst.frc.team1305.robot;

import edu.wpi.first.wpilibj.PIDOutput;

/**
 * This is a dummy class used to emulate a PIDOutput, used in PacmanDrive.java
 */
public class DummyPIDOutput implements PIDOutput{

	double value;
	public DummyPIDOutput(){

	}

	public void pidWrite(double output){
		//SmartDashboard.putNumber("DriveTrainPID Write", output);
		value = output;
	}

	public double get(){
		return this.value;
	}
}
