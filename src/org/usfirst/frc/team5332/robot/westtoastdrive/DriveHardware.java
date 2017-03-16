package org.usfirst.frc.team5332.robot.westtoastdrive;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveHardwareBase;
import org.usfirst.frc.team5332.util.constants.HardwareConstants;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

	// Ultrasonic sensors.
	private Ultrasonic ultrasonicSensorBack;
	private Ultrasonic ultrasonicSensorFront;

	//Create double variables.
	private double right;
	private double left;
	public static boolean reverseDrive = false;
	private double distanceFront;
	private double distanceBack;

	/** 
	 * Constructor. Creates Constructor for TalonSRX.
	 */
	public DriveHardware(){
		driveRight1 = new TalonSRX(HardwareConstants.driveRightPort1);
		driveRight2 = new TalonSRX(HardwareConstants.driveRightPort2);
		driveLeft1 = new TalonSRX(HardwareConstants.driveLeftPort1);
		driveLeft2 = new TalonSRX(HardwareConstants.driveLeftPort2);
//		leftEncoder = new Encoder(HardwareConstants.driveEncoderLeftPort1, HardwareConstants.driveEncoderLeftPort2);
//		rightEncoder = new Encoder(HardwareConstants.driveEncoderRightPort1, HardwareConstants.driveEncoderRightPort2);
		gyro = new ADXRS450_Gyro();
		ultrasonicSensorBack = new Ultrasonic(HardwareConstants.ultrasonicSensorLeftPortO, HardwareConstants.ultrasonicSensorLeftPortI);
		ultrasonicSensorBack.setDistanceUnits(Ultrasonic.Unit.kMillimeters);
		ultrasonicSensorBack.setEnabled(true);
		ultrasonicSensorFront = new Ultrasonic(HardwareConstants.ultrasonicSensorRightPortO, HardwareConstants.ultrasonicSensorRightPortI);
		ultrasonicSensorFront.setDistanceUnits(Ultrasonic.Unit.kMillimeters);
		ultrasonicSensorFront.setEnabled(true);
	}

	@Override
	public void init() {
		reverseDrive = false;
	}

	@Override
	public void periodicUpdate() {
		//DriveHardware.reverseDrive = true;
		if(reverseDrive) {
			// Rotates the robot 180 degrees
			driveRight1.set(left);
			driveRight2.set(left);
			driveLeft1.set(right);
			driveLeft2.set(right);
		}else{
			driveRight1.set(right);
			driveRight2.set(right);
			driveLeft1.set(left);
			driveLeft2.set(left);
		}
		distanceFront = ultrasonicSensorFront.getRangeMM();
		distanceBack = ultrasonicSensorBack.getRangeMM();
		SmartDashboard.putString("DB/String 1", "FRONT U/S:"+distanceFront);
		SmartDashboard.putString("DB/String 2", "BACK U/S:"+distanceBack);
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
	public double getDistanceBack(){
		return distanceBack;
	}
	
	@Override
	public double getDistanceFront(){
		return distanceFront;
	}

	@Override
	public String getName() {
		return "FIXED_LAYER_DNU";
	}

}
