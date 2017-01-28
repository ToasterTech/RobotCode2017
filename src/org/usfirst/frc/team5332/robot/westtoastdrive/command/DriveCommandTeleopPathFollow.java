package org.usfirst.frc.team5332.robot.westtoastdrive.command;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;
import org.usfirst.frc.team5332.robot.westtoastdrive.path.Path;
import org.usfirst.frc.team5332.util.Constants;

public class DriveCommandTeleopPathFollow extends DriveCommandBase{
	
	private Path path;
	private double gain = 1;
	
	public DriveCommandTeleopPathFollow(Path path){
		this.path = path;
	}

	@Override
	public void init(){
		
	}

	@Override
	public void periodicUpdate(){
		// Citation: http://stackoverflow.com/questions/25835510/draw-curved-line-between-two-points
		double[] coords = path.getNextCoordinate();
		
		// Translate our coordinates so that the robot's current position is on the origin
		coords[0] -= systemLayer.getCurrentGlobalCoords()[0];
		coords[1] -= systemLayer.getCurrentGlobalCoords()[1];
		
		// Magically rotate the other point to the x axis around the origin (May not need this)
		double a = Math.atan2(coords[0], coords[1]);
		double[] rP = {coords[0] * Math.cos(a) - coords[1] * Math.sin(a), coords[0] * Math.sin(a) + coords[1] * Math.cos(a)};
		
		// Set the speed proportionally to the next coord relative to the origin (e.g (40,50) gives a ratio of 4/5)
		
		
	}

	@Override
	public String getName(){
		return "TELEOP_VISION_TRACK";
	}
	
}