package org.usfirst.frc.team1305.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrainPIDOutput implements PIDOutput{

	double value;
	public DriveTrainPIDOutput(){

	}
	
	public void pidWrite(double output){
		SmartDashboard.putNumber("DriveTrainPID Write", output);
		value = output;
	}
	
	public double get(){
		return this.value;
	}
}
