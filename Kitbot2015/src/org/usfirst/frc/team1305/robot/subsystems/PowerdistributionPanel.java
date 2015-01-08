
package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.commands.getPowerMetric;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PowerdistributionPanel extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private double PDPtemp;
	private double PDPeneg;
	private double PDPpow;
	private double PDPvolt;
	private double motor1;
	private double motor2;
	private double motor3;
	private double motor4;
	private double motor5;
	private double motor6;
	
	private PowerDistributionPanel PDP = new PowerDistributionPanel();
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new getPowerMetric());
    }
    @SuppressWarnings("deprecation")
	public void PowerMatric(){
    	double PDPtemp = PDP.getTemperature();
    	double PDPeneg = PDP.getTotalEnergy();
    	double PDPpow  = PDP.getTotalPower();
    	double PDPvolt = PDP.getVoltage();
    	double motor1  = PDP.getCurrent(0);
    	double motor2  = PDP.getCurrent(1);
    	double motor3  = PDP.getCurrent(2);
    	double motor4  = PDP.getCurrent(3);
    	double motor5  = PDP.getCurrent(14);
    	double motor6  = PDP.getCurrent(15);
    	SmartDashboard.putDouble("PDP Temp. :", PDPtemp);
    	SmartDashboard.putDouble("PDP Total Energy :", PDPeneg);
    	SmartDashboard.putDouble("PDP Total Power :", PDPpow);
    	SmartDashboard.putDouble("PDP Voltage :", PDPvolt);
    	SmartDashboard.putDouble("Motor1", motor1);
    	SmartDashboard.putDouble("Motor2", motor2);
    	SmartDashboard.putDouble("Motor3", motor3);
    	SmartDashboard.putDouble("Motor4", motor4);
    	SmartDashboard.putDouble("Motor5", motor5);
    	SmartDashboard.putDouble("Motor6", motor6);
    			}
}

