/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team1305.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Root 1
 */
public class AutonomousMasterGroup extends CommandGroup {
    double hotCount;
    
    public AutonomousMasterGroup() {
    	addSequential(new AutonomousMobility());
    	addSequential(new AutonomousDance());
    	
    	
//        //Changes the autonomous if the robot is in the middle of the field.
//        if (DriverStation.getInstance().getLocation() == 2){
//        addParallel(new CatapultLockNLoad());
//        addSequential(new AutonomousRotate());
//        addSequential(new CameraActive());
//            if (hotCount >= 2){
//                 // System.out.println("Detected high amount of Hot Goals");
//                SmartDashboard.putString("HEY THIS WORKS", "BOO");
//                addSequential(new AutonomousFire());
//                addSequential(new AutonomousMobility());
//            }
//            else{
//                //System.out.println("No hot goal");
//                SmartDashboard.putString("HEY THIS WORKS", "HEY");
//                addSequential(new AutonomousMobility());
//                addSequential(new AutonomousFire());
//            }
//        
//        }
//        //Triggered if goal is directly in front of the robot.
//        else{
//          //Starts analyzing the target, while opening the claw and loading the catapult.
//          //addParallel(new CameraActive());
//          //addParallel(new ClawOpen());
//          SmartDashboard.putString("AUTO STATE", "Locking and Loading");
//          addSequential(new CatapultLockNLoad());
//          SmartDashboard.putString("AUTO STATE", "Closing claws");
//          //Closes claws to clamp ball and prevent it falling off.
//          addSequential(new ClawClose());
//          SmartDashboard.putString("AUTO STATE", "Moving");
//          //Moves 
//          addSequential(new AutonomousMobility());
//          SmartDashboard.putNumber("What I read", hotCount);
//          SmartDashboard.putString("AUTO STATE", "Reading camera stuff");
//          hotCount = SmartDashboard.getNumber("Hot Count");
//          SmartDashboard.putNumber("What I read", hotCount);
//          addSequential(new ClawPark());
//          //NOT NEEDED
//        if (hotCount >= 2){
//           // System.out.println("Detected high amount of Hot Goals");
//            SmartDashboard.putString("HEY THIS WORKS", "BOO");
//            addSequential(new AutonomousFire());
//            
//        }
//        else{
//            //System.out.println("No hot goal");
//            SmartDashboard.putString("HEY THIS WORKS", "HEY");
//            addSequential(new AutonomousFire());
//        }
        }
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }

