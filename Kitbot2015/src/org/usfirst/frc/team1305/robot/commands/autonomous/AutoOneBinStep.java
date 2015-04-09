package org.usfirst.frc.team1305.robot.commands.autonomous;


import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.commands.arm.ArmGoPreset;
import org.usfirst.frc.team1305.robot.commands.claw.ClawClose;
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
    	addSequential(new DriveEncoder(0.5, 0.3));
    	// go to max stack preset for a fraction of a second to clear the front totes
    	addSequential(new ArmGoPreset(Preset.PRESET_MAXSTACK, 0.5));
    	//now go to max extension
    	addSequential(new ArmGoPreset(Preset.PRESET_EXTENDED, 3.0));
    	//drive (backwards!) until the claw trigger gets triggered by a bin
    	addSequential(new DriveEncoderUntilClawTrig(-10.0, 0.3, 10.0));
    	//close the claw around the bin
    	addSequential(new ClawClose());
    	//lift the bin up a bit
    	addSequential(new ArmGoPreset(Preset.PRESET_MAXSTACK, 0.6));
    	//TODO finish this

    }
}
