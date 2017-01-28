package org.usfirst.frc.team5332.robot.gearmanipulator.command;

import org.usfirst.frc.team5332.robot.gearmanipulator.base.GearManipulatorCommandBase;
import org.usfirst.frc.team5332.util.Constants;
import org.usfirst.frc.team5332.robot.control.GamePad;

public class GearManipulatorCommandTeleop extends GearManipulatorCommandBase{
	
	private GamePad js;
	
	public GearManipulatorCommandTeleop(){
		js = new GamePad();
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void periodicUpdate() {
		if(js.getLeftBumper() && js.getRightBumper()){
			systemLayer.stop();
		}else if(js.getLeftBumper()){
			systemLayer.goLeft();
		}else if(js.getRightBumper()){
			systemLayer.goRight();
		}else{
			systemLayer.stop();
		}
		
		if(js.getButton(Constants.gmPistonToggleButton)){
			systemLayer.toggleGates();
		}
		
	}

	@Override
	public String getName() {
		return "TELEOP_DNU";
	}

}
