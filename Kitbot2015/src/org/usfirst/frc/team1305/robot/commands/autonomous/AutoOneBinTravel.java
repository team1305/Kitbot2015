package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.arm.AutonomousArmExtend;
import org.usfirst.frc.team1305.robot.commands.arm.AutonomousArmTransport;
import org.usfirst.frc.team1305.robot.commands.arm.AutonomousArmUp;
import org.usfirst.frc.team1305.robot.commands.claw.AutonomousBin;
import org.usfirst.frc.team1305.robot.commands.claw.ToggleClaw;
import org.usfirst.frc.team1305.robot.commands.drivetrain.AutonomousMobility;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoOneBinTravel extends CommandGroup {
    
    public  AutoOneBinTravel() {
    	addSequential(new AutonomousMobility(0.3, 0.5, 0.5));
    	addSequential(new AutonomousArmExtend(2.7));
    	addSequential(new AutonomousBin(-0.7,-0.7));
    	addSequential(new Wait(0.1));
    	addSequential(new AutonomousArmUp(1.2));
    	addSequential(new AutonomousMobility(1.5, 0.6, 0.6)); //TODO: 1.5
    	addParallel(new AutonomousMobility(0.5, 0.5, 0.5));
    	addSequential(new AutonomousArmTransport(1.8));
    	addSequential(new ToggleClaw());
    	addSequential(new AutonomousMobility(1, 0.8, 0.8));
    	addSequential(new AutonomousMobility(0.55, 0.6, -0.6));
    	addSequential(new AutonomousMobility(5, -0.6, -0.6));
    	addSequential(new AutonomousMobility(0.4, -0.6, 0.6));
    }
}
