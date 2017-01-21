package org.usfirst.frc.team5332.robot.gearmanipulator.base;

import org.usfirst.frc.team5332.robot.gearmanipulator.base.GearManipulatorHardwareBase;
import org.usfirst.frc.team5332.subsystem.Layer;

public abstract class GearManipulatorSystemBase implements Layer<GearManipulatorHardwareBase>{

	@Override
	public abstract void periodicUpdate();	
	
	@Override
	public abstract void setChild(GearManipulatorHardwareBase c);
	
	public abstract void toggleGates();
	public abstract void goLeft();
	public abstract void goRight();
	public abstract void goLeft(double speed);
	public abstract void goRight(double speed);
	public abstract void gotoCounts(int counts);
	public abstract void gotoCounts(double speed, int counts);
	public abstract void centerManipulator();
	public abstract int getCounts();
	public abstract void userControlOverride();
	public abstract void stop();
	
}