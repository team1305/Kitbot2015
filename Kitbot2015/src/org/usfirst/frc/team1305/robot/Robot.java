
package org.usfirst.frc.team1305.robot;

import org.usfirst.frc.team1305.robot.commands.autonomous.AutoOneBinStep;
import org.usfirst.frc.team1305.robot.commands.autonomous.AutoOneBinTravel;
<<<<<<< HEAD
import org.usfirst.frc.team1305.robot.commands.autonomous.AutoSmasher;
=======
import org.usfirst.frc.team1305.robot.commands.autonomous.AutoTurnNGo;
>>>>>>> branch 'master' of https://github.com/team1305/kitbot2015.git
import org.usfirst.frc.team1305.robot.commands.autonomous.AutoTwoBinStep;
import org.usfirst.frc.team1305.robot.commands.autonomous.AutonomousDance;
import org.usfirst.frc.team1305.robot.commands.autonomous.Wait;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveEncoder;
import org.usfirst.frc.team1305.robot.commands.drivetrain.DriveGyroRotate;
import org.usfirst.frc.team1305.robot.subsystems.Arm;
import org.usfirst.frc.team1305.robot.subsystems.Claw;
import org.usfirst.frc.team1305.robot.subsystems.Dash;
import org.usfirst.frc.team1305.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1305.robot.subsystems.EJSmasher;
import org.usfirst.frc.team1305.robot.subsystems.Gyroscope;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	

	public static OI oi;

	public static Drivetrain drivetrain;
	public static Gyroscope gyroscope;
	public static Claw claw;
	public static Dash dash;
	public static Arm arm;
	public static EJSmasher ejSmasher;

	//camera server aka camera declaration
	CameraServer server;
    //Command autonomousCommand = new AutonomousMasterGroup();
	Command autonomousCommand;
	SendableChooser autoChooser = new SendableChooser();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	//instantiating all them subsystems.
		System.out.println("INFO: Creating subsystems...");
		drivetrain = new Drivetrain();
		gyroscope  = new Gyroscope();
		claw       = new Claw();
		dash 	   = new Dash();
		arm        = new Arm();
		ejSmasher = new EJSmasher();
		
		System.out.println("INFO: Creating OI...");
		oi = new OI();
		
		System.out.println("INFO: Creating Autonomous Chooser...");

		/* ===============================================
		 * =====ADD Autonomous Routines here!=============
		 * ===============================================
		 */

		autoChooser.addDefault("One Bin step auto", new AutoOneBinStep());
		autoChooser.addObject("Two bin step auto", new AutoTwoBinStep());
//		autoChooser.addObject("One bin step ABORT", new AutoOneBinAbort());
		autoChooser.addObject("One bin step Travel", new AutoOneBinTravel());
		autoChooser.addObject("Can Burgler", new AutoSmasher());
		autoChooser.addObject("Drive Forward", new DriveEncoder(10, 0.3));
		autoChooser.addObject("Drive Backward", new DriveEncoder(-10, 0.3));
		autoChooser.addObject("Turn 90", new DriveGyroRotate(90));
		autoChooser.addObject("GyroTest", new AutoTurnNGo());
		autoChooser.addObject("Null auto", new Wait(1));
		autoChooser.addObject("Dance auto", new AutonomousDance());
		
		//================================================
		
		SmartDashboard.putData("Autochooser", autoChooser);
		System.out.println("INFO: Robot init complete. Waiting for match to start.");
    }

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
	}

    public void autonomousInit() {
    	autonomousCommand = (Command) autoChooser.getSelected();
    	
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
