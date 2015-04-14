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
public class AutoTurnNGo extends CommandGroup {
    
    public  AutoTurnNGo() {

    	addSequential(new DriveEncoder(-3, 0.6));
    	
    	addSequential(new DriveGyroRotate(86));
    	
    	addSequential(new DriveEncoder(-3, 0.6));

    }
}
