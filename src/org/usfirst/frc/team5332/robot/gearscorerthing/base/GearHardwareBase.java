package org.usfirst.frc.team5332.robot.gearscorerthing.base;

import org.usfirst.frc.team5332.subsystem.BaseLayer;

public abstract class GearHardwareBase implements BaseLayer{
	
	public abstract void driveGearManiupulator(double speed);
	
	public abstract void zeroGearManipulator();
	
	public abstract void togglePiston(int index);
	
	public abstract int getEncoderCounts();
	
	public abstract void resetEncoderCounts();
	
}