package org.usfirst.frc.team1305.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.forks.ForksDoNothing;
import org.usfirst.frc.team1305.robot.commands.forks.ToggleForks;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 */
public class Forks extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private boolean IsOpen = false;
	private Solenoid forkSol = new Solenoid(RobotMap.SOL_FORK);
	
	public Forks(){
		
	}
	
	private void updateSmartDashboard()
    {
	
    }
	// Called just before this Command runs the first time
    protected void initialize() {
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ForksDoNothing());
    }
    
    public void ToggleForks(){
    	
    	if (IsOpen == false){
    		IsOpen = true;
    		SmartDashboard.putString("Claw Status :", "Open!");
    		forkSol.set(true);
    		
    	} else {
    		IsOpen = false;
    		SmartDashboard.putString("Claw Status :", "Close!");
    		forkSol.set(false);
    	}
    	updateSmartDashboard();
    }
}

