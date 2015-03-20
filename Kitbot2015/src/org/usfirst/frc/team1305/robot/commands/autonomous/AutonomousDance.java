package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Movements in autonomous, calls Drivetrain subsystem.
 */
public class AutonomousDance extends Command {
	private final double ROBOT_DANCE_DURATION = 1;

	private int currentState = 0;
	Timer t = new Timer();

    public AutonomousDance() {
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	currentState = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (currentState){
        case 0:
            t.start();

            currentState++;
            break;
        case 1:
            if (t.get()>=ROBOT_DANCE_DURATION)
            {

                currentState++;
                t.reset();
                t.start();
            }
            Robot.drivetrain.tankDrive(-0.5,0.5);
            break;
        case 2:
        	if (t.get()>=ROBOT_DANCE_DURATION)
            {
                currentState++;
                t.reset();
                t.start();
            }
            Robot.drivetrain.tankDrive(0.5,-0.5);
        	break;
        case 3:
        	if (t.get()>=ROBOT_DANCE_DURATION)
            {
                currentState++;
                t.reset();
                t.start();
            }
        	Robot.drivetrain.tankDrive(-0.3,0.3);
        	break;
        case 4:
        	break;
    	}
    }    


    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (currentState == 4);
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    	t.stop();
    	t.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.stop();
    	t.stop();
    	t.reset();
    }
}
