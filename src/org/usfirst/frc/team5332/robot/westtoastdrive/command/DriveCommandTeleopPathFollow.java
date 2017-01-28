package org.usfirst.frc.team5332.robot.westtoastdrive.command;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;
import org.usfirst.frc.team5332.robot.westtoastdrive.path.Path;

public class DriveCommandTeleopPathFollow extends DriveCommandBase{
	
	private Path path;
	
	public DriveCommandTeleopPathFollow(Path path){
		this.path = path;
	}

	@Override
	public void init(){
		
	}

	@Override
	public void periodicUpdate(){
		double[] coords = path.getNextCoordinate();
		coords[0] -= systemLayer.getCurrentGlobalCoords()[0];
		coords[1] -= systemLayer.getCurrentGlobalCoords()[1];
		double a = Math.atan2(coords[0], coords[1]);
		double[] rP = { coords[0] * cos(a) - p2.y * sin(a), p2.x * sin(a) + p2.y * cos(a) }
	}

	@Override
	public String getName(){
		return "TELEOP_VISION_TRACK";
	}
	
}