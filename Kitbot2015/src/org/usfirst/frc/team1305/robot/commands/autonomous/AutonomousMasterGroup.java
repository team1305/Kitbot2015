package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.forks.ToggleForks;
import org.usfirst.frc.team1305.robot.commands.forks.ToggleStackerDeployment;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *Does auto things
 */
public class AutonomousMasterGroup extends CommandGroup {

	// AutonomousMobility uses three doubles: Duration, LeftSpeed, RightSpeed.
    public AutonomousMasterGroup() {
    	// TOTE GRAB
//    	addSequential(new AutonomousMobility(-0.5, -0.5, -0.5));
//    	addSequential(new ToggleStackerDeployment());
//    	addSequential(new AutonomousMobility(1.5, 0, 0));
//    	addSequential(new AutonomousForks());
//    	addSequential(new AutonomousMobility(1, 0, 0));
//    	addSequential(new AutonomousTote(0.5, 0.5));
//    	addSequential(new AutonomousMobility(0.25, 0, 0));
//    	addSequential(new AutonomousForks());
//    	addSequential(new AutonomousMobility(0.5, 0, 0));
//    	addSequential(new AutonomousElevator(0.5, -1));
//    	addSequential(new AutonomousMobility(3.3, -0.5, -0.5)); // enable for drive auto
//    	addSequential(new AutonomousElevator(0.5, 1));
//    	addSequential(new AutonomousForks());
//    	addSequential(new AutonomousMobility(0.7, 0, 0));
//    	addSequential(new AutonomousMobility(0.4, -0.5, -0.5));
    	
    	// BIN GRAB
    	addSequential(new AutonomousMobility(0.3, 0.5, 0.5));
    	addSequential(new AutonomousArmExtend(3.8));
    	addSequential(new AutonomousBin(-0.5,-0.5));
    	addSequential(new Wait(1));
    	addSequential(new AutonomousArmUp(1.5));
    	addSequential(new AutonomousMobility(3.5, 0.5, 0.5));
        }
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }

