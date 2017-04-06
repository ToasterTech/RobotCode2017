package org.usfirst.frc.team5332.robot;

import org.usfirst.frc.team5332.robot.toaster.base.ToasterCommandBase;
import org.usfirst.frc.team5332.robot.toaster.base.ToasterHardwareBase;
import org.usfirst.frc.team5332.robot.toaster.base.ToasterSystemBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
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
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
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
	DigitalOutput led0  = new DigitalOutput(9);
	DigitalOutput led1 = new DigitalOutput(10);
	Alliance color;
	int station;
	boolean lastPressLeft;
	boolean lastPressMiddle;
	boolean lastPressRight;
	
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
		
		try{
			CameraServer.getInstance().startAutomaticCapture();
		}catch(Exception yolo420){
			yolo420.printStackTrace();
		}
		
		//set the default state of the led strip
		//led states table
		//0		1
		//false false	set color to red blue alternate
		//true false	set color to blue
		//false true	set color to red
		//true true		set color to rainbow
		//red blue alternate until ds attached
		//rainbow until enable
		//alliance color after that
		
		led0.set(false);
		led1.set(false);
		
	}
	
	@Override
	public void robotPeriodic(){
		
		if(DriverStation.getInstance().isDSAttached()) {
			station = DriverStation.getInstance().getLocation();
		}
		
		if(DriverStation.getInstance().isDSAttached() && !DriverStation.getInstance().isEnabled()) {
			led0.set(true);
			led1.set(true);
		}
		
		if(SmartDashboard.getBoolean("DB/Button 1", true) && !lastPressLeft){
			chosenAuto = "left";
			SmartDashboard.putBoolean("DB/Button 2", false);
			SmartDashboard.putBoolean("DB/Button 3", false);
			lastPressLeft = true;
			lastPressRight = false;
			lastPressMiddle = false;
		} else if(SmartDashboard.getBoolean("DB/Button 2", true) && !lastPressMiddle){
			chosenAuto = "middle";
			SmartDashboard.putBoolean("DB/Button 1", false);
			SmartDashboard.putBoolean("DB/Button 3", false);
			lastPressLeft = false;
			lastPressRight = false;
			lastPressMiddle = true;
		} else if(SmartDashboard.getBoolean("DB/Button 3", true) && !lastPressRight){
			chosenAuto = "right";
			SmartDashboard.putBoolean("DB/Button 1", false);
			SmartDashboard.putBoolean("DB/Button 2", false);
			lastPressLeft = false;
			lastPressRight = true;
			lastPressMiddle = false;
		}
		
		if(SmartDashboard.getBoolean("DB/Button 1", false)) {
			lastPressLeft=false;
		}
		if(SmartDashboard.getBoolean("DB/Button 2", false)) {
			lastPressMiddle=false;
		}
		if(SmartDashboard.getBoolean("DB/Button 3", false)) { 
			lastPressRight=false;
		}
	}

	/*
	 * Called at the beginning of the autonomous period. Autonomous initialization code should be run here.
	 */
	@Override
	public void autonomousInit(){
		
//		String[] autoDatas = SmartDashboard.getString("DB/String 0", "0,0,0,0,0,0").split(",");
		
		// structured
		// sketchy
//		switch(chosenAuto){
//			case "left":
//				drive.setCommandLayer(new DriveCommandAutoLeft(Double.parseDouble(autoDatas[0].trim()),Double.parseDouble(autoDatas[1].trim()),Double.parseDouble(autoDatas[2].trim()),Double.parseDouble(autoDatas[3].trim()),Double.parseDouble(autoDatas[4].trim()),Double.parseDouble(autoDatas[5].trim())));
//				break;
//			case "right":
//				drive.setCommandLayer(new DriveCommandAutoRight(Double.parseDouble(autoDatas[0].trim()),Double.parseDouble(autoDatas[1].trim()),Double.parseDouble(autoDatas[2].trim()),Double.parseDouble(autoDatas[3].trim()),Double.parseDouble(autoDatas[4].trim()),Double.parseDouble(autoDatas[5].trim())));
//				break;
//			case "middle":
//				drive.setCommandLayer(new DriveCommandAutoStraight(Double.parseDouble(autoDatas[0].trim()),Double.parseDouble(autoDatas[3].trim())));
//				break;
//			case "nothing":
//				drive.setCommandLayer(new DriveCommandAutoNothing());
//				break;
//			default:
//				drive.setCommandLayer(new DriveCommandAutoNothing());
//				break;
//		}
		
		
			drive.setCommandLayer(new DriveCommandAutoStraight(-0.4,3));
//			drive.setCommandLayer(new DriveCommandAutoLeft(-0.4, -0.4, 0.4, 1.85, 0.85, 2.52));
			drive.init();
		
			color = DriverStation.getInstance().getAlliance();
			if(color == DriverStation.Alliance.Blue){
				led0.set(true);
				led1.set(false);
			} else if(color == DriverStation.Alliance.Red){
				led0.set(false);
				led1.set(true);
			}

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
		drive.init();
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

