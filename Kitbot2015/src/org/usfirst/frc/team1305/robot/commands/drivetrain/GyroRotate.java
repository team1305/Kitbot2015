//package org.usfirst.frc.team1305.robot.commands.drivetrain;
//
//import org.usfirst.frc.team1305.robot.DriveTrainPIDOutput;
//import org.usfirst.frc.team1305.robot.Robot;
//
//import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.Preferences;
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// * rotate the robot a specified number of degrees. An optional timeout may be 
// * specified.
// */
//public class GyroRotate extends Command {
//	//PID constants
//	//TODO: figure out constants.
//	private final double P = 0.08; //0.03 //0.055
//	private final double I = 0.0068; //0.00045;
//	private final double D = 0.18; //0.09  //0.15
//
//	private final double AUTO_TIMEOUT_PERIOD = 0.4; //seconds
//	private final double TOLERANCE           = 2.5; //degrees
//	
//	private PIDController pid;
//	
//	//timeout timer
//	private Timer autoTimer = new Timer();
//	private Timer manuTimer = new Timer();
//	private double originalAngle;
//	private double offset;
//	private double timeout;
//	private boolean usingManualTimeout;
//
//	/**
//	 * Rotate the robot a specified number of degrees. The command will never
//	 * time out and will 
//	 * @param offset The number of degrees to turn. A positive value corresponds
//	 * to clockwise, and a negative value is counterclockwise.
//	 */
//    public GyroRotate(double offset) {
//    	requires(Robot.drivetrain);
//    	this.offset        = offset;
//    	this.timeout       = 0.0;
//    	usingManualTimeout   = false;
//    	
//    	pid = new PIDController(P, 
//    							I, 
//    							D, 
//    							Robot.gyroscope, 
//    							new DriveTrainPIDOutput());
//    	pid.setOutputRange(-0.7,  0.7);
//    }
//    
//	/**
//	 * Rotate the robot a specified number of degrees. The command will never
//	 * time out and will 
//	 * @param offset The number of degrees to turn. A positive value corresponds
//	 * to clockwise, and a negative value is counterclockwise.
//	 * @param timeout the maximum time the program will operate for.
//	 */
//    public GyroRotate(double offset, double timeout) {
//    	requires(Robot.drivetrain);
//    	this.offset        = offset;
//    	this.timeout 	   = timeout;
//    	usingManualTimeout   = true;
//    	
//    	pid = new PIDController(P,
//    							I,
//    							D, 
//    							Robot.gyroscope, new DriveTrainPIDOutput());
//    	pid.setOutputRange(-0.7,  0.7);
//    }
//    
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	this.originalAngle = Robot.gyroscope.getRawAngle();
////    	Preferences prefs = Preferences.getInstance();
////    	prefs.save();
////    	pid.setPID(prefs.getDouble("P",0), prefs.getDouble("I", 0), prefs.getDouble("D", 0));
//    	pid.setSetpoint(originalAngle + offset);
//    	pid.enable();
//    	autoTimer.reset();
//    	autoTimer.start();
//    	manuTimer.reset();
//    	manuTimer.start();
//    	
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	//take the computed output value of the pid controller and apply
//    	//to the wheels.
//    	double val = pid.get();
//    	Robot.drivetrain.tankDrive(-val, +val);
//    	
//    	//if we leave the tolerance zone, then we reset the auto timer
//    	if(Math.abs(pid.getError()) > TOLERANCE ){
//    		autoTimer.reset();
//    	}
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//    	//if we're using auto timeout and we've gone long enough without 
//    	//leaving the tolerance zone
//        if(autoTimer.get() > AUTO_TIMEOUT_PERIOD) return true;
//        
//        //if the manual timer has expired
//        else if(usingManualTimeout && manuTimer.get() > timeout) return true;
//        
//        else return false;
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    	autoTimer.stop();
//    	pid.disable();
//    	autoTimer.reset();
//    	manuTimer.stop();
//    	manuTimer.reset();
//    	Robot.drivetrain.tankDrive(0.0, 0.0);
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    	autoTimer.stop();
//    	pid.disable();
//    	autoTimer.reset();
//    	manuTimer.stop();
//    	manuTimer.reset();
//    	Robot.drivetrain.tankDrive(0.0, 0.0);
//    }
//}
