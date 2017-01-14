package org.usfirst.frc.team5332.robot.drive.base;

import org.usfirst.frc.team5332.subsystem.Layer;

public abstract class DriveCommandBase implements Layer<DriveSystemBase>{
	
	protected DriveSystemBase systemLayer;
	
	@Override
	public void setChild(DriveSystemBase c){
		systemLayer = c;
	}
	
}