package org.usfirst.frc.team5332.robot.gearscorerthing.base;

import org.usfirst.frc.team5332.robot.gearscorerthing.base.GearHardwareBase;
import org.usfirst.frc.team5332.subsystem.Layer;

public abstract class GearSystemBase implements Layer<GearHardwareBase>{

	@Override
	public abstract void periodicUpdate();	
	
	@Override
	public abstract void setChild(GearHardwareBase c);
	
}