package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.accelerometer.AccelerometerDefaultCommand;
import org.usfirst.frc.team1305.robot.commands.claw.ClawDoNothing;
import org.usfirst.frc.team1305.robot.commands.claw.ToggleClaw;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.CanTalonSRX;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Claw extends Subsystem {
    
	private Solenoid ClawAct = new Solenoid(RobotMap.SOL_CLAW);
	
	private boolean IsOpen = false;
    
    public Claw(){
    	
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand();
    	setDefaultCommand(new ClawDoNothing());
    }
    //toggles claw
    public  void toggleGrab(){
    	if (IsOpen == false){
    		IsOpen = true;
//    		SmartDashboard.putString("Claw Status :", "Open!");
    		ClawAct.set(true);
    		
    	} else {
    		IsOpen = false;
//    		SmartDashboard.putString("Claw Status :", "Close!");
    		ClawAct.set(false);
    	}
    }

    
    
}

