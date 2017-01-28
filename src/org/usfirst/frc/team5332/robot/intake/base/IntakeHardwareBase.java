package org.usfirst.frc.team5332.robot.intake.base;

import org.usfirst.frc.team5332.subsystem.BaseLayer;
/**
 * This is the abstract class that gives the system layer the commands for driving.
 * @author E-Dunbar
 *
 */

public abstract class IntakeHardwareBase implements BaseLayer{
	/**
	 * Sets the speed of the intake.
	 * @param speed The speed to set the motor
	 */
	public abstract void setIntakeSpeed(double speed);
	
}