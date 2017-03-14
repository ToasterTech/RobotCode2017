package org.usfirst.frc.team5332.robot;

import org.usfirst.frc.team5332.robot.toaster.base.ToasterCommandBase;
import org.usfirst.frc.team5332.robot.toaster.base.ToasterHardwareBase;
import org.usfirst.frc.team5332.robot.toaster.base.ToasterSystemBase;

import java.util.Arrays;

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

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	String chosenAuto;
	
	/*
	 * Robot-wide initialization code goes here.
	 * NOTE: This is called when the robot first boots up and ONLY when the robot first boots up.
	 * If the DS is not attached but DS communication tasks are performed you will break your code.
	 * 
	 * NOTE: DO NOT PUT LOOPS IN HERE.
	 */
	@Override
	public void robotInit(){
		drive.init();
		toaster.init();
		intake.init();
		chosenAuto = "middle";
		try {
			CameraServer.getInstance().startAutomaticCapture();
			} catch (Exception yolo420) {
			yolo420.printStackTrace();
			}
	}
	
	@Override
	public void robotPeriodic(){
		if(SmartDashboard.getBoolean("DB/Button 1", true)){
			chosenAuto = "left";
		}else if(SmartDashboard.getBoolean("DB/Button 2", true)){
			chosenAuto = "middle";
		}else if(SmartDashboard.getBoolean("DB/Button 3", true)){
			chosenAuto = "right";
		}
		if(SmartDashboard.getBoolean("DB/Button 3", false)){
			
		}
	}

	/*
	 * Called at the beginning of the autonomous period. Autonomous initialization code should be run here.
	 */
	@Override
	public void autonomousInit(){
		String[] autoDatas = SmartDashboard.getString("DB/String 0", "0,0,0,0,0,0").split(",");
		
		// structured
		// sketchy
		switch(chosenAuto){
			case "left":
				drive.setCommandLayer(new DriveCommandAutoRight(Double.parseDouble(autoDatas[0].trim()),Double.parseDouble(autoDatas[1].trim()),Double.parseDouble(autoDatas[2].trim()),Double.parseDouble(autoDatas[3].trim()),Double.parseDouble(autoDatas[4].trim()),Double.parseDouble(autoDatas[5].trim())));
				break;
			case "right":
				drive.setCommandLayer(new DriveCommandAutoRight(Double.parseDouble(autoDatas[0].trim()),Double.parseDouble(autoDatas[1].trim()),Double.parseDouble(autoDatas[2].trim()),Double.parseDouble(autoDatas[3].trim()),Double.parseDouble(autoDatas[4].trim()),Double.parseDouble(autoDatas[5].trim())));
				break;
			case "middle":
				drive.setCommandLayer(new DriveCommandAutoStraight(Double.parseDouble(autoDatas[0].trim()),Double.parseDouble(autoDatas[3].trim())));
				break;
			case "nothing":
				drive.setCommandLayer(new DriveCommandAutoNothing());
				break;
			default:
				drive.setCommandLayer(new DriveCommandAutoNothing());
				break;
		}
		
		//drive.setCommandLayer(new DriveCommandAutoStraight(0.4,3));
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

