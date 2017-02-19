package org.usfirst.frc.team5332.robot.westtoastdrive.command;

import org.usfirst.frc.team5332.robot.control.GamePad;
import org.usfirst.frc.team5332.robot.westtoastdrive.base.DriveCommandBase;
import org.usfirst.frc.team5332.util.constants.TuningConstants;

public class DriveCommandTeleopArcade extends DriveCommandBase{
	
	private double gain = 1;
	
	public DriveCommandTeleopArcade(){
		
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
		if(GamePad.getDriverJoystick().getLeftYAxisValue() < 0.05){
			turn = GamePad.getDriverJoystick().getRightXAxisValue()*(TuningConstants.turningScalar);
		}
		
		turn = GamePad.getDriverJoystick().getRightXAxisValue()*(TuningConstants.turningScalar * Math.abs(GamePad.getDriverJoystick().getLeftYAxisValue()));
		
		turnLeft = GamePad.getDriverJoystick().getLeftYAxisValue() + turn;
		turnRight = GamePad.getDriverJoystick().getLeftYAxisValue() - turn;
		
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
		return "TELEOP_ARCADE";
	}
	
}