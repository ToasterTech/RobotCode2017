package org.usfirst.frc.team5332.robot.westtoastdrive.command.auto;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;
import org.usfirst.frc.team5332.util.constants.TuningConstants;

public class DriveCommandAutoStraight extends DriveCommandBase{

	private double speed;
	private double seconds;
	private long startTime;
	
	public DriveCommandAutoStraight(double speed, double seconds){
		this.speed = speed;
		this.seconds = seconds;
	}
	
	@Override
	public void init() {
		startTime = System.currentTimeMillis();
	}

	@Override
	public void periodicUpdate() {
		if((System.currentTimeMillis() - startTime)/1000 < seconds){
			if(systemLayer.getDistanceBack() < 0 || systemLayer.getDistanceBack() > 38+TuningConstants.ultrasonicDistInRobot){
				systemLayer.setDriveLeft(speed);
				systemLayer.setDriveRight(speed);
			}
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
