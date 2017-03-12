package org.usfirst.frc.team5332.autoselect;

import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;
import java.util.ArrayList;
import java.util.HashMap;
import org.usfirst.frc.team5332.robot.westtoastdrive.command.auto.DriveCommandAutoLeft;
import org.usfirst.frc.team5332.robot.westtoastdrive.command.auto.DriveCommandAutoNothing;
import org.usfirst.frc.team5332.robot.westtoastdrive.command.auto.DriveCommandAutoRight;
import org.usfirst.frc.team5332.robot.westtoastdrive.command.auto.DriveCommandAutoStraight;

public class ToastSelector {
	private ArrayList<DriveCommandBase> driveAutoList;
	private HashMap<String, DriveCommandBase> driveAutoMap;
	
	public ToastSelector () {
		driveAutoMap=new HashMap<String,DriveCommandBase>();
		
		DriveCommandBase auto = new DriveCommandAutoStraight(0.4,3);
		driveAutoMap.put(auto.getName(), auto);
		
		auto = new DriveCommandAutoLeft(0.4 , 0.4 , 0.4 , 3 , 3.3 , 5.5);
		driveAutoMap.put(auto.getName(), auto);
		
		auto = new DriveCommandAutoRight(0.4 , 0.4 , 0.4 , 4.247 , 3.3 , 3.900);
		driveAutoMap.put(auto.getName(), auto);
	}
	public DriveCommandBase getDriveAuto(String input){
		DriveCommandBase out=driveAutoMap.get(input);
		if(out==null)
			return new DriveCommandAutoNothing();
		return out;
	}

}
