package org.usfirst.frc.team1305.robot.commands.newarm;

import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.subsystems.NewArm.ArmMode;
import org.usfirst.frc.team1305.robot.subsystems.NewArm.Preset;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Coommand which manually controls the arm. This command does not distinguish
 * between manual wrist control and automatic wrist control.
 */
public class ArmGoPreset extends Command {
	private Preset preset;
	private Timer t = new Timer();
	public double timeout;
	private boolean usingTimer = false;
	private ArmMode oldMode;
	
	/**
	 * Command which manually controls the arm. This command does not distinguish
	 * between manual wrist control and automatic wrist control. It is assumed
	 * that this command will be used as a WhileHeld, or have an assosciated
	 * timeout.
	 * @param p the preset to apply.
	 */
    public ArmGoPreset(Preset p) {
       requires(Robot.newArm);
       preset = p;
       usingTimer = false;
    }
    
	/**
	 * Command which manually controls the arm. This command does not distinguish
	 * between manual wrist control and automatic wrist control. It is assumed
	 * that this command will be used as a WhileHeld, or have an assosciated
	 * timeout.
	 * @param p the preset to apply.
	 * @param timeout the maximum time to run the command.
	 */
    public ArmGoPreset(Preset p, double timeout){
    	requires(Robot.newArm);
    	preset = p;
    	usingTimer = true;
    	this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	t.start();
    	oldMode = Robot.newArm.getMode();
    	Robot.newArm.setMode(ArmMode.preset);
    	Robot.newArm.setPreset(preset);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.newArm.update(0, 0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(usingTimer && t.get() > timeout) return true;
    	else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.newArm.stopMotors();
    	Robot.newArm.setMode(oldMode);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.newArm.stopMotors();
    	Robot.newArm.setMode(oldMode);

    }
}
