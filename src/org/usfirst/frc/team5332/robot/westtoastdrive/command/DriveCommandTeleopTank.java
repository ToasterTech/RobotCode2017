package org.usfirst.frc.team5332.robot.westtoastdrive.command;

import org.usfirst.frc.team5332.robot.control.GamePad;
import org.usfirst.frc.team5332.robot.westtoastdrive.DriveHardware;
import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;

public class DriveCommandTeleopTank extends DriveCommandBase{
	
	
	
	public DriveCommandTeleopTank(){
		
	}
	
	
	@Override
	public void init(){
		
	}

	@Override
	public void periodicUpdate(){
		if (GamePad.getDriverJoystick().getPOV() == 0){
			DriveHardware.reverseDrive = false;
		} else if (GamePad.getDriverJoystick().getPOV() == 180) {
			DriveHardware.reverseDrive = true;
		}

		if (GamePad.getDriverJoystick().getLeftZAxisValue() > 0.05) {
			systemLayer.setDriveLeft(GamePad.getDriverJoystick().getLeftZAxisValue());
			systemLayer.setDriveRight(GamePad.getDriverJoystick().getLeftZAxisValue());		
		} else if (GamePad.getDriverJoystick().getRightZAxisValue() > 0.05) {
			systemLayer.setDriveLeft(-GamePad.getDriverJoystick().getRightZAxisValue());
			systemLayer.setDriveRight(-GamePad.getDriverJoystick().getRightZAxisValue());
		} else {
			systemLayer.setDriveLeft(GamePad.getDriverJoystick().getLeftYAxisValue());
			systemLayer.setDriveRight(GamePad.getDriverJoystick().getRightYAxisValue());		}
	}
	
	@Override
	public String getName() {
		return "TELEOP_TANK";
	}
	
}