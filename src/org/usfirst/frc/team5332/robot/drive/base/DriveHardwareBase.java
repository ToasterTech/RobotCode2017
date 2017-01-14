package org.usfirst.frc.team5332.robot.drive.base;

import org.usfirst.frc.team5332.subsystem.BaseLayer;

public abstract class DriveHardwareBase implements BaseLayer{

	public abstract void setDriveRight(double speed);
	
	public abstract void setDriveLeft(double speed);
	
	public abstract int rightDriveCount();
	
	public abstract int leftDriveCount();
	
	public abstract void encoderReset();
}
