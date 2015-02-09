package org.usfirst.frc.team1305.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.arm.ArmDefaultCommand;
import org.usfirst.frc.team1305.robot.commands.arm.MoveShoulderCommand;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 *
 */
public class Arm extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private AnalogPotentiometer potShoulder = new AnalogPotentiometer(RobotMap.ANALOG_POT_SHOULDER);
	private AnalogPotentiometer potElbow = new AnalogPotentiometer(RobotMap.ANALOG_POT_ELBOW);
	
	private int newXClawPosition, prevXClawPosition;
	private int newYClawPosition, prevYClawPosition;
	private int X_AXIS_MAX = 30, X_AXIS_MIN = 0, Y_AXIS_MIN = -14, Y_AXIS_MAX = 30;
	private int X_AXIS_FACTOR = 10, Y_AXIS_FACTOR = 10;
	private double hypot;
	private double BICEP_LENGTH = 38, FOREARM_LEN = 33;
	private double MIN_SHOULDER_POT = 0.12;
	private double MAX_SHOULDER_POT = 0.465;
	private double MIN_WRIST_POT = 0.1;
	private double MAX_WRIST_POT = 0.5;
	private CANTalon ShoulderMotor = new CANTalon(RobotMap.CAN_DEVICE_SHOULDER);
	private CANTalon ElbowMotor = new CANTalon(RobotMap.CAN_DEVICE_ELBOW);
	private CANTalon WristMotor = new CANTalon(RobotMap.CAN_DEVICE_WRIST);
	
	public Arm(){
		//SmartDashboard.putNumber("Elbow Pot", getElbowPot());    	
		//SmartDashboard.putNumber("Shoulder Pot", getShoulderPot());
	}
	
	// Called just before this Command runs the first time
    protected void initialize() {
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new MoveShoulderCommand());
    }
    
    public double getShoulderPot(){
    	return potShoulder.get();
    }
    
    public double getElbowPot(){
    	return potElbow.get();
    }
    
    public void MoveArm(int xAxisDir, int yAxisDir)
    {
    	newXClawPosition = prevXClawPosition + xAxisDir * X_AXIS_FACTOR;
    	if (newXClawPosition > X_AXIS_MAX) {newXClawPosition = X_AXIS_MAX;}
    		else if (newXClawPosition < X_AXIS_MIN) {newXClawPosition = X_AXIS_MIN;}
    	
    	newYClawPosition = prevYClawPosition + yAxisDir * Y_AXIS_FACTOR;
    	if (newYClawPosition > Y_AXIS_MAX) {newXClawPosition = Y_AXIS_MAX;}
    		else if (newYClawPosition < Y_AXIS_MIN) {newXClawPosition = Y_AXIS_MIN;}
    	
    	hypot = Math.sqrt(newXClawPosition * newXClawPosition + newYClawPosition * newYClawPosition);
    	
    	CalcElbowPot(newXClawPosition, newYClawPosition, hypot);
    	CalcShoulderPot(newXClawPosition, newYClawPosition, hypot);
    }
    
    public void MoveShoulder(double yAxis){
    	//min extension 0.08, max extension 0.47
    	SmartDashboard.putNumber("Shoulder Pot", getShoulderPot());
    	SmartDashboard.putNumber("Elbow Pot", getElbowPot());   
    	if(getShoulderPot() <= MIN_SHOULDER_POT){
    		ShoulderMotor.set(-Math.abs(yAxis)/4);
    	}
    	else if(getShoulderPot() >= MAX_SHOULDER_POT){
    		ShoulderMotor.set(Math.abs(yAxis)/4);
    	}
    	else{
    	ShoulderMotor.set(yAxis);
    	}
    }
    
    public void MoveElbow(double yAxis){
    	//min 0.13 max 0.5
    	ElbowMotor.set(yAxis);
    	if(getElbowPot() <= MIN_WRIST_POT){
    		ElbowMotor.set(-Math.abs(yAxis)/4);
    	}
    	else if(getElbowPot() >= MAX_WRIST_POT){
    		ElbowMotor.set(Math.abs(yAxis)/4);
    	}
    	else{
    	ElbowMotor.set(yAxis);
    	}
    	SmartDashboard.putNumber("Shoulder Pot", getShoulderPot());
    	SmartDashboard.putNumber("Elbow Pot", getElbowPot());   
    }
    
    public void MoveWrist(double yAxis){
    	WristMotor.set(yAxis);
    }
    
    public void MoveArmUpDown(int yAxisDir)
    {
    	//newYClawPosition = prevYClawPosition + yAxisDir * Y_AXIS_FACTOR;
    	//if (newYClawPosition > Y_AXIS_MAX) {newXClawPosition = Y_AXIS_MAX;}
    		//else if (newYClawPosition < Y_AXIS_MAX) {newXClawPosition = Y_AXIS_MIN;}
    	
    	//CalcElbowPot(newXClawPosition, newYClawPosition);
    	//CalcShoulderPot(newXClawPosition, newYClawPosition);
    }
    
    public void MoveArmInOut(int xAxisDir)
    {
    	//newXClawPosition = prevXClawPosition + xAxisDir * X_AXIS_FACTOR;
    	//if (newXClawPosition > X_AXIS_MAX) {newXClawPosition = X_AXIS_MAX;}
    		//else if (newXClawPosition < X_AXIS_MAX) {newXClawPosition = X_AXIS_MIN;}
    	
    	//CalcElbowPot(newXClawPosition, newYClawPosition);
    	//CalcShoulderPot(newXClawPosition, newYClawPosition);
    }
    
    private void CalcElbowPot(int newX, int newY, double hypotenuse)
    {
    	//hypotenuse is opposite elbow, so 
    	 //SSS theorem says elbow angle = invCos (bicep ^ 2 + forearm ^ 2 - hypot ^ 2) / (2 * bicep *forearm)
    	double newElbowAngleTarget;
    	newElbowAngleTarget = Math.acos((BICEP_LENGTH * BICEP_LENGTH + FOREARM_LEN * FOREARM_LEN - hypotenuse * hypotenuse)/
    			(2 * BICEP_LENGTH * FOREARM_LEN));
    }
    
    private void CalcShoulderPot(int newX, int newY, double hypotenuse)
    {
    	//forearm is opposite shoulder, so
    	//SSS theorem says shoulder angle = invCos ((bicep ^ 2 + hypot ^ 2 - forearm ^ 2) / (2 * bicep * hypot))
    	double newShoulderAngleTarget;
    	newShoulderAngleTarget = Math.acos((BICEP_LENGTH * BICEP_LENGTH + hypotenuse * hypotenuse - FOREARM_LEN * FOREARM_LEN)/
    			(2 * BICEP_LENGTH * hypotenuse));
    	
    }
    
// // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return false;
//    }
//    
// // Called once after isFinished returns true
//    protected void end() {
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
}

