package org.usfirst.frc.team5332.robot.util;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Provides a simple interface with a joystick, where we can specify methods with named axes, etc.
 * 
 * @author J-Dierberger
 */
public class JoystickInterface{
	
	// The Joystick object to get stuff from.
	private Joystick js;
	
	// The axis index constants. If more special axes appear we will add them later.
	private static final int JS_AXIS_RIGHT = 0;
	private static final int JS_AXIS_LEFT = 1;
	
	/**
	 * Create a JoystickInterface object.
	 * 
	 * @param port The USB port index.
	 */
	public JoystickInterface(int port){
		js = new Joystick(port);
	}
	
	/**
	 * Get the right side value of the joystick, on a scale of 0.0-1.0.
	 * 
	 * @return The current right side value of the joystick, on a scale of 0.0-1.0.
	 */
	public double getJoystickRight(){
		return js.getRawAxis(JS_AXIS_RIGHT);
	}
	
	/**
	 * Get the left side value of the joystick, on a scale of 0.0-1.0.
	 * 
	 * @return The current left side value of the joystick, on a scale of 0.0-1.0.
	 */
	public double getJoystickLeft(){
		return js.getRawAxis(JS_AXIS_LEFT);
	}
	
	/**
	 * Get if a button is pressed or not.
	 * 
	 * @param button The button index to check.
	 * @return If the button at the given index is pressed or not.
	 */
	public boolean getJoystickButton(int button){
		return js.getRawButton(button);
	}
	
}