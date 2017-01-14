package org.usfirst.frc.team5332.robot.util;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickInterface{
	
	private Joystick js;
	
	private static final int JS_AXIS_RIGHT = 0;
	private static final int JS_AXIS_LEFT = 1;
	
	public JoystickInterface(int port){
		js = new Joystick(port);
	}
	
	public double getJoystickRight(){
		return js.getRawAxis(JS_AXIS_RIGHT);
	}
	
	public double getJoystickLeft(){
		return js.getRawAxis(JS_AXIS_LEFT);
	}
	
	public boolean getJoystickButton(int button){
		return js.getRawButton(button);
	}
	
}