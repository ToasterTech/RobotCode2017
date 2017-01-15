package org.usfirst.frc.team5332.robot.util;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Provides a simple interface with a joystick, where we can specify methods with named axes, etc.
 * 
 * @author J-Dierberger
 */
public class OperatorJoystick{
	
	// The Joystick object to get stuff from.
	private Joystick js;
	
	/**
	 * Create a JoystickInterface object.
	 * 
	 * @param port The USB port index.
	 */
	public OperatorJoystick(){
		js = new Joystick(Constants.operatorJoystickPort);
	}
	
	/**
	 * Returns the current value of the throttle joystick. 
	 * 
	 * @return The current value of the throttle joystick.
	 */
	public double get(){
		return js.getRawAxis(Constants.throttleJoystickAxisIndex);
	}
	
}