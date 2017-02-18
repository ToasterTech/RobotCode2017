package org.usfirst.frc.team5332.robot.westtoastdrive;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveHardwareBase;
import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveSystemBase;
import org.usfirst.frc.team5332.vision.ArmSink;

public class DriveSystem extends DriveSystemBase{
	private double relativeX;
	private double relativeY;
	private double relativeTheta;
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

		System.out.printf("R.T: %f, L.T: %f \n", encoderCountTotalRight, encoderCountTotalLeft);

		double encoderCountChangeRight = this.getRightEncoderCounts() - encoderCountTotalRight;
		double encoderCountChangeLeft = this.getLeftEncoderCounts() - encoderCountTotalLeft;
		double angleMeasure;
		double curveMeasure;
		// All cases for turning.
		if(encoderCountChangeRight < encoderCountChangeLeft) {
			angleMeasure = (encoderCountChangeRight / encoderCountChangeLeft) * 90;
			curveMeasure = 90 - angleMeasure;
		}else if(encoderCountChangeRight > encoderCountChangeLeft) {
			angleMeasure = 180 - ((encoderCountChangeLeft / encoderCountChangeRight) * 90);
			curveMeasure = angleMeasure - 90;
		}else{
			angleMeasure = (encoderCountChangeRight / encoderCountChangeLeft) * 90;
			curveMeasure = 90;
		}
		
		double beta = 90-angleMeasure;
		double alpha = 180 - 2*beta;
		double radius = curveMeasure/alpha;  //signum is not complete...?
		double distTraveled = Math.signum(encoderCountChangeRight) * Math.sqrt(2* Math.pow(radius, 2) - (2* Math.pow(radius, 2)* Math.cos((alpha*Math.PI)/180)));


		/*
		y prime = (xtan0 + y)cos0
		x prime = sqrt(x2 + y2 - y'2)
		 */

		relativeX = (ArmSink.getX() * Math.tan(ArmSink.getTheta()) + ArmSink.getY() * Math.cos(ArmSink.getTheta()));
		relativeY = Math.sqrt(Math.pow(2 , ArmSink.getX()) + Math.pow(2, ArmSink.getY()));
		
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

	@Override
	public double getRelativeX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRelativeY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRelativeTheta() {
		// TODO Auto-generated method stub
		return 0;
	}

}