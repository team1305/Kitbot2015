
package org.usfirst.frc.team1305.robot;

import org.usfirst.frc.team1305.robot.commands.autonomous.AutonomousMasterGroup;
import org.usfirst.frc.team1305.robot.subsystems.Arm;
import org.usfirst.frc.team1305.robot.subsystems.CameraServo;
import org.usfirst.frc.team1305.robot.subsystems.Claw;
import org.usfirst.frc.team1305.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1305.robot.subsystems.Elevator;
import org.usfirst.frc.team1305.robot.subsystems.Forks;
import org.usfirst.frc.team1305.robot.subsystems.Gyroscope;
import org.usfirst.frc.team1305.robot.subsystems.PowerPanel;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static final Drivetrain drivetrain	    = new Drivetrain();
	public static final PowerPanel powerPanel   	= new PowerPanel();
	public static final Gyroscope gyroscope         = new Gyroscope();
	public static final Claw claw                   = new Claw();

	//camera server aka camera declaration
	CameraServer server;


	public static final Arm arm                 = new Arm();
	public static final Forks forks             = new Forks();
	public static final Elevator elevator       = new Elevator();
	public static final CameraServo cameraServo = new CameraServo();


    Command autonomousCommand = new AutonomousMasterGroup();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	//set oi
		oi = new OI();
		//Gets instances of camera from camera server
		//server = CameraServer.getInstance();
		//set quality of video feed
        //server.setQuality(80);
        //the camera name (ex "cam0") can be found through the roborio web interface
        //starts camera feed
        //TODO: REMEMBER THIS
        //server.startAutomaticCapture("cam0");
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
    	//TODO: check if this is correct or just do command.start()
    	//Scheduler.getInstance().add(new ReInit());
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
//        SmartDashboard.putData(Scheduler.getInstance());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
