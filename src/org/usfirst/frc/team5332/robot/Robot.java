package org.usfirst.frc.team5332.robot;

import org.usfirst.frc.team5332.robot.gearmanipulator.GearManipulatorHardware;
import org.usfirst.frc.team5332.robot.gearmanipulator.GearManipulatorSystem;
import org.usfirst.frc.team5332.robot.gearmanipulator.base.GearManipulatorCommandBase;
import org.usfirst.frc.team5332.robot.gearmanipulator.base.GearManipulatorHardwareBase;
import org.usfirst.frc.team5332.robot.gearmanipulator.base.GearManipulatorSystemBase;
import org.usfirst.frc.team5332.robot.gearmanipulator.command.GearManipulatorCommandTeleop;
import org.usfirst.frc.team5332.robot.westtoastdrive.DriveHardware;
import org.usfirst.frc.team5332.robot.westtoastdrive.DriveSystem;
import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;
import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveHardwareBase;
import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveSystemBase;
import org.usfirst.frc.team5332.robot.westtoastdrive.command.DriveCommandTeleop;
import org.usfirst.frc.team5332.robot.westtoastdrive.command.DriveCommandTeleopDiffDrive;
import org.usfirst.frc.team5332.robot.westtoastdrive.command.DriveCommandTeleopPathFollow;
import org.usfirst.frc.team5332.robot.westtoastdrive.path.Path;
import org.usfirst.frc.team5332.subsystem.Subsystem;

import edu.wpi.first.wpilibj.IterativeRobot;

/*
 * The Linux JVM on the robot is configured to run this class upon startup. Do not refactor it. 
 */
public class Robot extends IterativeRobot{
	
	// Drive Subsystem component.
	Subsystem<DriveHardwareBase,DriveSystemBase,DriveCommandBase> drive = new Subsystem
			<DriveHardwareBase,DriveSystemBase,DriveCommandBase>(new DriveHardware(), new DriveSystem(), new DriveCommandTeleop());
	Subsystem<GearManipulatorHardwareBase,GearManipulatorSystemBase,GearManipulatorCommandBase> gear = new Subsystem
			<GearManipulatorHardwareBase,GearManipulatorSystemBase,GearManipulatorCommandBase>(new GearManipulatorHardware(), new GearManipulatorSystem(), new GearManipulatorCommandTeleop());
	
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
		drive.init();
	}
	
	/*
	 * This method is called once the driver station is attached.
	 */
	public void dsInitTasks(){
		
	}
	
	// Runs the dsInitTasks once the DS is attached. No other usage for this method.
	@Override
	public void robotPeriodic(){
		if(m_ds.isDSAttached()){
			dsInitTasks();
		}
	}
	
	/*
	 * Called at the beginning of the autonomous period. Autonomous initialization code should be run here.
	 */
	@Override
	public void autonomousInit(){
		drive.setCommandLayer(new DriveCommandTeleopDiffDrive(new double[]{10,0}));
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
		
	}
	
	/*
	 * Called during the teleop period.
	 */
	@Override
	public void teleopPeriodic(){
		// Call the periodicUpdate method for the drive Subsystem.
		drive.periodicUpdate();
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

