package org.usfirst.frc.team5332.robot.control;

import org.usfirst.frc.team5332.util.Constants;

import edu.wpi.first.wpilibj.Joystick;

public class GamePad{
	
	private Joystick js;
	
	public GamePad(){
		js = new Joystick(Constants.operatorJoystickPort);
	}
	
	public double getLeftAxisValue(){
		return js.getRawAxis(Constants.gamePadLeftAxis);
	}
	public double getRightAxisValue(){
		return js.getRawAxis(Constants.gamePadRightAxis);
	}
	
	public boolean getRightBumper(){
		return js.getRawAxis(Constants.gamePadRightBumperAxisIndex) > Constants.gamePadBumperThresh;
	}
	
	public boolean getLeftBumper(){
		return js.getRawAxis(Constants.gamePadLeftBumperAxisIndex) > Constants.gamePadBumperThresh;
	}
	
	public boolean getButton(int index){
		return js.getRawButton(index);
	}
	
}