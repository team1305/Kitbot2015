package org.usfirst.frc.team1305.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.OI;
//import org.usfirst.frc.team1305.robot.commands.arm.MoveShoulderCommand;
import org.usfirst.frc.team1305.robot.commands.arm.ArmDefaultCommand;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;

/**
 *
 */
public class Arm extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private AnalogPotentiometer potShoulder = new AnalogPotentiometer(RobotMap.ANALOG_POT_SHOULDER);
	private AnalogPotentiometer potElbow = new AnalogPotentiometer(RobotMap.ANALOG_POT_ELBOW);
	private AnalogPotentiometer potWrist = new AnalogPotentiometer(RobotMap.ANALOG_POT_WRIST);
	
//	private int newXClawPosition, prevXClawPosition;
//	private int newYClawPosition, prevYClawPosition;
//	private int X_AXIS_MAX = 30, X_AXIS_MIN = 0, Y_AXIS_MIN = -14, Y_AXIS_MAX = 30;
//	private int X_AXIS_FACTOR = 10, Y_AXIS_FACTOR = 10;
//	private double hypot;
//	private double BICEP_LENGTH = 38, FOREARM_LEN = 33;
	private double MIN_SHOULDER_POT = 0.12;
	private double MAX_SHOULDER_POT = 0.495;
	private double MIN_ELBOW_POT = 0.1;
	private double MAX_ELBOW_POT = 0.5;
	private double MIN_WRIST_POT = 0.13;
	private double MAX_WRIST_POT = 0.36;
	private double ELBOW_DIR_TO_MOTOR_DIR = 1; // -1 if positive motor causes negative elbow dir
	private double SHOULDER_DIR_TO_MOTOR_DIR = 1; // -1 if positive motor causes negative shoulder dir
	private double WRIST_DIR_TO_MOTOR_DIR = 1; // -1 if positive motor causes negative wrist dir
	private CANTalon shoulderMotor = new CANTalon(RobotMap.CAN_DEVICE_SHOULDER);
	private CANTalon elbowMotor = new CANTalon(RobotMap.CAN_DEVICE_ELBOW);
	private CANTalon wristMotor = new CANTalon(RobotMap.CAN_DEVICE_WRIST);
