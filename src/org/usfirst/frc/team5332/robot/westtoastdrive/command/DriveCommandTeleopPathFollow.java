package org.usfirst.frc.team5332.robot.westtoastdrive.command;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;
import org.usfirst.frc.team5332.robot.westtoastdrive.path.Path;

public class DriveCommandTeleopPathFollow extends DriveCommandBase{
	
	// Remove when we actually use the path.
	@SuppressWarnings("unused")
	private Path path;
	
	public DriveCommandTeleopPathFollow(Path path){
		this.path = path;
	}

	@Override
	public void init(){
		
	}

	@Override
	public void periodicUpdate(){
		
	}

	@Override
	public String getName(){
		return "TELEOP_VISION_TRACK";
	}
	
}