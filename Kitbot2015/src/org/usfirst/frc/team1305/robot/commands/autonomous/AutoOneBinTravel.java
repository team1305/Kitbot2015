package org.usfirst.frc.team1305.robot.commands.autonomous;



import org.usfirst.frc.team1305.robot.commands.arm.ArmGoPreset;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveEncoder;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveGyroRotate;
import org.usfirst.frc.team1305.robot.subsystems.Arm.Preset;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoOneBinTravel extends CommandGroup {
    
    public  AutoOneBinTravel() {
    	// Backs up
    	addSequential(new DriveEncoder(2, 0.4));
    	// Turns robot 90 degrees counterclockwise
    	addSequential(new DriveGyroRotate(-90, 1.5));
    	
    	addSequential(new DriveEncoder(-15, 0.4));
    	
    	addSequential(new DriveGyroRotate(90, 1.5));
    	
    	addSequential(new ArmGoPreset(Preset.PRESET_MAXSTACK, 0.1));
    	
    	addSequential(new ArmGoPreset(Preset.PRESET_EXTENDED_BUMP, 3));
    	
    	addSequential(new DriveEncoder(-4, 0.3));

    }
}
