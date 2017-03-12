package org.usfirst.frc.team5332.robot.westtoastdrive.command.auto;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;
import org.usfirst.frc.team5332.util.constants.TuningConstants;

public class DriveCommandAutoRight extends DriveCommandBase{

	private long startTime;

	public DriveCommandAutoRight(){

	}

	@Override
	public void init() {
		startTime = System.currentTimeMillis();
	}

	@Override
	public void periodicUpdate() {
		if((System.currentTimeMillis() - startTime)/1000 < TuningConstants.autoTime1){
			systemLayer.setDriveLeft(TuningConstants.autoSpeedStraight);
			systemLayer.setDriveRight(TuningConstants.autoSpeedStraight);
		}else if((System.currentTimeMillis() - startTime)/1000 > TuningConstants.autoTime1 
				&& (System.currentTimeMillis() - startTime)/1000 < TuningConstants.autoTime2){
			systemLayer.setDriveLeft(-TuningConstants.autoSpeedLeftTurn);
			systemLayer.setDriveRight(TuningConstants.autoSpeedRightTurn);
		}else if((System.currentTimeMillis() - startTime)/1000 > TuningConstants.autoTime2 
				&& (System.currentTimeMillis() - startTime)/1000 < TuningConstants.autoTime3){
			systemLayer.setDriveLeft(TuningConstants.autoSpeedStraight);
			systemLayer.setDriveRight(TuningConstants.autoSpeedStraight);	
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


