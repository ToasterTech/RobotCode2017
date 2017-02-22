package org.usfirst.frc.team5332.robot.ToastYaw.base;

import org.usfirst.frc.team5332.subsystem.Layer;

public abstract class ToastYawCommandBase implements Layer<ToastYawSystemBase> {
	
	protected ToastYawSystemBase systemlayer;
	
	@Override
	public abstract void setChild(ToastYawSystemBase c);
	
	public abstract boolean buttonPressed();
	
	@Override
	public abstract void periodicUpdate();
}
