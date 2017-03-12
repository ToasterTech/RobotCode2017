package org.usfirst.frc.team5332.robot;

import org.usfirst.frc.team5332.robot.toaster.base.ToasterCommandBase;
import org.usfirst.frc.team5332.robot.toaster.base.ToasterHardwareBase;
import org.usfirst.frc.team5332.robot.toaster.base.ToasterSystemBase;
import org.usfirst.frc.team5332.autoselect.ToastSelector;
import org.usfirst.frc.team5332.dashboard.LabviewDashboard;
import org.usfirst.frc.team5332.robot.intake.IntakeHardware;
import org.usfirst.frc.team5332.robot.intake.IntakeSystem;
import org.usfirst.frc.team5332.robot.intake.base.IntakeCommandBase;
import org.usfirst.frc.team5332.robot.intake.base.IntakeHardwareBase;
import org.usfirst.frc.team5332.robot.intake.base.IntakeSystemBase;
import org.usfirst.frc.team5332.robot.intake.command.IntakeCommandTeleop;
import org.usfirst.frc.team5332.robot.toaster.ToasterHardware;
import org.usfirst.frc.team5332.robot.toaster.ToasterSystem;
import org.usfirst.frc.team5332.robot.toaster.command.ToasterCommandTeleop;
import org.usfirst.frc.team5332.robot.westtoastdrive.DriveHardware;
import org.usfirst.frc.team5332.robot.westtoastdrive.DriveSystem;
import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;
import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveHardwareBase;
import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveSystemBase;
import org.usfirst.frc.team5332.robot.westtoastdrive.command.DriveCommandTeleopTank;
import org.usfirst.frc.team5332.robot.westtoastdrive.command.auto.DriveCommandAutoLeft;
import org.usfirst.frc.team5332.robot.westtoastdrive.command.auto.DriveCommandAutoNothing;
import org.usfirst.frc.team5332.robot.westtoastdrive.command.auto.DriveCommandAutoRight;
import org.usfirst.frc.team5332.robot.westtoastdrive.command.auto.DriveCommandAutoStraight;
import org.usfirst.frc.team5332.subsystem.Subsystem;

import edu.wpi.first.wpilibj.IterativeRobot;

/*
 * The Linux JVM on the robot is configured to run this class upon startup. Do not refactor it. 
 */
public class Robot extends IterativeRobot{
	
	DriveCommandBase tankDrive = new DriveCommandTeleopTank();
	String autoSelected;
	// Drive Subsystem component.
	Subsystem<DriveHardwareBase,DriveSystemBase,DriveCommandBase> drive = new Subsystem
			<DriveHardwareBase,DriveSystemBase,DriveCommandBase>(new DriveHardware(), new DriveSystem(), tankDrive);
	Subsystem<ToasterHardwareBase, ToasterSystemBase, ToasterCommandBase> toaster = new Subsystem
			<ToasterHardwareBase, ToasterSystemBase, ToasterCommandBase>(new ToasterHardware(), new ToasterSystem(), new ToasterCommandTeleop());
	
	Subsystem<IntakeHardwareBase,IntakeSystemBase,IntakeCommandBase> intake = new Subsystem
			<IntakeHardwareBase,IntakeSystemBase,IntakeCommandBase>(new IntakeHardware(), new IntakeSystem(), new IntakeCommandTeleop());
	/*
	 * Robot-wide initialization code goes here.
	 * NOTE: This is called when the robot first boots up and ONLY when the robot first boots up.
	 * If the DS is not attached but DS communication tasks are performed you will break your code.
	 * 
	 * NOTE: DO NOT PUT LOOPS IN HERE.
	 */
	@Override
	public void robotInit(){
		// Initialize the drive subsystem.
    	LabviewDashboard.getDashboard().init();
    	LabviewDashboard.getDashboard().addData("Status",1000);
    	LabviewDashboard.getDashboard().run();
		
		drive.init();
		toaster.init();
		intake.init();
	}
	
	/*
	 * Called at the beginning of the autonomous period. Autonomous initialization code should be run here.
	 */
	@Override
	public void autonomousInit(){
		System.out.println("Running Autonomous");
    	LabviewDashboard.getDashboard().run();
    	
    	boolean runAuto = LabviewDashboard.getDashboard().getBoolean("RunAuto");
    	String autoMode= LabviewDashboard.getDashboard().getString("AutoMode");
    	ToastSelector selector=new ToastSelector();
    	System.out.println("runAuto: " + runAuto);
    	System.out.println("autoMode: " + autoMode);

    	LabviewDashboard.getDashboard().addData("Status",4000);
    	if(runAuto){
	        drive.setCommandLayer(selector.getDriveAuto(autoMode));
	       	System.out.println(selector.getDriveAuto(autoMode));
	    	LabviewDashboard.getDashboard().run();

    	}else{
	        drive.setCommandLayer(new DriveCommandAutoNothing());
    	}
		
		drive.setCommandLayer(new DriveCommandAutoStraight(0.4,3));
		drive.setCommandLayer(new DriveCommandAutoLeft(0.4 , 0.4 , 0.4 , 3 , 3.3 , 5.5));
		drive.setCommandLayer(new DriveCommandAutoRight(0.4 , 0.4 , 0.4 , 4.247 , 3.3 , 3.900)); //experimental, to be tested at JRD; 3.3 is arbitrary
		drive.init();
	}
	
	/*
	 * Called during the autonomous period.
	 */
	@Override
	public void autonomousPeriodic(){
		drive.periodicUpdate();
	}
	
	/*
	 * Called at the beginning of the teleop period. teleop initialization code should be run here.
	 */
	@Override
	public void teleopInit(){
		drive.setCommandLayer(tankDrive);
	}
	
	/*
	 * Called during the teleop period.
	 */
	@Override
	public void teleopPeriodic(){
		// Call the periodicUpdate method for the drive Subsystem.
		drive.periodicUpdate();
		toaster.periodicUpdate();
		intake.periodicUpdate();
	}
	
	/*
	 * Called when the robot is first disabled.
	 */
	@Override
	public void disabledInit(){
		
	}
	
	/*
	 * Called periodically when the robot is disabled.
	 */
	@Override
	public void disabledPeriodic(){
		
	}
	
	/*
	 * Don't worry about this.
	 */
	@Override
	public void testPeriodic(){
		
	}
}

