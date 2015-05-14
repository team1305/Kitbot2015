//package org.usfirst.frc.team1305.robot.commands.EJSmasher;
//
//import org.usfirst.frc.team1305.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// * Automatically retract the smasher by winching until the limit trigger is hit.
// * 
// */
//public class SmasherAutoRetract extends Command {
//
//    public SmasherAutoRetract() {
//    	requires(Robot.ejSmasher);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	Robot.ejSmasher.winch_up();
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return Robot.ejSmasher.getTrigger();
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    	Robot.ejSmasher.winch_stop();
//    	Robot.ejSmasher.trigger_unrelease();
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    	Robot.ejSmasher.winch_stop();
//    }
//}
