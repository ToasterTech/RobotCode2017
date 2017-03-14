package org.usfirst.frc.team5332.robot.westtoastdrive;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveHardwareBase;
import org.usfirst.frc.team5332.util.constants.HardwareConstants;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Ultrasonic;

public class DriveHardware extends DriveHardwareBase {

	// Create TalonSRX Variables.
	private TalonSRX driveRight1;
	private TalonSRX driveRight2;
	private TalonSRX driveLeft1;
	private TalonSRX driveLeft2;
	
	// Create encoder variables.
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	
	// Create gyro variable.
	private ADXRS450_Gyro gyro;
	
	// Ultrasonic sensor.
	private Ultrasonic ultrasonicSensor;
	
	//Create double variables.
	private double right;
	private double left;
	public static boolean reverseDrive = false;
	
	/** 
	 * Constructor. Creates Constructor for TalonSRX.
	 */
	public DriveHardware(){
		driveRight1 = new TalonSRX(HardwareConstants.driveRightPort1);
		driveRight2 = new TalonSRX(HardwareConstants.driveRightPort2);
		driveLeft1 = new TalonSRX(HardwareConstants.driveLeftPort1);
		driveLeft2 = new TalonSRX(HardwareConstants.driveLeftPort2);
		leftEncoder = new Encoder(HardwareConstants.driveEncoderLeftPort1, HardwareConstants.driveEncoderLeftPort2);
		rightEncoder = new Encoder(HardwareConstants.driveEncoderRightPort1, HardwareConstants.driveEncoderRightPort2);
		gyro = new ADXRS450_Gyro();
		ultrasonicSensor = new Ultrasonic(HardwareConstants.ultrasonicSensorPortO, HardwareConstants.ultrasonicSensorPortI);
		ultrasonicSensor.setDistanceUnits(Ultrasonic.Unit.kMillimeters);
		ultrasonicSensor.setEnabled(true);
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void periodicUpdate() {
		//DriveHardware.reverseDrive = true;
		if(reverseDrive) {
			driveRight1.set(left);
			driveRight2.set(left);
			driveLeft1.set(right);
			driveLeft2.set(right);
		}
		else {
			driveRight1.set(right);
			driveRight2.set(right);
			driveLeft1.set(left);
			driveLeft2.set(left);
			}
		
		
	}

	@Override
	public void setDriveRight(double speed) {
	
		right = speed;
	}

	@Override
	public void setDriveLeft(double speed) {
		
			left = -speed;
		
	}

	@Override
	public int rightDriveCount() {
		return rightEncoder.get();
	}

	@Override
	public int leftDriveCount() {
		return leftEncoder.get();
	}

	@Override
	public void encoderReset() {
		rightEncoder.reset();
		leftEncoder.reset();
	}
	
	@Override
	public double getGyroAngle(){
		return gyro.getAngle();
	}
	
	@Override
	public double getGyroRotationRate(){
		return gyro.getRate();
	}
	
	@Override
	public void resetGyro() {
		gyro.reset();
	}
	
	
	@Override
	public String getName() {
		return "FIXED_LAYER_DNU";
	}

}
