package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveUntilClawTrig;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoOneBinStep extends CommandGroup {
    
    public  AutoOneBinStep() {
    	addSequential(new DriveAuto(0.3, 0.5, 0.5));
    	addSequential(new DriveUntilClawTrig(-0.6,-0.6));
    	addSequential(new Wait(0.1));
    	addSequential(new DriveAuto(1.5, 0.5, 0.5)); //TODO: 3.5
    }
}
