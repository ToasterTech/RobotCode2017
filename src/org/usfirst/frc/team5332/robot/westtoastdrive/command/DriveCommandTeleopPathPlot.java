package org.usfirst.frc.team5332.robot.westtoastdrive.command;

import org.usfirst.frc.team5332.robot.control.DriverJoystickSet;
import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;
import org.usfirst.frc.team5332.robot.westtoastdrive.path.Path;
import org.usfirst.frc.team5332.util.Constants;

public class DriveCommandTeleopPathPlot extends DriveCommandBase{
	
	private DriverJoystickSet js;
	private double gain = 1;
	
	private Path path;
	
	public DriveCommandTeleopPathPlot(String pathName){
		js = new DriverJoystickSet();
		path = new Path(pathName);
	}
	
	
	@Override
	public void init(){
		
	}

	@Override
	public void periodicUpdate(){
		double leftSpeed, rightSpeed, turnLeft, turnRight, turn;
		
		/*
		 * Throttle: Left joystick
		 * Turn: Right joystick
		 */
		if(js.getThrottleValue() < 0.05){
			if(js.doFixedSpeedTurn() && js.getTurnValue() < 0.05)
				turn = Constants.turningJoystickFixedSpeed;
			else
				turn = js.getTurnValue()*(Constants.turningScalar);
		}
		
		turn = js.getTurnValue()*(Constants.turningScalar * Math.abs(js.getThrottleValue()));
		
		turnLeft = js.getThrottleValue() + turn;
		turnRight = js.getThrottleValue() - turn;
		
		leftSpeed = turnLeft + skim(turnRight);
		rightSpeed = turnRight + skim(turnLeft);
		
		/*
		 * if finishButton.isPressed
		 * 	serializePath
		 * else
		 * 	addCurrentCoordinatesToPath
		 */
		
		systemLayer.setDriveLeft(leftSpeed);
		systemLayer.setDriveRight(rightSpeed);
		
	}
	
	private double skim(double vel){
		if(vel > 1)
			return -((vel - 1.0) * gain);
		else if(vel < -1)
			return -((vel + 1.0) * gain);
		return 0;
	}
	
	@Override
	public String getName() {
		return "TELEOP_USER";
	}
	
}