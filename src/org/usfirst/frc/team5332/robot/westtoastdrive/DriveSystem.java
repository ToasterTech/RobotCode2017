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
	public int getLeftEncoderCounts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRightEncoderCounts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getCurrentGlobalCoords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getCurrentLocalCoords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getOrientation() {
		// TODO Auto-generated method stub
		return 0;
	} 
	
	@Override
	public String getName() {
		return "FIXED_LAYER_DNU";
	}
	
}