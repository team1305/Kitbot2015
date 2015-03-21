package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.commands.dash.DashUpdate;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem which collects all important data and reports it to
 * the SmartDashboard. Polls public interfaces and calls other commands in
 * order to find required data.
 */
public class Dash extends Subsystem {
	//=========================================================================
	//=========== Subsystem Variables =========================================
	//=========================================================================
	
	//**Arm(NewArm)**
	
    
    public void initDefaultCommand() {
        setDefaultCommand(new DashUpdate());
    }
    
}

