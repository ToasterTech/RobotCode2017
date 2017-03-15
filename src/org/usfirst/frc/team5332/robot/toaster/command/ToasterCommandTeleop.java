package org.usfirst.frc.team5332.robot.toaster.command;

import org.usfirst.frc.team5332.robot.control.GamePad;
import org.usfirst.frc.team5332.robot.toaster.base.ToasterCommandBase;
import org.usfirst.frc.team5332.util.constants.JoystickConstants;

public class ToasterCommandTeleop extends ToasterCommandBase{

	public ToasterCommandTeleop() {
		
	}

	@Override
	public void init() {

	}

	@Override
	public void periodicUpdate() {
		if (GamePad.getDriverJoystick().getButton(JoystickConstants.feedButtonID)){
			if((System.currentTimeMillis() % 500) < 100){
				systemLayer.feed();
			} else {
				systemLayer.feedStop();
			}
		} else if (GamePad.getDriverJoystick().getButton(JoystickConstants.unfeedButtonID)){
			systemLayer.unfeed();
		} else {
			systemLayer.feedStop();
		}
		
		if (GamePad.getDriverJoystick().getButton(JoystickConstants.shootButtonID)){
			systemLayer.shoot();
		} else {
			systemLayer.shootStop();
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}