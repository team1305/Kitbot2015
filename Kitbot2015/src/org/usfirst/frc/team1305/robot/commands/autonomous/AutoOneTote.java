package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.arm.AutonomousArmExtend;
import org.usfirst.frc.team1305.robot.commands.arm.AutonomousArmUp;
import org.usfirst.frc.team1305.robot.commands.claw.AutonomousBin;
import org.usfirst.frc.team1305.robot.commands.drivetrain.AutonomousMobility;
//import org.usfirst.frc.team1305.robot.commands.drivetrain.GyroRotate;
import org.usfirst.frc.team1305.robot.commands.elevator.AutonomousElevator;
import org.usfirst.frc.team1305.robot.commands.forks.AutonomousForks;
import org.usfirst.frc.team1305.robot.commands.forks.AutonomousTote;
import org.usfirst.frc.team1305.robot.commands.forks.ToggleStackerDeployment;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoOneTote extends CommandGroup {
    
    public  AutoOneTote() {
    	addSequential(new AutonomousMobility(-0.5, -0.5, -0.5));
    	addSequential(new ToggleStackerDeployment());
    	addSequential(new AutonomousMobility(1.5, 0, 0));
    	addSequential(new AutonomousForks());
    	addSequential(new AutonomousMobility(1, 0, 0));
    	addSequential(new AutonomousTote(0.5, 0.5));
    	addSequential(new AutonomousMobility(0.25, 0, 0));
    	addSequential(new AutonomousForks());
    	addSequential(new AutonomousMobility(0.5, 0, 0));
    	addSequential(new AutonomousElevator(0.5, -1));
    	addSequential(new AutonomousMobility(3.3, -0.5, -0.5)); // enable for drive auto
    	addSequential(new AutonomousElevator(0.5, 1));
    	addSequential(new AutonomousForks());
    	addSequential(new AutonomousMobility(0.7, 0, 0));
    	addSequential(new AutonomousMobility(0.4, -0.5, -0.5));
    }
}
