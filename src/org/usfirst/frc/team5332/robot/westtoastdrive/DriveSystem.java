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
		hardwareLayer.setDriveLeft(left);
		hardwareLayer.setDriveRight(right);
	}

	@Override
	public void setChild(DriveHardwareBase c) {
		hardwareLayer = c;
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
	public double distanceForward() {
		return 0;
	}
	
	
	
}