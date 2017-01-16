package org.usfirst.frc.team5332.robot.toaster.base;

import org.usfirst.frc.team5332.subsystem.BaseLayer;

public abstract class ToasterHardwareBase implements BaseLayer{
	
	public abstract void setShooterSpeed(double speed);
	
	public abstract void setFeedSpeed(double speed);
	
}