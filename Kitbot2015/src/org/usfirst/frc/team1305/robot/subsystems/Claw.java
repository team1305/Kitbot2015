package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.claw.ClawDoNothing;

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

	public DigitalInput trigger = new DigitalInput(RobotMap.DIO_CLAW_TRIGGER);

	// Prevents autotrigger from closing when opened.
	private Timer triggerTimer = new Timer();
	private final double TRIGGER_LOCKOUT = 3;
	
	private boolean IsOpen = false;

    public Claw(){
    	triggerTimer.start();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand();
    	setDefaultCommand(new ClawDoNothing());
    }
    
    /**
     * Handles manually opening and closing claw.
     */
    public  void toggleGrab(){
    	if (IsOpen == false){
    		triggerTimer.reset();
    		IsOpen = true;
//    		SmartDashboard.putString("Claw Status :", "Open!");
    		ClawAct.set(true);

    	} else {
    		IsOpen = false;
//    		SmartDashboard.putString("Claw Status :", "Close!");
    		ClawAct.set(false);
    	}
    }

    /**
     * Automatically opens claw when trigger is hit.
     * 
     * If TRIGGER_LOCKOUT is still in effect, this will not activate.
     */
    public void autoGrab(){
    	if(triggerTimer.get() >= TRIGGER_LOCKOUT){
    	ClawAct.set(false);
    	IsOpen = false;
    	}

    }

}

