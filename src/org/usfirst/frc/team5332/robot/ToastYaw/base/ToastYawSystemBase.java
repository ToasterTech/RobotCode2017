package org.usfirst.frc.team5332.robot.ToastYaw.base;

import org.usfirst.frc.team5332.subsystem.Layer;

public abstract class ToastYawSystemBase implements Layer<ToastYawHardwareBase>{

	@Override
	public abstract void setChild(ToastYawHardwareBase c);

	@Override
	public abstract void periodicUpdate();

	public abstract void climb(); 

}
