package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.arm.AutonomousArmExtend;
import org.usfirst.frc.team1305.robot.commands.arm.AutonomousArmUp;
import org.usfirst.frc.team1305.robot.commands.claw.AutonomousBin;
import org.usfirst.frc.team1305.robot.commands.drivetrain.AutonomousMobility;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoOneBinStep extends CommandGroup {
    
    public  AutoOneBinStep() {
    	addSequential(new AutonomousMobility(0.3, 0.5, 0.5));
    	addSequential(new AutonomousArmExtend(3.0));
    	addSequential(new AutonomousBin(-0.6,-0.6));
    	addSequential(new Wait(0.1));
    	addSequential(new AutonomousArmUp(1.3));
    	addSequential(new AutonomousMobility(1.5, 0.5, 0.5)); //TODO: 3.5
    }
}
