package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.EJSmasher.SmasherAutoRelease;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveEncoder;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSmasherNoMove extends CommandGroup {
    
    public  AutoSmasherNoMove() {
    	addSequential(new DriveEncoder(0, 0));
        addSequential(new SmasherAutoRelease());
        addSequential(new DriveEncoder(-6, 0.4));
    }
}
