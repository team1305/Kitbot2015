package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.arm.ArmGoPreset;
import org.usfirst.frc.team1305.robot.commands.claw.ClawClose;
import org.usfirst.frc.team1305.robot.commands.claw.ClawOpen;
import org.usfirst.frc.team1305.robot.commands.claw.ClawToggle;
//import org.usfirst.frc.team1305.robot.commands.drivetrain.GyroRotate;


import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveEncoder;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveEncoderUntilClawTrig;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveGyroRotate;
import org.usfirst.frc.team1305.robot.subsystems.Arm.Preset;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTwoBinStep extends CommandGroup {
    
    public  AutoTwoBinStep() {
    	
    	addSequential(new AutoOneBinStep());
    	
    	addSequential(new DriveGyroRotate(-48, 3));

    	addParallel(new ArmGoPreset(Preset.PRESET_EXTENDED, 2.8));
    	
    	addSequential(new DriveEncoder(-8.07, 0.5, 5));
    	
    	addSequential(new DriveGyroRotate(48, 3));
    	//drive (backwards!) until the claw trigger gets triggered by a bin

//    	addSequential(new AutonomousMobility(0.3, 0.5, 0.5));
//    	addSequential(new AutonomousArmExtend(2.7));
//    	addSequential(new AutonomousBin(-0.7,-0.7));
//    	addSequential(new Wait(0.1));
//    	addSequential(new AutonomousArmUp(1.2));
//    	addSequential(new AutonomousMobility(1.5, 0.6, 0.6)); //TODO: 1.5
//    	addParallel(new AutonomousMobility(0.5, 0.5, 0.5));
//    	addSequential(new AutonomousArmTransport(1.8));
//    	addSequential(new ToggleClaw());
//    	addSequential(new AutonomousMobility(1, 0.8, 0.8));
//    	addSequential(new AutonomousMobility(0.55, 0.6, -0.6));
//    	addSequential(new AutonomousMobility(5, -0.6, -0.6));
//    	addSequential(new AutonomousMobility(0.4, -0.6, 0.6));

    	
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
