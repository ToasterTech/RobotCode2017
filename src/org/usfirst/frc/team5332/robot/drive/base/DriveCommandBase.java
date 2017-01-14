package org.usfirst.frc.team5332.robot.drive.base;

import org.usfirst.frc.team5332.subsystem.Layer;

/**
 * Base class for all drive subsystem command control layers.
 * 
 * @author J-Dierberger
 */
public abstract class DriveCommandBase implements Layer<DriveSystemBase>{
	
	/**
	 * System layer object of the command group.
	 */
	protected DriveSystemBase systemLayer;
	
	@Override
	public void setChild(DriveSystemBase c){
		systemLayer = c;
	}
	
}