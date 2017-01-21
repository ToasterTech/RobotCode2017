package org.usfirst.frc.team5332.robot.intake.base;

import org.usfirst.frc.team5332.subsystem.Layer;

/**
 * 
 * This class provides abstract methods for the abstract hardware controls. Abstractly.
 * 
 * @author Robert Wood IV
 *
 */

public abstract class IntakeSystemBase implements Layer<IntakeHardwareBase>{

	@Override
	public abstract void periodicUpdate();	
	
	@Override
	public abstract void setChild(IntakeHardwareBase c);
	
	/**
	 * Drive the intake.
	 */
	public abstract void intake();
	
	/**
	 * Drive the intake backward.
	 */
	public abstract void reverse();
	
	/**
	 * Stop the intake.
	 */
	public abstract void stopIntake();

}