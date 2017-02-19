package org.usfirst.frc.team5332.robot.intake.command;

import org.usfirst.frc.team5332.robot.control.GamePad;
import org.usfirst.frc.team5332.robot.intake.base.IntakeCommandBase;
import org.usfirst.frc.team5332.util.constants.JoystickConstants;

public class IntakeCommandTeleop extends IntakeCommandBase{
	
	public IntakeCommandTeleop(){
		
	}
	
	@Override
	public void init(){
		
	}

	@Override
	public void periodicUpdate(){
		if(GamePad.getDriverJoystick().getButton(JoystickConstants.intakeButtonID)){
			systemLayer.intake();
		}else if(GamePad.getDriverJoystick().getButton(JoystickConstants.outtakeButtonID)){
			systemLayer.reverse();
		}else{
			systemLayer.stopIntake();
		}
	}

	@Override
	public String getName() {
		return "TELEOP_DNU";
	}
	
}