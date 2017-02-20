package org.usfirst.frc.team5332.robot.westtoastdrive;

import java.net.SocketException;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveHardwareBase;
import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveSystemBase;
import org.usfirst.frc.team5332.vision.ArmSink;

public class DriveSystem extends DriveSystemBase{
	private double relativeX;
	private double relativeY;
	@SuppressWarnings("unused")
	private double relativeTheta;
	private DriveHardwareBase hardwareLayer;

	
	private double getX;
	private double getY;
	
	private double right;
	private double left;

	private double[] localCoords;
	private double[] globalCoords;
	
	private ArmSink input;

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
		try {
			input = new ArmSink();
		} catch (SocketException e) {
			System.out.println("Couldn't communicate with Jetson");
		}
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
		@SuppressWarnings("unused")
		double distTraveled = Math.signum(encoderCountChangeRight) * Math.sqrt(2* Math.pow(radius, 2) - (2* Math.pow(radius, 2)* Math.cos((alpha*Math.PI)/180)));


		/*
		y prime = (xtan0 + y)cos0
		x prime = sqrt(x2 + y2 - y'2)
		 */

		relativeX = (getX * Math.tan(input.getAngle()) + getY * Math.cos(input.getAngle()));

		relativeY = Math.sqrt(Math.pow(2 , getX) + Math.pow(2, getY));
		
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
	public double getRelativeX(double x) {
		getX = x;
		return relativeX;
	}

	@Override
	public double getRelativeY(double y) {
		getY = y;
		return relativeY;
	}

	@Override
	public double getRelativeTheta(double t) {
		
		return 0;
	}
	
}