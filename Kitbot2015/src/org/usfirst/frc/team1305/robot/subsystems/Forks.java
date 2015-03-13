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
	private boolean IsOpen = false;
	private boolean IsDeployed = false;
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

    public void initDefaultCommand() {
        setDefaultCommand(new ForksDoNothing());
    }

    /**
     * Opens and closes the stacker.
     */
    public void ToggleForks(){

    	if (IsOpen == false &&
    			humanErrorTimer.get() >= TRIGGER_TIMEOUT_HUMAN_ERROR){
    		IsOpen = true;
    		SmartDashboard.putString("Fork Status :", "Open!");
    		forkSol.set(true);
    		triggerTimer.reset();

    	} else if(IsOpen == true){
    		IsOpen = false;
    		SmartDashboard.putString("Fork Status :", "Close!");
    		forkSol.set(false);
    	}
    }

    /**
     * Deploys/Retracts the stacker.
     */
    public void ToggleStacker(){

    	if (IsDeployed == false){
    		IsDeployed = true;
    		SmartDashboard.putString("Stacker Status :", "Open!");
    		stackerSol.set(true);

    	} else {
    		IsDeployed = false;
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
    		IsOpen = false;
    		humanErrorTimer.start();
    	}

    }
    
    public void AutonomousForks(){

    	if (IsOpen == false){
    		IsOpen = true;
    		SmartDashboard.putString("Fork Status :", "Open!");
    		forkSol.set(true);

    	} else if(IsOpen == true){
    		IsOpen = false;
    		SmartDashboard.putString("Fork Status :", "Close!");
    		forkSol.set(false);
    	}
    }
}

