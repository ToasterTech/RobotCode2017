package org.usfirst.frc.team5332.robot.westtoastdrive.command;

import org.usfirst.frc.team5332.robot.control.GamePad;
import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;
import org.usfirst.frc.team5332.util.Constants;

public class DriveCommandTeleop extends DriveCommandBase{
	
	private GamePad js;
	private double gain = 1;
	
	public DriveCommandTeleop(){
		js = new GamePad();
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
		if(js.getLeftAxisValue() < 0.05){
			turn = js.getRightAxisValue()*(Constants.turningScalar);
		}
		
		turn = js.getRightAxisValue()*(Constants.turningScalar * Math.abs(js.getLeftAxisValue()));
		
		turnLeft = js.getLeftAxisValue() + turn;
		turnRight = js.getLeftAxisValue() - turn;
		
		leftSpeed = turnLeft + skim(turnRight);
		rightSpeed = turnRight + skim(turnLeft);
		
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