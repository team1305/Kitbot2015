package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.claw.ClawAutoGrab;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Claw extends Subsystem {

	private Solenoid ClawAct = new Solenoid(RobotMap.SOL_CLAW);

	private DigitalInput trigger = new DigitalInput(RobotMap.DIO_CLAW_TRIGGER);

	// Prevents autotrigger from closing immediately  when opened.
	private Timer triggerTimer = new Timer();
	private final double TRIGGER_LOCKOUT = 3.0;
	private final double RUMBLE_DURATION = 0.1;
	
	private boolean isClosed = false;

    public Claw(){
    	triggerTimer.start();
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new ClawAutoGrab());
    }
    
    /**
     * Manually open or close the claw.
     */
    public  void toggleGrab(){
    	if (isClosed == true){
    		triggerTimer.reset();
    		isClosed = false;
    		ClawAct.set(false);
    	} else {
    		isClosed = true;
    		ClawAct.set(true);
    	}
    }
    
    /**
     * explicitly close the claw
     */
    public void close(){
    	isClosed = true;
    	ClawAct.set(true);
		Robot.oi.armRumble(RUMBLE_DURATION);
    }
    
    /**
     * explicitly open the claw.
     */
    public void open(){
    	triggerTimer.reset();
    	isClosed = false;
    	ClawAct.set(false);
		Robot.oi.armRumble(RUMBLE_DURATION);
    }

    /**
     * Automatically opens claw when trigger is hit.
     * 
     * If TRIGGER_LOCKOUT is still in effect, this will not activate.
     */
    public void autoGrab(){
    	if (getTrigger() == true){
	    	if(triggerTimer.get() >= TRIGGER_LOCKOUT){
		    	ClawAct.set(true);
		    	isClosed = true;
	    		Robot.oi.armRumble(RUMBLE_DURATION);
	    	}
    	}

    }
    /**
     * Return the state of the claw trigger. 
     * @return true if the trigger is triggered, false otherwise.
     */
    public boolean getTrigger(){
    	return !trigger.get();
    }
    
    /**
     * Return the state of the claw	
     * @return true if the claw is closed, false otherwise.
     */
    public boolean getClosed(){
    	return this.isClosed;
    }

}

