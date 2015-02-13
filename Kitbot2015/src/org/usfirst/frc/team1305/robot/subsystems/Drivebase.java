
package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.DriveBase.Drive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.CanTalonSRX;

/**
 *
 */
public class Drivebase extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CanTalonSRX Left_Rear_Drive_Motor = new CanTalonSRX(1);
	private CanTalonSRX Left_Front_Drive_Motor = new CanTalonSRX(2);
	private CanTalonSRX Right_Rear_Drive_Motor = new CanTalonSRX(10);
	private CanTalonSRX Right_Front_Drive_Motor = new CanTalonSRX(9);
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
    public void Teamptank(double move, double turn){
    	double vall;
    	double valr;
    	vall = move + turn;
    	//above/below needs restringing to -1.0 to 1.0
    	valr = -move + turn;
    	Left_Front_Drive_Motor.Set(vall);
    	Left_Rear_Drive_Motor.Set(vall);
    	Right_Front_Drive_Motor.Set(valr);
    	Right_Rear_Drive_Motor.Set(valr);
    	

    }
    
}

