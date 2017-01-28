package org.usfirst.frc.team5332.robot.westtoastdrive;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveHardwareBase;
import org.usfirst.frc.team5332.util.Constants;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TalonSRX;

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
	
	//Create double variables.
	private double right;
	private double left;
	
	/** 
	 * Constructor. Creates Constructor for TalonSRX.
	 */
	public DriveHardware(){
		driveRight1 = new TalonSRX(1);
		driveRight2 = new TalonSRX(2);
		driveLeft1 = new TalonSRX(3);
		driveLeft2 = new TalonSRX(4);
		leftEncoder = new Encoder(Constants.driveEncoderLeftPort1, Constants.driveEncoderLeftPort2);
		rightEncoder = new Encoder(Constants.driveEncoderRightPort1, Constants.driveEncoderRightPort2);
		gyro = new ADXRS450_Gyro();
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void periodicUpdate() {
		driveRight1.set(right);
		driveRight2.set(right);
		driveLeft1.set(left);
		driveLeft2.set(left);
	}

	@Override
	public void setDriveRight(double speed) {
		right = speed;
	}

	@Override
	public void setDriveLeft(double speed) {
		left = speed;
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
	public String getName() {
		return "FIXED_LAYER_DNU";
	}

}
