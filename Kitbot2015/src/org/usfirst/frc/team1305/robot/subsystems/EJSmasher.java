package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The EJ Smasher (Working title) is the two-bin grabbin' super arm that can 
 * replace the EJ Stacker for elims.
 * 
 * The EJ Smasher starts the match with the winch cable already loose, so that 
 * a quick jerk of the robot is all that is needed to send it flying. Once it
 * it is deployed, the robot has to drive forward for a few seconds, and then 
 * winch the system back up. Winching the stacker back up will be done 
 * automagically.
 */
public class EJSmasher extends Subsystem {
	// The EJ Smasher re-uses the winch system, as well as the sneezeguard 
	// trigger that is already present for the EJ Stacker subsystem. Thus, it is
	// imperative that only one of the subsystems be activated at one time.
	private CANTalon m_winch = new CANTalon(RobotMap.CAN_DEVICE_SMASHER);
	private DigitalInput d_trigger = new DigitalInput(RobotMap.DIO_SMASHER_WINCH_LIMIT);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * 
     * @return true if the trigger is being hit. False otherwise.
     */
    public boolean getTrigger(){
    	return !d_trigger.get();
    }
    
    public void winch_up(){
    	m_winch.set(1);
    }
    
    public void winch_down(){
    	m_winch.set(-1);
    }
    
    public void winch_stop(){
    	m_winch.set(0);
    }
}

