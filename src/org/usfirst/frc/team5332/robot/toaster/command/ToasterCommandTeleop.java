package org.usfirst.frc.team5332.robot.toaster.command;

import org.usfirst.frc.team5332.robot.control.GamePad;
import org.usfirst.frc.team5332.robot.toaster.base.ToasterCommandBase;
import org.usfirst.frc.team5332.util.Constants;

public class ToasterCommandTeleop extends ToasterCommandBase{

	private GamePad js;
	
	public ToasterCommandTeleop() {
		js = new GamePad();
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void periodicUpdate() {
		if (js.getButton(Constants.feedButtonID)){
			systemLayer.feed();
		}
		else
			systemLayer.feedStop();
		
		if (js.getButton(Constants.shootButtonID)){
			systemLayer.shoot();
		}
		else
			systemLayer.shootStop();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}