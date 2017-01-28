package org.usfirst.frc.team5332.robot.control;

import org.usfirst.frc.team5332.util.Constants;

import edu.wpi.first.wpilibj.Joystick;

public class OperatorJoystick{
	
	private Joystick js;
	
	public OperatorJoystick(){
		js = new Joystick(Constants.operatorJoystickPort);
	}
	
	public double getGearManipulatorValue(){
		return js.getRawAxis(Constants.throttleJoystickAxisIndex);
	}
	
	public boolean getRightBumper(){
		return js.getRawAxis(Constants.operatorJoystickRightBumperAxisIndex) > Constants.operatorJoystickBumperThresh;
	}
	
	public boolean getLeftBumper(){
		return js.getRawAxis(Constants.operatorJoystickLeftBumperAxisIndex) > Constants.operatorJoystickBumperThresh;
	}
	
	public boolean getButton(int index){
		return js.getRawButton(index);
	}
	
}