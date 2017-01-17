package org.usfirst.frc.team5332.robot.toaster.command;

import org.usfirst.frc.team5332.robot.control.OperatorJoystick;
import org.usfirst.frc.team5332.robot.toaster.base.ToasterCommandBase;
import org.usfirst.frc.team5332.util.Constants;

public class ToasterCommandTeleop extends ToasterCommandBase{

	private OperatorJoystick js;
	
	public ToasterCommandTeleop() {
		js = new OperatorJoystick();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
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