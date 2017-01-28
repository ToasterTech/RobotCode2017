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
		// Stop the gear manipulator if getLeftBumper and getRightBumber are detected at the same time.
		if(js.getLeftBumper() && js.getRightBumper()){
			systemLayer.stop();
		}
		// Tell the gear manipulator to go left  if getLeftBumper is activated.
		else if(js.getLeftBumper()){
			systemLayer.goLeft();
		}
		// Tell the gear manipulator to go right if getRightBumper is activated.
		else if(js.getRightBumper()){
			systemLayer.goRight();
		}
		// Tell the gear manipulator to stop.
		else{
			systemLayer.stop();
		}
		
		// Toggle gates if getButton is equal to gmPistonToggleButton.
		if(js.getButton(Constants.gmPistonToggleButton)){
			systemLayer.toggleGates();
		}
		
	}

	@Override
	public String getName() {
		return "TELEOP_DNU";
	}

}