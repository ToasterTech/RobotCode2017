package org.usfirst.frc.team5332.robot.intake.base;

import org.usfirst.frc.team5332.subsystem.Layer;

public abstract class IntakeSystemBase implements Layer<IntakeHardwareBase>{

	@Override
	public abstract void periodicUpdate();	
	
	@Override
	public abstract void setChild(IntakeHardwareBase c);
	
	public abstract void forwardIntake();
	
	public abstract void reverseIntake();
	
	public abstract void stopIntake();

}