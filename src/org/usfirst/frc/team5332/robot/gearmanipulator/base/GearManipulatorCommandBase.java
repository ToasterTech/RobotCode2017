package org.usfirst.frc.team5332.robot.gearmanipulator.base;

import org.usfirst.frc.team5332.subsystem.Layer;

public abstract class GearManipulatorCommandBase implements Layer<GearManipulatorSystemBase>{
	
	/**
	 * System layer object of the command group.
	 */
	protected GearManipulatorSystemBase systemLayer;
	
	@Override
	public void setChild(GearManipulatorSystemBase c){
		systemLayer = c;
	}
	
}