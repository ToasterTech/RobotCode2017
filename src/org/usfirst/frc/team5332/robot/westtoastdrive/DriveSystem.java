package org.usfirst.frc.team5332.robot.westtoastdrive;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveHardwareBase;
import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveSystemBase;

public class DriveSystem extends DriveSystemBase{

	private DriveHardwareBase hardwareLayer;
	
	private double right;
	private double left;
	
	private double[] localCoords;
	private double[] globalCoords;
	
	public DriveSystem(){
		localCoords = new double[2];
		globalCoords = new double[2];
	}
	
	@Override
	public void init(){
		localCoords[0] = 0;
		localCoords[1] = 0;
		globalCoords[0] = 0;
		globalCoords[1] = 0;
	}

	@Override
	public void periodicUpdate() {
		// Set the motors to what our current motor speed variable is.
		hardwareLayer.setDriveLeft(left);
		hardwareLayer.setDriveRight(right);
		// Getting encoder change over time period.
		double  encoderCountTotalRight = this.getRightEncoderCounts();
		double  encoderCountTotalLeft = this.getLeftEncoderCounts();
		
		double encoderCountChangeRight = this.getRightEncoderCounts() - encoderCountTotalRight;
		double encoderCountChangeLeft = this.getLeftEncoderCounts() - encoderCountTotalLeft;
		// All cases for turning.
		if(encoderCountChangeRight < encoderCountChangeLeft) {
			double angleMeasure = (encoderCountChangeRight / encoderCountChangeLeft) * 90;
			double curveMeasure = 90 - angleMeasure;
		}
		if(encoderCountChangeRight > encoderCountChangeLeft) {
			double angleMeasure = 180 - ((encoderCountChangeLeft / encoderCountChangeRight) * 90);
			double curveMeasure = angleMeasure - 90;
		}
	
		if(encoderCountChangeRight == encoderCountChangeLeft) {
			double angleMeasure = (encoderCountChangeRight / encoderCountChangeLeft) * 90;
			double curveMeasure = 90;
		}
		
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
	public double[] getCurrentGlobalCoords() {
		return globalCoords;
	}

	@Override
	public double[] getCurrentLocalCoords() {
		return localCoords;
	}

	@Override
	public double getOrientation() {
		return hardwareLayer.getGyroAngle();
	} 
	
	@Override
	public void resetLocalCoords(){
		localCoords[0] = 0;
		localCoords[1] = 0;
	}
	
	@Override
	public String getName() {
		return "FIXED_LAYER_DNU";
	}
	
}