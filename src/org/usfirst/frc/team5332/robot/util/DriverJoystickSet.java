package org.usfirst.frc.team5332.robot.util;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Provides a simple interface with a joystick, where we can specify methods with named axes, etc.
 * 
 * @author J-Dierberger
 */
public class DriverJoystickSet{
	
	// The Joystick object to get stuff from.
	private Joystick turningJs;
	private Joystick throttleJs;
	
	/**
	 * Create a JoystickInterface object.
	 * 
	 * @param port The USB port index.
	 */
	public DriverJoystickSet(){
		turningJs = new Joystick(Constants.driverTurnJoystickPort);
		throttleJs = new Joystick(Constants.driverThrottleJoystickPort);
	}
	
	/**
	 * Returns the current value of the throttle joystick. 
	 * 
	 * @return The current value of the throttle joystick.
	 */
	public double getThrottleValue(){
		return throttleJs.getRawAxis(Constants.throttleJoystickAxisIndex);
	}
	
	public double getTurnValue() {
		return turningJs.getRawAxis(Constants.turningJoystickRangeAxisIndex);
	}
	
	public boolean doFixedSpeedTurn(){
		return turningJs.getRawAxis(Constants.turningJoystickFixedAxisIndex) > Constants.turningJoystickFixedThresh;
	}
	
	public boolean getTurningButton(int index){
		return turningJs.getRawButton(index);
	}
	
	public boolean getThrottleButton(int index){
		return throttleJs.getRawButton(index);
	}
}