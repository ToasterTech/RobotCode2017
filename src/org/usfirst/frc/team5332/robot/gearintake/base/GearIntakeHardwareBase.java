package org.usfirst.frc.team5332.robot.gearintake.base;

import org.usfirst.frc.team5332.subsystem.BaseLayer;;

public abstract class GearIntakeHardwareBase {
	
	public abstract void extendPneumatics();
	
	public abstract void retractPneumatics();
	
	public abstract void setIntakeSpeed(double speed);
}
