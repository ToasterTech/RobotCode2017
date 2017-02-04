package org.usfirst.frc.team5332.robot.westtoastdrive.command;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;
import org.usfirst.frc.team5332.robot.westtoastdrive.path.Path;
import org.usfirst.frc.team5332.util.Constants;

public class DriveCommandTeleopDiffDrive extends DriveCommandBase{
	
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
	
	public DriveCommandTeleopDiffDrive(double[] coords){
		targetDist = Math.sqrt(coords[0]*coords[0] + coords[1]*coords[1]);
		targetTheta = Math.atan(coords[1]/coords[0]);
	}
	
	@Override
	public void init() {
		lastUpdateTime = System.nanoTime();
		leftSpeed = 0;
		rightSpeed = 0;
		systemLayer.resetLocalCoords();
		double[] coords = systemLayer.getCurrentLocalCoords();
		x = coords[0];
		y = coords[1];
		leftCount = systemLayer.getLeftEncoderCounts();
		rightCount = systemLayer.getRightEncoderCounts();
	}

	@Override
	public void periodicUpdate() {
		
		if(System.nanoTime()-lastUpdateTime > (1000000000 * Constants.followSecondsBetweenChecks)){
			
			double deltaLeftCount = systemLayer.getLeftEncoderCounts() - leftCount;
			double deltaRightCount = systemLayer.getRightEncoderCounts() - rightCount;
			
			double leftDist = 2*Math.PI*Constants.radiusOfWheels * (deltaLeftCount/Constants.ticksPerRevolution);
			double rightDist = 2*Math.PI*Constants.radiusOfWheels * (deltaRightCount/Constants.ticksPerRevolution);
			
			x += (leftDist + rightDist)/2 * Math.cos(theta);
			y += (leftDist + rightDist)/2 * Math.sin(theta);
			theta += (rightDist - leftDist)/Constants.lengthBetweenWheels;
			
			mathUpdate(x, y, theta, targetDist, targetTheta);
			
			leftCount = systemLayer.getLeftEncoderCounts();
			rightCount = systemLayer.getRightEncoderCounts();
			
			lastUpdateTime = System.nanoTime();
		}
		systemLayer.setDriveLeft(leftSpeed);
		systemLayer.setDriveRight(rightSpeed);
	}

	@Override
	public String getName() {
		return "DRIVE_TELEOP_DIFFDRIVE";
	}
	
	private void mathUpdate(double x, double y, double theta, double targetDist, double targetTheta){
		double v = Constants.radiusOfWheels/2 * (systemLayer.getRightMotorSpeed() + systemLayer.getRightMotorSpeed());
		double w = Constants.radiusOfWheels/Constants.lengthBetweenWheels * (systemLayer.getRightMotorSpeed() - systemLayer.getRightMotorSpeed());
		
		double currentDist = Math.sqrt((x*x)+(y*y));
		
		double errorDist = targetDist - currentDist;
		double errorTheta = targetTheta - theta;
		
		double xPrime = v*Math.cos(theta)*errorDist*Constants.distConst;
		double yPrime = v*Math.sin(theta)*errorDist*Constants.distConst;
		w = w * errorTheta * Constants.angleConst;
		v = Math.sqrt((xPrime*xPrime) + (yPrime*yPrime));
		
		leftSpeed = (2*v+w*Constants.lengthBetweenWheels)/(2*Constants.radiusOfWheels);
		rightSpeed = (2*v-w*Constants.lengthBetweenWheels)/(2*Constants.radiusOfWheels);
	}
	
}