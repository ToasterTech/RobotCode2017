package org.usfirst.frc.team5332.robot.westtoastdrive.base;

import org.usfirst.frc.team5332.subsystem.Layer;

/**
 * Base class for all drive subsystem system layers.
 * 
 * @author R-Wood
 */
public abstract class DriveSystemBase implements Layer<DriveHardwareBase>{
	
	@Override
	public abstract void periodicUpdate();

	@Override
	public abstract void setChild(DriveHardwareBase c);
	
	/**
	 * Set the speed of the right side of the drive train, on a scale of 0.0-1.0.
	 * 
	 * @param speed The speed to set.
	 */
	public abstract void setDriveRight(double speed);
	
	/**
	 * Set the speed of the left side of the drive train, on a scale of 0.0-1.0.
	 * 
	 * @param speed The speed to set.
	 */
	public abstract void setDriveLeft(double speed);
	
	
	public abstract int getLeftEncoderCounts();
	
	public abstract int getRightEncoderCounts();
	
	public abstract double[] getCurrentGlobalCoords();
	
	public abstract double[] getCurrentLocalCoords();
	
	public abstract void resetLocalCoords();
	
	public abstract double getOrientation();
	
	public abstract void resetOrientation();
	
	public abstract double getLeftMotorSpeed();
	
	public abstract double getRightMotorSpeed();
	
	public abstract double getRelativeX(double x);
	public abstract double getRelativeY(double y);
	public abstract double getRelativeTheta(double t);
}
