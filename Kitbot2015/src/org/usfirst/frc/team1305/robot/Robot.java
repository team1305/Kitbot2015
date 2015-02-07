
package org.usfirst.frc.team1305.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team1305.robot.commands.powerpanel.getPowerMetric;
import org.usfirst.frc.team1305.robot.subsystems.Accelerometer;
import org.usfirst.frc.team1305.robot.subsystems.Claw;
import org.usfirst.frc.team1305.robot.subsystems.Drivebase;
import org.usfirst.frc.team1305.robot.subsystems.Drivetrain;
//import org.usfirst.frc.team1305.robot.subsystems.Gyroscope;
import org.usfirst.frc.team1305.robot.subsystems.PowerPanel;
//import org.usfirst.frc.team1305.robot.subsystems.Arm;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static final Drivebase Drivetrain = new Drivebase();
	public static final PowerPanel powerPanel = new PowerPanel();
	public static final Accelerometer accelerometer = new Accelerometer();
	//public static final Gyroscope gyroscope = new Gyroscope();
	public static final Claw IGrab = new Claw();
	
	CameraServer server;
	CameraServer server2;
	
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		server = CameraServer.getInstance();
		server2 = CameraServer.getInstance();
        server.setQuality(80);
        server2.setQuality(80);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam0");
        server2.startAutomaticCapture("cam1");
        // instantiate the command used for the autonomous period
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
