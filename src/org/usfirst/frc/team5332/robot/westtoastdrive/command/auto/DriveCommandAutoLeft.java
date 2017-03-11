package org.usfirst.frc.team5332.robot.westtoastdrive.command.auto;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;

public class DriveCommandAutoLeft extends DriveCommandBase{

	private long startTime;

	public DriveCommandAutoLeft(){

	}

	@Override
	public void init() {
		startTime = System.currentTimeMillis();
	}

	@Override
	public void periodicUpdate() {
		if((System.currentTimeMillis() - startTime)/1000 < 3){
			systemLayer.setDriveLeft(0.4);
			systemLayer.setDriveRight(0.4);
		}else if((System.currentTimeMillis() - startTime)/1000 > 3 && (System.currentTimeMillis() - startTime)/1000 < 3.3){
			systemLayer.setDriveLeft(0.4);
			systemLayer.setDriveRight(0);
		}else if((System.currentTimeMillis() - startTime)/1000 > 3.3 && (System.currentTimeMillis() - startTime)/1000 < 5.5){
			systemLayer.setDriveLeft(0.4);
			systemLayer.setDriveRight(0.4);	
		}else{
			systemLayer.setDriveLeft(0);
			systemLayer.setDriveRight(0);
		}
	}

	@Override
	public String getName() {
		return "DRIVE_AUTO_STRAIGHT";
	}

}


