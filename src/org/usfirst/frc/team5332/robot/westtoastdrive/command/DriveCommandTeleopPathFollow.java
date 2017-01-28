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
		
		double[] coords = {0,0};
		
		if(systemLayer.getCurrentLocalCoords()[0] == coords[0] && systemLayer.getCurrentLocalCoords()[1] == coords[1]){
			coords = path.getNextCoordinate();
		}
		
//		// Magically rotate the other point to the x axis around the origin (May not need this)
//		double a = Math.atan2(coords[0], coords[1]);
//		double[] rotCoord = {coords[0] * Math.cos(a) - coords[1] * Math.sin(a), coords[0] * Math.sin(a) + coords[1] * Math.cos(a)};
		
		/*
		 * Set the speed proportionally to the next coord relative to the origin (e.g (40,50) gives a ratio of 4/5); skim the extra.
		 * Since the path's first point is our current position it will be a ratio relative to the origin.
		 */
		systemLayer.setDriveLeft(skim(coords[0]/coords[1]));
		systemLayer.setDriveRight(skim(coords[1]/coords[0]));
		
	}
	
	private double skim(double vel){
		if(vel > 1)
			return -((vel - 1.0) * gain);
		else if(vel < -1)
			return -((vel + 1.0) * gain);
		return vel;
	}
	
	@Override
	public String getName(){
		return "TELEOP_VISION_TRACK";
	}
	
}