package org.usfirst.frc.team5332.robot.gearmanipulator.base;

import org.usfirst.frc.team5332.subsystem.BaseLayer;

public abstract class GearManipulatorHardwareBase implements BaseLayer{
	
	public abstract void driveGearManiupulator(double speed);
	
	public abstract void toggleUpperPistons();
	
	public abstract void toggleLowerPistons();
	
	public abstract int getEncoderCounts();
	
	public abstract void resetEncoderCounts();
	
}