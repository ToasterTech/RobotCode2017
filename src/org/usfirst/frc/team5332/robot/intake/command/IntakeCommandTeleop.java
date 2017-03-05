package org.usfirst.frc.team5332.robot.intake.command;

import org.usfirst.frc.team5332.robot.control.GamePad;
import org.usfirst.frc.team5332.robot.intake.base.IntakeCommandBase;
import org.usfirst.frc.team5332.util.constants.JoystickConstants;

public class IntakeCommandTeleop extends IntakeCommandBase{
	private boolean toggleIntake = false;
	private boolean lastPress = false;
	
	public IntakeCommandTeleop(){
		
	}
	
	@Override
	public void init(){
		
	}

	@Override
	public void periodicUpdate(){
		if(GamePad.getDriverJoystick().getButton(JoystickConstants.intakeButtonID) && toggleIntake == true && !lastPress) {
			toggleIntake = false;
		} else if(GamePad.getDriverJoystick().getButton(JoystickConstants.intakeButtonID) && toggleIntake == false && !lastPress) {
			toggleIntake = true;
		}
		
		lastPress = GamePad.getDriverJoystick().getButton(JoystickConstants.intakeButtonID);
		
		if(toggleIntake){
			systemLayer.intake();
		} else{
			systemLayer.stopIntake();
		}
		
		
		
	}

	@Override
	public String getName() {
		return "TELEOP_DNU";
	}
	
}