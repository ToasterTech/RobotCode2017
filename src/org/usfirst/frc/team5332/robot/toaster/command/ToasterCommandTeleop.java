package org.usfirst.frc.team5332.robot.toaster.command;

import org.usfirst.frc.team5332.robot.control.GamePad;
import org.usfirst.frc.team5332.robot.toaster.base.ToasterCommandBase;
import org.usfirst.frc.team5332.util.constants.JoystickConstants;

public class ToasterCommandTeleop extends ToasterCommandBase{
	private boolean shooterIsSped;
	private boolean spinningUp;
	private long spinStartTime;
	
	public ToasterCommandTeleop() {
		shooterIsSped = false;
		spinningUp = false;
		spinStartTime = System.currentTimeMillis();
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void periodicUpdate() {
		//Fancy partial trigger pushing shooting and feeding
		if (GamePad.getDriverJoystick().getRightZAxisValue() > 0.25) {
			System.out.println("RightJoyShooter");
			if (GamePad.getDriverJoystick().getRightZAxisValue() > 0.75) {
				if (!shooterIsSped) {
					if (!spinningUp) {
						spinStartTime = System.currentTimeMillis();
						spinningUp = true;
					} else if (System.currentTimeMillis() - spinStartTime > 1500) {
							shooterIsSped = true;
							spinningUp = false;
					}
					systemLayer.feedStop();
				} else {
					systemLayer.feed();
				}
			}
			systemLayer.shoot();
		//left trigger just if pressed shooting and feed
		} else if (GamePad.getDriverJoystick().getLeftZAxisValue() > .25) {
			if (!shooterIsSped) {
				if (!spinningUp) {
					spinStartTime = System.currentTimeMillis();
					spinningUp = true;
				} else if (System.currentTimeMillis() - spinStartTime > 1500) {
						shooterIsSped = true;
						spinningUp = false;
				}
				systemLayer.feedStop();
			} else {
				systemLayer.feed();
			}
			systemLayer.shoot();
		//separate buttons for feeding and shooting 
		} else {
			if (GamePad.getDriverJoystick().getButton(JoystickConstants.feedButtonID)){
				systemLayer.feed();
			} else {
				systemLayer.feedStop();
			}
			if (GamePad.getDriverJoystick().getButton(JoystickConstants.shootButtonID)){
				systemLayer.shoot();
			} else {
				systemLayer.shootStop();
				shooterIsSped = false;
			}
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}