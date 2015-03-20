package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.forks.ForksAutoGrab;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Handles all fork movement, including opening and closing, and deployment.
 */
public class Forks extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private boolean isOpen = false;
	private boolean isDeployed = false;
	private Solenoid forkSol = new Solenoid(RobotMap.SOL_FORK);
	private Solenoid stackerSol = new Solenoid(RobotMap.SOL_STACKER);
	private Timer triggerTimer = new Timer();
	private Timer humanErrorTimer = new Timer();
	public DigitalInput trigger = new DigitalInput(RobotMap.DIO_STACKER_TRIGGER);

	private double TRIGGER_TIMEOUT = 1.5;
	private double TRIGGER_TIMEOUT_HUMAN_ERROR = 0.8;

	public Forks(){
		triggerTimer.start();
		humanErrorTimer.start();
	}
	
	/**
	 * get the state of the forks
	 * @return true if the forks are open. False otherwise.
	 */
	public boolean getForkState(){
		return this.isOpen;
	}

    public void initDefaultCommand() {
        setDefaultCommand(new ForksAutoGrab());
    }

    /**
     * Opens and closes the stacker.
     */
    public void ToggleForks(){

    	if (isOpen == false &&
    			humanErrorTimer.get() >= TRIGGER_TIMEOUT_HUMAN_ERROR){
    		isOpen = true;
    		SmartDashboard.putString("Fork Status :", "Open!");
    		forkSol.set(true);
    		triggerTimer.reset();

    	} else if(isOpen == true){
    		isOpen = false;
    		SmartDashboard.putString("Fork Status :", "Close!");
    		forkSol.set(false);
    	}
    }

    /**
     * Explicitly deploy the stacker.
     */
    public void deployStacker(){
    	isDeployed = true;
    	stackerSol.set(true);
    }
    
    /**
     * explicitly retract the stacker.
     */
    public void retractStacker(){
    	isDeployed = false;
    	stackerSol.set(false);
    }
    
    /**
     * Deploys/Retracts the stacker.
     */
    public void toggleDeployment(){

    	if (isDeployed == false){
    		isDeployed = true;
    		SmartDashboard.putString("Stacker Status :", "Open!");
    		stackerSol.set(true);

    	} else {
    		isDeployed = false;
    		SmartDashboard.putString("Stacker Status :", "Close!");
    		stackerSol.set(false);
    	}
    }

    /**
     * Auto closes forks when trigger is activated.
     * 
     * If TRIGGER_TIMEOUT is still active, 
     */
    public void autoForks(){
    	if(getTrigger() == true){
	    	if(triggerTimer.get() >= TRIGGER_TIMEOUT){
	    		forkSol.set(false);
	    		isOpen = false;
	    		humanErrorTimer.start();
	    	}
    	}
    }
    
    /**
     * explicitly open the forks
     */
    public void openForks(){
    	isOpen = true;
    	forkSol.set(true);
    }
    
    /**
     * explicitly close the forks
     */
    public void closeForks(){
    	isOpen = false;
    	forkSol.set(false);
    }
    
    /**
     * Get the state of the sneezeguard trigger.
     * @return true if the trigger is activated. False otherwise.
     */
    public boolean getTrigger(){
    	return !this.trigger.get();
    }
}

