package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.arm.AutonomousArmExtend;
import org.usfirst.frc.team1305.robot.commands.arm.AutonomousArmUp;
import org.usfirst.frc.team1305.robot.commands.claw.AutonomousBin;
import org.usfirst.frc.team1305.robot.commands.claw.ToggleClaw;
import org.usfirst.frc.team1305.robot.commands.drivetrain.AutonomousMobility;
//import org.usfirst.frc.team1305.robot.commands.drivetrain.GyroRotate;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTwoBinStep extends CommandGroup {
    
    public  AutoTwoBinStep() {
//    	addSequential(new GyroRotate(-60.0, 1.0));
    	addParallel(new AutonomousArmExtend(1));
    	addSequential(new AutonomousBin(-0.6,-0.6));
    	addSequential(new AutonomousArmUp(0.5));
    	addSequential(new AutonomousMobility(0.3, 0.8, 0.8));
    	addSequential(new ToggleClaw());
    	addSequential(new AutonomousMobility(0.1, 0, 0));
    	addSequential(new AutonomousMobility(0.3, 0.8, 0.8));
//    	addSequential(new GyroRotate(-42.0, 1.0));
    	addParallel(new AutonomousArmExtend(1));
    	addSequential(new AutonomousBin(-0.6,-0.6));
    	addSequential(new AutonomousArmUp(0.5));
    	addSequential(new AutonomousMobility(0.3, 0.8, 0.8));
    	addSequential(new ToggleClaw());
    	addSequential(new AutonomousMobility(0.1, 0, 0));
    	addSequential(new AutonomousMobility(0.3, 0.8, 0.8));
//    	addSequential(new GyroRotate(-65.0, 1.1));
    	addSequential(new AutonomousMobility(0.7, 0.8, 0.8));
//    	addSequential(new GyroRotate(60.0, 1.0));
    	addSequential(new AutonomousBin(-0.6,-0.6));
    	addSequential(new AutonomousArmUp(0.5));
    	addSequential(new AutonomousMobility(0.3, 0.8, 0.8));
    	
    	//DOUBLE BIN GRAB [THEORETICAL]
//    	addSequential(new GyroRotate(-60.0, 1.4));
//    	addParallel(new AutonomousArmExtend(0.5));
//    	addSequential(new AutonomousMobility(1.5, 0, 0));
//    	addSequential(new GyroRotate(-42.0, 2));
//    	addSequential(new AutonomousMobility(1.5, 0, 0));
//    	addSequential(new GyroRotate(-68.0, 2));
//    	addSequential(new AutonomousMobility(0.7, 0.8, 0.8));
//    	addSequential(new GyroRotate(60.0, 2.2));
    }
}
