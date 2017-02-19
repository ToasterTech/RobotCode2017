package org.usfirst.frc.team5332.robot.intake.command;

import org.usfirst.frc.team5332.robot.control.GamePad;
import org.usfirst.frc.team5332.robot.intake.base.IntakeCommandBase;

public class IntakeCommandTeleop extends IntakeCommandBase{
	
	public IntakeCommandTeleop(){
		
	}
	
	@Override
	public void init(){
		
	}

	@Override
	public void periodicUpdate(){
		if(GamePad.getDriverJoystick().getLeftBumper()){
			systemLayer.intake();
		}else if(GamePad.getDriverJoystick().getRightBumper()){
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