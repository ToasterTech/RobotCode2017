package org.usfirst.frc.team5332.robot.westtoastdrive.command.auto;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;

public class DriveCommandAutoLeft extends DriveCommandBase{

	private long startTime;
	private double autoSpeedStraight;
	private double autoSpeedLeftTurn;
	private double autoSpeedRightTurn;
	private double autoTime1;
	private double autoTime2;
	private double autoTime3;

	public DriveCommandAutoLeft(double autoSpeedStraight , double autoSpeedLeftTurn , double autoSpeedRightTurn , 
								double autoTime1 , double autoTime2 , double autoTime3) {

	}

	@Override
	public void init() {
		startTime = System.currentTimeMillis();
	}

	@Override
	public void periodicUpdate() {
		if((System.currentTimeMillis() - startTime)/1000 < autoTime1){
			systemLayer.setDriveLeft(autoSpeedStraight);
			systemLayer.setDriveRight(autoSpeedStraight);
		}else if((System.currentTimeMillis() - startTime)/1000 > autoTime1
				&& (System.currentTimeMillis() - startTime)/1000 < autoTime2){
			systemLayer.setDriveLeft(autoSpeedLeftTurn);
			systemLayer.setDriveRight(-autoSpeedRightTurn);
		}else if((System.currentTimeMillis() - startTime)/1000 > autoTime2 
				&& (System.currentTimeMillis() - startTime)/1000 < autoTime3){
			systemLayer.setDriveLeft(autoSpeedStraight);
			systemLayer.setDriveRight(autoSpeedStraight);	
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


