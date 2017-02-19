package org.usfirst.frc.team5332.robot.westtoastdrive.command;

import org.usfirst.frc.team5332.robot.control.GamePad;
import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;

public class DriveCommandTeleopTank extends DriveCommandBase{
	
	public DriveCommandTeleopTank(){
		
	}
	
	
	@Override
	public void init(){
		
	}

	@Override
	public void periodicUpdate(){
		systemLayer.setDriveLeft(GamePad.getDriverJoystick().getLeftYAxisValue());
		systemLayer.setDriveRight(GamePad.getDriverJoystick().getRightYAxisValue());
		
	}
	
	@Override
	public String getName() {
		return "TELEOP_TANK";
	}
	
}