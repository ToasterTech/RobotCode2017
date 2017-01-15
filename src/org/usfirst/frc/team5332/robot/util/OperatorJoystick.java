package org.usfirst.frc.team5332.robot.util;

import edu.wpi.first.wpilibj.Joystick;

public class OperatorJoystick{
	
	private Joystick js;
	
	public OperatorJoystick(){
		js = new Joystick(Constants.operatorJoystickPort);
	}
	
	public double getGearManipulatorValue(){
		return js.getRawAxis(Constants.throttleJoystickAxisIndex);
	}
	
	public boolean getButton(int index){
		return js.getRawButton(index);
	}
	
}