package org.usfirst.frc.team5332.robot.drive.base;

import org.usfirst.frc.team5332.subsystem.BaseLayer;

/**
 * Base class for the drive subsystem's hardware classes.  
 * 
 * @author E-Dunbar
 */
public abstract class DriveHardwareBase implements BaseLayer{
	
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
	
	/**
	 * Get the encoder counts of the right drive train.
	 * 
	 * @return The encoder counts on the right drive train encoder.
	 */
	public abstract int rightDriveCount();
	
	/**
	 * Get the encoder counts of the left drive train.
	 * 
	 * @return The encoder counts on the left drive train encoder.
	 */
	public abstract int leftDriveCount();
	
	/**
	 * Reset both encoders on the robot.
	 */
	public abstract void encoderReset();
}
