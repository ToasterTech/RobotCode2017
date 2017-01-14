package org.usfirst.frc.team5332.robot.drive;

import org.usfirst.frc.team5332.robot.drive.base.DriveCommandBase;
import org.usfirst.frc.team5332.robot.util.JoystickInterface;

public class DriveCommand extends DriveCommandBase{
	
	private JoystickInterface js;
	
	public DriveCommand(){
		js = new JoystickInterface(1);
	}

	@Override
	public void init(){
		
	}

	@Override
	public void periodicUpdate(){
		systemLayer.setDriveLeft(js.getJoystickLeft());
		systemLayer.setDriveRight(js.getJoystickRight());
	}
	
}