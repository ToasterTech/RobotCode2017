package org.usfirst.frc.team5332.robot.drive.base;

import org.usfirst.frc.team5332.subsystem.Layer;

public abstract class DriveSystemBase implements Layer<DriveHardwareBase>{
	
	@Override
	public abstract void periodicUpdate();

	@Override
	public abstract void setChild(DriveHardwareBase c);
	
	public abstract void setDriveRight(double speed);
	
	public abstract void setDriveLeft(double speed);
	
	public abstract double distanceForward(double distance);
}
