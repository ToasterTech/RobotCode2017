package org.usfirst.frc.team5332.robot.gearintake.command;

import org.usfirst.frc.team5332.robot.control.GamePad;
import org.usfirst.frc.team5332.robot.gearintake.GearIntakeSystem;
import org.usfirst.frc.team5332.robot.gearintake.base.GearIntakeCommandBase;
import org.usfirst.frc.team5332.robot.gearintake.base.GearIntakeSystemBase;
import org.usfirst.frc.team5332.util.constants.JoystickConstants;

public class GearIntakeCommandTeleop extends GearIntakeCommandBase {

	private GearIntakeSystemBase systemLayer;
	private GamePad joystick;
	
	public GearIntakeCommandTeleop() {
		joystick = GamePad.getDriverJoystick();
	}	
	
	@Override
	public void setChild(GearIntakeSystemBase c) {
		systemLayer = c;
	}

	@Override
	public void init() {
		//TODO
	}

	@Override
	public void periodicUpdate() {
		if (joystick.getButton(JoystickConstants.lowerGearIntake)) {
			systemLayer.moveIntakeDown();
		}
		else if (joystick.getButton(JoystickConstants.raiseGearIntake)) {
			systemLayer.moveIntakeUp();
		}
		if (joystick.getButton(JoystickConstants.intakeWheelsForwards)) {
			systemLayer.runForwards();
		}
		else if (joystick.getButton(JoystickConstants.intakeWheelsReverse)) {
			systemLayer.runReverse();
		}
		else {
			systemLayer.runStop();
		}
	}

	@Override
	public String getName() {
		return null;
	}
	
}
