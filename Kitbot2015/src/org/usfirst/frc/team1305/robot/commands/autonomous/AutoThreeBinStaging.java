package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.arm.AutonomousArmExtend;
import org.usfirst.frc.team1305.robot.commands.arm.AutonomousArmTransport;
import org.usfirst.frc.team1305.robot.commands.arm.AutonomousArmUp;
import org.usfirst.frc.team1305.robot.commands.claw.AutonomousBin;
import org.usfirst.frc.team1305.robot.commands.claw.ToggleClaw;
import org.usfirst.frc.team1305.robot.commands.drivetrain.AutonomousMobility;
//import org.usfirst.frc.team1305.robot.commands.drivetrain.GyroRotate;
import org.usfirst.frc.team1305.robot.commands.elevator.AutonomousElevator;
import org.usfirst.frc.team1305.robot.commands.forks.AutonomousForks;
import org.usfirst.frc.team1305.robot.commands.forks.AutonomousTote;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class AutoThreeBinStaging extends CommandGroup {
    /**
     * Robot starts in staging, grabs three bins, scores in auto zone.
     */
    public  AutoThreeBinStaging() {
    	addSequential(new AutonomousTote(0.5, 0.5));
    	addSequential(new AutonomousForks());
    	addSequential(new AutonomousElevator(0.3, 0.5));
    	addParallel(new AutonomousArmTransport(0.5));
    	addSequential(new AutonomousBin(-0.5,-0.5));
    	addSequential(new AutonomousArmUp(0.4));
//    	addParallel(new GyroRotate(10));
    	addSequential(new AutonomousMobility(3, -0.7, -0.7));
//    	addSequential(new GyroRotate(80));
    	addSequential(new AutonomousMobility(2, -0.7, -0.7));
//    	addSequential(new GyroRotate(180));
    	addSequential(new AutonomousForks());
    	addSequential(new AutonomousMobility(0.2, -1, -1));
    	addSequential(new ToggleClaw());
    }
}
