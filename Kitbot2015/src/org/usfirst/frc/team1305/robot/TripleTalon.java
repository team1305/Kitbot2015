package org.usfirst.frc.team1305.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class TripleTalon implements SpeedController {
	
	private Talon t1;
	private Talon t2;
	private Talon t3;
	
	public TripleTalon(int portA, int portB, int portC){
		t1 = new Talon(portA);
		t2 = new Talon(portB);
		t3 = new Talon(portC);
	}

	public void pidWrite(double output) {
		t1.pidWrite(output);
		t2.pidWrite(output);
		t3.pidWrite(output);

	}

	public double get() {
		return	t1.get();
	}

	public void set(double speed, byte syncGroup) {
		t1.set(speed, syncGroup);
		t2.set(speed, syncGroup);
		t3.set(speed, syncGroup);
	}

	public void set(double speed) {
		t1.set(speed);
		t2.set(speed);
		t3.set(speed);

	}

	public void disable() {
		t1.disable();
		t2.disable();
		t3.disable();

	}

}