//	private double WristAngleToPotRatio = 180;
//	private double ShoulderAngleToPotRatio = 180;
//	private double ElbowAngleToPotRatio = 180;
	private double targetWristPot;
	private double targetWristAngle;
	private boolean isWristAuto = true;
	
	public Arm(){
		
	}
	
	// Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Arm is Initialized");
    	SmartDashboard.putNumber("Shoulder MIN Pot", MIN_SHOULDER_POT);    
    	SmartDashboard.putNumber("Shoulder MAX Pot", MAX_SHOULDER_POT);    
    	SmartDashboard.putNumber("Elbow MIN Pot", MIN_ELBOW_POT);    
    	SmartDashboard.putNumber("Elbow MAX Pot", MAX_ELBOW_POT);
    	SmartDashboard.putNumber("Wrist MIN Pot", MIN_WRIST_POT);    
    	SmartDashboard.putNumber("Wrist MAX Pot", MAX_WRIST_POT); 
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ArmDefaultCommand());
    }
    
    private double GetShoulderAngle()
    {
    	//xxxmeasurement of pot to angle produced following formula =178 * G2 - 3.53
    	//measurement of pot to angle produced following formula = =(-178 * C8) + 125.83

    	updateSmartDashboard("Shoulder Angle Calc", -178 * getShoulderPot() + 130.00); //125.83);
    	return (-178 * getShoulderPot() + 125.83);
    }
    
    private double GetElbowAngle()
    {
    	//measurement of pot to angle produced following formula =(-274  * G11) + 162
    	
    	updateSmartDashboard("Elbow Angle Calc", (-274  * getElbowPot()) + 165);
    	return (-274  * getElbowPot()) + 165;
    	//inverse formula....return -.0036 * getElbowPot() + .5891;
    }
    
    private double getShoulderPot(){
    	return potShoulder.get();
    }
    
    private double getElbowPot(){
    	return potElbow.get();
    }
    
    public double getWristPot(){
    	return potWrist.get();
    }
   
    private double calcTargetWristPot()
    {
    	targetWristAngle = GetElbowAngle() + GetShoulderAngle(); // - 90;
    	updateSmartDashboard();
    	return ConvertWristAngleToPot(targetWristAngle);
    }
    
    private double ConvertWristAngleToPot(double wristAngle)
    {
    	//measurement of pot to angle produced following formula = .0019x + .0991

    	return (0.0019 * wristAngle) + 0.0991; //-  WristAngleToPotRatio;
    }
    
    public void MoveShoulder(double yAxis){
    	//min extension 0.12, max extension 0.465
    	
    	if(getShoulderPot() <= MIN_SHOULDER_POT){
    		shoulderMotor.set(-Math.abs(yAxis)/4);
    	}
    	else if(getShoulderPot() >= MAX_SHOULDER_POT){
    		shoulderMotor.set(Math.abs(yAxis)/4);
    	}
    	else{
    		shoulderMotor.set(yAxis);
    	}
    	updateSmartDashboard("Shoulder Joystick", yAxis);
    }
    
    public void MoveElbow(double yAxis){
    	//min 0.1 max 0.5
    	
    	if(getElbowPot() <= MIN_ELBOW_POT){
    		elbowMotor.set(-Math.abs(yAxis)/4);
    	}
    	else if(getElbowPot() >= MAX_ELBOW_POT){
    		elbowMotor.set(Math.abs(yAxis)/4);
    	}
    	else{
    		elbowMotor.set(yAxis);
    	}
    	updateSmartDashboard("Elbow Joystick", yAxis);
    	System.out.println("ShoulderPot = " + getShoulderPot());
    	System.out.println("ElbowPot = " + getElbowPot());
    	
    }
    
    private void updateSmartDashboard()
    {
    	SmartDashboard.putNumber("Shoulder Pot", getShoulderPot());
    	SmartDashboard.putNumber("Elbow Pot", getElbowPot());
    	SmartDashboard.putNumber("Wrist Pot", getWristPot());
    	SmartDashboard.putBoolean("Is Wrist Auto?", isWristAuto);
    	
    	SmartDashboard.putNumber("Target Wrist Angle", targetWristAngle);
    	SmartDashboard.putNumber("Wrist Pot Calc", targetWristPot);
		SmartDashboard.putNumber("Wrist Motor Suggestion", (getWristPot()-targetWristPot)/getWristPot());
		
		SmartDashboard.putNumber("Shoulder Speed", shoulderMotor.get());
		SmartDashboard.putNumber("Elbow Speed", elbowMotor.get());
		SmartDashboard.putNumber("Extended Wrist Speed", wristMotor.get());
    }
    
    private void updateSmartDashboard(String itemLabel, double itemValue)
    {
    	SmartDashboard.putNumber(itemLabel, itemValue);
    	updateSmartDashboard();
    }
    
    private void updateSmartDashboard(String itemLabel, String itemValue)
    {
    	SmartDashboard.putString(itemLabel, itemValue);
    	updateSmartDashboard();
    }
    
    public void MoveWrist(double yAxis){
    	//if in auto, ignore; otherwise respond
    	if (! isWristAuto)
    	{
    	 	moveWristDirectly(yAxis);
    	}
    		
    }
    
    private void moveWristDirectly(double yAxis){
    	//min 0.12 max 0.37
    	targetWristPot = calcTargetWristPot();
		//TODO:  put min/max logic back in once
		//potentiometer is fixed
    	wristMotor.set(-yAxis);
    	
//    	if(getWristPot() <= MIN_WRIST_POT){
//    		WristMotor.set(-Math.abs(yAxis)/4);
//    	}
//    	else if(getWristPot() >= MAX_WRIST_POT){
//    		WristMotor.set(Math.abs(yAxis)/4);
//    	}
//    	else{
//    		WristMotor.set(-yAxis);
//    	}
    	updateSmartDashboard();
    }

    public void MoveWristAutomatically()
    {
    	//if wrist is not in auto mode, just ignore
    	if (isWristAuto){
    		targetWristPot = calcTargetWristPot();
        	
        	if(getWristPot() != targetWristPot){
        		//WristMotor.set((getWristPot()-targetWristPot)*24);
        		//calc fraction it is away, and send as joystick signal
        		moveWristDirectly(-20 * (getWristPot()-targetWristPot)/getWristPot());
    		}
        	updateSmartDashboard();	
    	}
    	
    }
    
    public void toggleWristAutoManu(){
    	isWristAuto = !isWristAuto;
    }
    
    private boolean isBetween(double value1, double value2)
    {
    	return value1 == value2;
    }
    
    public void StopShoulder()
    {
    	shoulderMotor.set(0);
    }
    
    public void StopElbow()
    {
    	elbowMotor.set(0);
    }
    
    public void StopWrist()
    {
    	wristMotor.set(0);
    }
    
    public void MoveArm(int xAxisDir, int yAxisDir)
    {
//    	newXClawPosition = prevXClawPosition + xAxisDir * X_AXIS_FACTOR;
//    	if (newXClawPosition > X_AXIS_MAX) {newXClawPosition = X_AXIS_MAX;}
//    		else if (newXClawPosition < X_AXIS_MIN) {newXClawPosition = X_AXIS_MIN;}
//    	
//    	newYClawPosition = prevYClawPosition + yAxisDir * Y_AXIS_FACTOR;
//    	if (newYClawPosition > Y_AXIS_MAX) {newXClawPosition = Y_AXIS_MAX;}
//    		else if (newYClawPosition < Y_AXIS_MIN) {newXClawPosition = Y_AXIS_MIN;}
//    	
//    	hypot = Math.sqrt(newXClawPosition * newXClawPosition + newYClawPosition * newYClawPosition);
//    	
//    	CalcElbowPot(newXClawPosition, newYClawPosition, hypot);
//    	CalcShoulderPot(newXClawPosition, newYClawPosition, hypot);
    }
    
    private void CalcElbowPot(int newX, int newY, double hypotenuse)
    {
    	//hypotenuse is opposite elbow, so 
    	//SSS theorem says elbow angle = invCos (bicep ^ 2 + forearm ^ 2 - hypot ^ 2) / (2 * bicep *forearm)
    	//double newElbowAngleTarget;
    	//newElbowAngleTarget = Math.acos((BICEP_LENGTH * BICEP_LENGTH + FOREARM_LEN * FOREARM_LEN - hypotenuse * hypotenuse)/
    	//		(2 * BICEP_LENGTH * FOREARM_LEN));
    }
    
    private void CalcShoulderPot(int newX, int newY, double hypotenuse)
    {
    	//forearm is opposite shoulder, so
    	//SSS theorem says shoulder angle = invCos ((bicep ^ 2 + hypot ^ 2 - forearm ^ 2) / (2 * bicep * hypot))
    	//double newShoulderAngleTarget;
    	//newShoulderAngleTarget = Math.acos((BICEP_LENGTH * BICEP_LENGTH + hypotenuse * hypotenuse - FOREARM_LEN * FOREARM_LEN)/
    	//		(2 * BICEP_LENGTH * hypotenuse));
    	
    }
    
    public void ArmPresets(String preset){

    	if(preset == OI.ARM_PRESET_EXTENDED){
    		
    		if(getShoulderPot() != 0.45){
    			shoulderMotor.set((getShoulderPot()-0.45)*24);
    		}
    		if(getElbowPot() != 0.12){
    			elbowMotor.set((getElbowPot()-0.12)*24);
    		}
    		if(getWristPot() != 0.42){
    			wristMotor.set((getWristPot()-0.42)*24);
    		}
    		//0.22
    	} 
    	else if(preset == OI.ARM_PRESET_TRANSPORT){
    		if(getShoulderPot() != 0.15){
    			shoulderMotor.set((getShoulderPot()-0.15)*24);
    		}
    		if(getElbowPot() != 0.44){
    			elbowMotor.set((getElbowPot()-0.44)*24);
    		}
    		if(getWristPot() != 0.37){
    			wristMotor.set((getWristPot()-0.37)*24);
    		}
    	}
    	else if(preset == OI.ARM_PRESET_MAX_STACK){
    		if(getShoulderPot() != 0.17){
    			shoulderMotor.set((getShoulderPot()-0.17)*24);
    		}
    		if(getElbowPot() != 0.10){
    			elbowMotor.set((getElbowPot()-0.10)*24);
    		}
    		if(getWristPot() != 0.52){
    			wristMotor.set((getWristPot()-0.52)*24);
    		}
    	}
    	updateSmartDashboard("Preset Is", preset);
    }
}
