package org.usfirst.frc.team5332.robot.westtoastdrive;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveHardwareBase;
import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveSystemBase;

public class DriveSystem extends DriveSystemBase{
	private DriveHardwareBase hardwareLayer;
	
	private double right;
	private double left;

	public DriveSystem(){

	}

	@Override
	public void init(){
		
	}

	@Override
	public void periodicUpdate() {
		// Set the motors to what our current motor speed variable is.
		hardwareLayer.setDriveLeft(left);
		hardwareLayer.setDriveRight(right);
	}

	@Override
	public void setChild(DriveHardwareBase c) {
		hardwareLayer = c;
	}

	@Override
	public void setDriveRight(double speed) {
		// Sets the right drive-motor speed.
		right = speed;
	}

	@Override
	public void setDriveLeft(double speed) {
		// Sets the left drive-motor speed.
		left = speed;
	}

	@Override
	public int getLeftEncoderCounts(){
		return hardwareLayer.leftDriveCount();
	}

	@Override
	public int getRightEncoderCounts() {
		return hardwareLayer.rightDriveCount();
	}

	@Override
	public double getOrientation() {
		return hardwareLayer.getGyroAngle();
	} 
	
	@Override
	public void resetOrientation() {
		hardwareLayer.resetGyro();
	}

	@Override
	public double getLeftMotorSpeed(){
		return left;
	}

	@Override
	public double getRightMotorSpeed(){
		return right;
	}

	@Override
	public String getName() {
		return "FIXED_LAYER_DNU";
	}
	
}