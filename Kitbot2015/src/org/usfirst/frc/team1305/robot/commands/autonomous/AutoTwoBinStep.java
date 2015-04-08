//package org.usfirst.frc.team1305.robot.commands.autonomous;
//
//import org.usfirst.frc.team1305.robot.commands.claw.ClawToggle;
//import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveUntilClawTrig;
//import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveAuto;
////import org.usfirst.frc.team1305.robot.commands.drivetrain.GyroRotate;
//
//
//import org.usfirst.frc.team1305.robot.commands.drivetrain.GyroRotate;
//
//import edu.wpi.first.wpilibj.command.CommandGroup;
//
///**
// *
// */
//public class AutoTwoBinStep extends CommandGroup {
//    
//    public  AutoTwoBinStep() {
//
////    	addSequential(new GyroRotate(-60.0, 1.0));
////    	addParallel(new AutonomousArmExtend(1));
//    	addSequential(new DriveUntilClawTrig(-0.6,-0.6));
////    	addSequential(new AutonomousArmUp(0.5));
//    	addSequential(new DriveAuto(0.3, 0.8, 0.8));
//    	addSequential(new ClawToggle());
//    	addSequential(new DriveAuto(0.1, 0, 0));
//    	addSequential(new DriveAuto(0.3, 0.8, 0.8));
//
//    	addSequential(new DriveUntilClawTrig(-0.6,-0.6));
////    	addSequential(new AutonomousArmUp(0.5));
//    	addSequential(new DriveAuto(0.3, 0.8, 0.8));
//    	addSequential(new ClawToggle());
//    	addSequential(new DriveAuto(0.1, 0, 0));
//    	addSequential(new DriveAuto(0.3, 0.8, 0.8));
//
//    	addSequential(new DriveAuto(0.7, 0.8, 0.8));
//
//    	addSequential(new DriveUntilClawTrig(-0.6,-0.6));
////    	addSequential(new AutonomousArmUp(0.5));
//    	addSequential(new DriveAuto(0.3, 0.8, 0.8));
//
//    	
//    	//DOUBLE BIN GRAB [THEORETICAL]
////    	addSequential(new GyroRotate(-60.0, 1.4));
////    	addParallel(new AutonomousArmExtend(0.5));
////    	addSequential(new AutonomousMobility(1.5, 0, 0));
////    	addSequential(new GyroRotate(-42.0, 2));
////    	addSequential(new AutonomousMobility(1.5, 0, 0));
////    	addSequential(new GyroRotate(-68.0, 2));
////    	addSequential(new AutonomousMobility(0.7, 0.8, 0.8));
////    	addSequential(new GyroRotate(60.0, 2.2));
//    }
//}
