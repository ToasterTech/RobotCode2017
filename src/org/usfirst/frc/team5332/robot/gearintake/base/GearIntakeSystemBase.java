package org.usfirst.frc.team5332.robot.gearintake.base;

import org.usfirst.frc.team5332.subsystem.BaseLayer;
import org.usfirst.frc.team5332.subsystem.Layer;

public abstract class GearIntakeSystemBase implements Layer<GearIntakeHardwareBase>  {
	
	//Gear intake "arm"
	public abstract void moveIntakeDown();
	public abstract void moveIntakeUp();
	
	//Gear intake wheels
	public abstract void runForwards();
	public abstract void runReverse();
	public abstract void runStop();
}
