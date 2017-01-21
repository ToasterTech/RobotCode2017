package org.usfirst.frc.team5332.robot.intake.command;

import org.usfirst.frc.team5332.robot.control.OperatorJoystick;
import org.usfirst.frc.team5332.robot.intake.base.IntakeCommandBase;

public class IntakeCommandTeleop extends IntakeCommandBase{
	
	private OperatorJoystick js;
	
	public IntakeCommandTeleop(){
		js = new OperatorJoystick();
	}
	
	@Override
	public void init(){
		
	}

	@Override
	public void periodicUpdate(){
		if(js.getLeftBumper()){
			systemLayer.intake();
		}else if(js.getRightBumper()){
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