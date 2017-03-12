package org.usfirst.frc.team5332.robot.westtoastdrive.command.auto;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;

public class DriveCommandAutoRight extends DriveCommandBase{

	private long startTime;
	private double autoSpeedStraight;
	private double autoSpeedRightTurn;
	private double autoSpeedLeftTurn;
	private double autoTime1;
	private double autoTime2;
	private double autoTime3;
	
	public DriveCommandAutoRight(double autoSpeedStraight, double autoSpeedRightTurn, double autoSpeedLeftTurn, double autoTime1, double autoTime2, double autoTime3){
		this.autoSpeedStraight = autoSpeedStraight;
		this.autoSpeedRightTurn = autoSpeedRightTurn;
		this.autoSpeedLeftTurn = autoSpeedLeftTurn;
		this.autoTime1 = autoTime1;
		this.autoTime2 = autoTime2;
		this.autoTime3 = autoTime3;
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
			systemLayer.setDriveLeft(-autoSpeedLeftTurn);
			systemLayer.setDriveRight(autoSpeedRightTurn);
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


