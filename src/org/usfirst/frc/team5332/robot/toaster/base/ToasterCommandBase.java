package org.usfirst.frc.team5332.robot.toaster.base;

import org.usfirst.frc.team5332.subsystem.Layer;

public abstract class ToasterCommandBase implements Layer<ToasterSystemBase>{
	
	protected ToasterSystemBase systemLayer;
	
	@Override
	public void setChild(ToasterSystemBase c){
		systemLayer = c;
	}
	
}