package org.usfirst.frc.team1305.robot.commands.autonomous;


import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.commands.arm.ArmGoPreset;
import org.usfirst.frc.team1305.robot.commands.claw.ClawClose;
import org.usfirst.frc.team1305.robot.commands.claw.ClawOpen;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveEncoder;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveEncoderUntilClawTrig;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveUntilClawTrig;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveAuto;
import org.usfirst.frc.team1305.robot.subsystems.Arm.Joint;
import org.usfirst.frc.team1305.robot.subsystems.Arm.Preset;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Extends the arm and drives forward until a bin is grasped. Then grab the bin, back up, and place it on the ground.
 */
public class AutoOneBinStep extends CommandGroup {
    
    public  AutoOneBinStep() {
    	//back up a bit so that we have room to move. Also remember that the arm is technically the back of the robot
    	addParallel(new DriveEncoder(0.4, 0.4));
    	// go to max stack preset for a fraction of a second to clear the front totes
    	addSequential(new ArmGoPreset(Preset.PRESET_EXTENDED_BUMP, 0.4));
    	//now go to max extension
    	addSequential(new ArmGoPreset(Preset.PRESET_EXTENDED, 2.8));
    	//drive (backwards!) until the claw trigger gets triggered by a bin
    	addSequential(new DriveEncoderUntilClawTrig(-10.0, 0.4, 10.0));
    	//close the claw around the bin
    	addSequential(new ClawClose());
    	//lift the bin up a bit
    	addSequential(new ArmGoPreset(Preset.PRESET_MAXSTACK, 0.4));
    	//Begins to drive backwards
    	addSequential(new DriveEncoder(2, 0.4));
    	// Lowers arm to prepare for drop
    	addParallel(new ArmGoPreset(Preset.PRESET_TRANSPORT, 2));
    	// Continues driving backwards while driving arm
    	addSequential(new DriveEncoder(4.8, 0.4));
    	addSequential(new ClawOpen());
    	addSequential(new Wait(0.1));
    	addSequential(new DriveEncoder(0.5,0.4));
    	
    }
}
