package org.usfirst.frc.team5332.robot.westtoastdrive.command;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;
import org.usfirst.frc.team5332.util.constants.MeasuredConstants;
import org.usfirst.frc.team5332.util.constants.TuningConstants;

public class DriveCommandTeleopDiffDrive extends DriveCommandBase{
	
	// TODO Figure out if we need this or not
	@SuppressWarnings("unused")
	private long lastUpdateTime;
	
	private double x;
	private double y;
	private double theta;
	private double leftCount;
	private double rightCount;
	
	private double leftSpeed;
	private double rightSpeed;
	
	private double targetDist;
	private double targetTheta;
	
	private boolean angleFirst;
	
	public DriveCommandTeleopDiffDrive(double[] coords){
		targetDist = Math.sqrt(coords[0]*coords[0] + coords[1]*coords[1]);
		targetTheta = Math.atan(coords[1]/coords[0]);
	}
	
	@Override
	public void init() {
		lastUpdateTime = System.nanoTime();
		leftSpeed = 0;
		rightSpeed = 0;
		//GYRO CODE
		theta = 0;
		systemLayer.resetOrientation();
		//END GYRO CODE
		systemLayer.resetLocalCoords();
		double[] coords = systemLayer.getCurrentLocalCoords();
		x = coords[0];
		y = coords[1];
		leftCount = systemLayer.getLeftEncoderCounts();
		rightCount = systemLayer.getRightEncoderCounts();
		angleFirst = true;
	}

	@Override
	public void periodicUpdate() {
		
//		if(System.nanoTime()-lastUpdateTime > (1000000000 * Constants.followSecondsBetweenChecks)){
			
			double deltaLeftCount = systemLayer.getLeftEncoderCounts() - leftCount;
			double deltaRightCount = systemLayer.getRightEncoderCounts() - rightCount;
			
			double leftDist = 2*Math.PI*MeasuredConstants.radiusOfWheels * (deltaLeftCount/MeasuredConstants.ticksPerRevolution);
			double rightDist = 2*Math.PI*MeasuredConstants.radiusOfWheels * (deltaRightCount/MeasuredConstants.ticksPerRevolution);
			
			double v = MeasuredConstants.radiusOfWheels/2 * (systemLayer.getRightMotorSpeed() + systemLayer.getLeftMotorSpeed());
			double w = MeasuredConstants.radiusOfWheels/MeasuredConstants.lengthBetweenWheels * (systemLayer.getRightMotorSpeed() - systemLayer.getLeftMotorSpeed());

			x += (leftDist + rightDist)/2 * Math.cos(theta);
			y += (leftDist + rightDist)/2 * Math.sin(theta);
			double totalTheta = theta + (rightDist - leftDist)/MeasuredConstants.lengthBetweenWheels;
			theta = Math.atan(Math.tan(totalTheta));
			System.out.println("Theta: " + theta);
			mathUpdate(x, y, theta, targetDist, targetTheta, v, w);
			
			leftCount = systemLayer.getLeftEncoderCounts();
			rightCount = systemLayer.getRightEncoderCounts();
			
			lastUpdateTime = System.nanoTime();
//		}
		systemLayer.setDriveLeft(leftSpeed);
		systemLayer.setDriveRight(rightSpeed);
	}

	@Override
	public String getName() {
		return "DRIVE_TELEOP_DIFFDRIVE";
	}
	
	private void mathUpdate(double x, double y, double theta, double targetDist, double targetTheta, double v, double w){

		double currentDist = Math.sqrt((x*x)+(y*y));
		
		double errorDist = targetDist - currentDist;
		double errorTheta = targetTheta - theta;
		
		//GYRO CODE
		double gyroErrorTheta = getAngleError();
		
		if (Math.abs(errorDist) < 1 || errorDist < 0 || angleFirst) {
			v = 0;
			angleFirst = true;
		} else {
			v = -errorDist * TuningConstants.distConst;
		}
		if (Math.abs(errorTheta) < .2 || !angleFirst) {
			w = 0;
			angleFirst = false;
		} else {
			w = -errorTheta * TuningConstants.angleConst;
		}
		System.out.printf("D: %f, T: %f, Ang: %b \n", errorDist, errorTheta, angleFirst);

		leftSpeed = (2*v+w*MeasuredConstants.lengthBetweenWheels)/(2*MeasuredConstants.radiusOfWheels);
		rightSpeed = (2*v-w*MeasuredConstants.lengthBetweenWheels)/(2*MeasuredConstants.radiusOfWheels);
	}
	
	//GYRO CODE
	private double getAngleError() {
		return Math.atan(Math.tan(targetTheta - systemLayer.getOrientation()));
	}
	
}