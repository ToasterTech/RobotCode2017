package org.usfirst.frc.team5332.robot.intake.base;

import org.usfirst.frc.team5332.subsystem.Layer;

public abstract class IntakeCommandBase implements Layer<IntakeSystemBase>{

	/**
	 * System layer object of the command group.
	 */
	protected IntakeSystemBase systemLayer;
	
	@Override
	public void setChild(IntakeSystemBase c){
		systemLayer = c;
	}

}