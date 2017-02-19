package org.usfirst.frc.team5332.robot.control;

import org.usfirst.frc.team5332.util.constants.HardwareConstants;
import org.usfirst.frc.team5332.util.constants.JoystickConstants;
import org.usfirst.frc.team5332.util.constants.TuningConstants;

import edu.wpi.first.wpilibj.Joystick;

public class GamePad{
	
	private Joystick js;
	
	private static GamePad driverJoystick = null;
	private static GamePad operatorJoystick = null;
	
	public static GamePad getDriverJoystick(){
		if(driverJoystick == null){
			driverJoystick = new GamePad(HardwareConstants.driverJoystickPort);
		}
		return driverJoystick;
	}
	
	public static GamePad getOperatorJoystick(){
		if(operatorJoystick == null){
			operatorJoystick = new GamePad(HardwareConstants.operatorJoystickPort);
		}
		return operatorJoystick;
	}
	
	private GamePad(int port){
		js = new Joystick(port);
	}
	
	public double getLeftXAxisValue(){
		return js.getRawAxis(JoystickConstants.gamePadLeftXAxis);
	}
	
	public double getRightXAxisValue(){
		return js.getRawAxis(JoystickConstants.gamePadRightXAxis);
	}
	
	public double getLeftYAxisValue(){
		return js.getRawAxis(JoystickConstants.gamePadLeftYAxis);
	}
	
	public double getRightYAxisValue(){
		return js.getRawAxis(JoystickConstants.gamePadRightYAxis);
	}
	
	public boolean getRightBumper(){
		return js.getRawAxis(JoystickConstants.gamePadRightBumperAxisIndex) > TuningConstants.gamePadBumperThresh;
	}
	
	public boolean getLeftBumper(){
		return js.getRawAxis(JoystickConstants.gamePadLeftBumperAxisIndex) > TuningConstants.gamePadBumperThresh;
	}
	
	public double getRightBumperRaw(){
		return js.getRawAxis(JoystickConstants.gamePadRightBumperAxisIndex);
	}
	
	public double getLeftBumperRaw(){
		return js.getRawAxis(JoystickConstants.gamePadLeftBumperAxisIndex);
	}
	
	public boolean getButton(int index){
		return js.getRawButton(index);
	}
	
}