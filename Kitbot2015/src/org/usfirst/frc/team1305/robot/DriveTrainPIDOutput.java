package org.usfirst.frc.team1305.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrainPIDOutput implements PIDOutput{

	Double value;
	public DriveTrainPIDOutput(Double d)
	{
		SmartDashboard.putNumber("DriveTrainPID Constructor", d);
		value = d;	
	}
	
	public void pidWrite(double output){
		SmartDashboard.putNumber("DriveTrainPID Write", output);
		value = output;
	}
}
