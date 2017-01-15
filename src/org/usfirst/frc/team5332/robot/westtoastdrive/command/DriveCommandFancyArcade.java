package org.usfirst.frc.team5332.robot.westtoastdrive.command;

import org.usfirst.frc.team5332.robot.util.Constants;
import org.usfirst.frc.team5332.robot.util.DriverJoystickSet;
import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;

public class DriveCommandFancyArcade extends DriveCommandBase{
	
	private DriverJoystickSet js;
	private double gain = 1;
	
	public DriveCommandFancyArcade(){
		js = new DriverJoystickSet();
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
	
}