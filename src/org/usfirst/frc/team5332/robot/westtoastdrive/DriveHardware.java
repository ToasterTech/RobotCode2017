package org.usfirst.frc.team5332.robot.westtoastdrive;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveHardwareBase;

import edu.wpi.first.wpilibj.TalonSRX;

public class DriveHardware extends DriveHardwareBase {
	
	private TalonSRX driveRight1;
	private TalonSRX driveRight2;
	private TalonSRX driveLeft1;
	private TalonSRX driveLeft2;
	
	private double right;
	private double left;
	
	public DriveHardware(){
		driveRight1 = new TalonSRX(1);
		driveRight2 = new TalonSRX(2);
		driveLeft1 = new TalonSRX(3);
		driveLeft2 = new TalonSRX(4);
		
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
		return 0;
	}

	@Override
	public int leftDriveCount() {
		
		return 0;
	}

	@Override
	public void encoderReset() {
		
	}

}
