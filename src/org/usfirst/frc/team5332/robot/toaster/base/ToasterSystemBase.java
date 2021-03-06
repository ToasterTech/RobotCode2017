package org.usfirst.frc.team5332.robot.toaster.base;

import org.usfirst.frc.team5332.subsystem.Layer;

public abstract class ToasterSystemBase implements Layer<ToasterHardwareBase>{

	@Override
	public abstract void periodicUpdate();

	@Override
	public abstract void setChild(ToasterHardwareBase c);

	public abstract void shoot();

	public abstract void feed();
	public abstract void unfeed();

	public abstract void shootStop();

	public abstract void feedStop();

}