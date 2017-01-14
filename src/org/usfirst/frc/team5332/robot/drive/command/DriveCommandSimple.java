package org.usfirst.frc.team5332.robot.drive.command;

import org.usfirst.frc.team5332.robot.drive.base.DriveCommandBase;
import org.usfirst.frc.team5332.robot.util.JoystickInterface;

/**
 * The simple drive command layer that directly sets joystick input to the drive train.
 * 
 * @author J-Dierberger
 */
public class DriveCommandSimple extends DriveCommandBase{
	
	// Create a joystick variable.
	private JoystickInterface js;
	
	/**
	 * Create a DriveCommandSimple command layer.
	 */
	public DriveCommandSimple(){
		// Create the joystick.
		js = new JoystickInterface(1);
	}

	@Override
	public void init(){
		
	}

	@Override
	public void periodicUpdate(){
		// Set the drive train speeds to the current joystick values.
		systemLayer.setDriveLeft(js.getJoystickLeft());
		systemLayer.setDriveRight(js.getJoystickRight());
	}
	
}