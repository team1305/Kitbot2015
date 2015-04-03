package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.forks.ForksDoNothing;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Handles all fork movement, including opening and closing, and deployment.
 */
public class EJSmasher extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon winch = new CANTalon(RobotMap.CAN_DEVICE_LIFT);
	private DigitalInput smashTrigger = new DigitalInput(RobotMap.DIO_SMASHER_TRIGGER);

	public EJSmasher(){
	}
	
	
    public void initDefaultCommand() {
//        setDefaultCommand(new ForksDoNothing());
    }

    public boolean getTrigger(){
    	return !smashTrigger.get();
    }    

    
    public void lift(){
    	winch.set(1);
    }
    
    public void lower(){
    	winch.set(-1);
    }
}

