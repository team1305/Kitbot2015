package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.forks.ForksDoNothing;

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
        setDefaultCommand(new ForksDoNothing());
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
     * deploy the forks.
     */
    public void deployStacker(){
    	isDeployed = true;
    	stackerSol.set(true);
    }
    
    /**
     * Deploys/Retracts the stacker.
     */
    public void ToggleStacker(){

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
    	if(triggerTimer.get() >= TRIGGER_TIMEOUT){
    		forkSol.set(false);
    		isOpen = false;
    		humanErrorTimer.start();
    	}

    }
    
    /**
     * Opens/Closes forks in auto.
     */
    public void AutonomousForks(){

    	if (isOpen == false){
    		isOpen = true;
    		SmartDashboard.putString("Fork Status :", "Open!");
    		forkSol.set(true);

    	} else if(isOpen == true){
    		isOpen = false;
    		SmartDashboard.putString("Fork Status :", "Close!");
    		forkSol.set(false);
    	}
    }
    
    public void openForks(){
    	isOpen = true;
    	forkSol.set(true);
    }
    
    public void closeForks(){
    	isOpen = false;
    	forkSol.set(false);
    }
}

