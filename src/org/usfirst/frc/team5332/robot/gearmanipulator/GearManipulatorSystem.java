package org.usfirst.frc.team5332.robot.gearmanipulator;

import org.usfirst.frc.team5332.robot.gearmanipulator.base.GearManipulatorHardwareBase;
import org.usfirst.frc.team5332.robot.gearmanipulator.base.GearManipulatorSystemBase;
import org.usfirst.frc.team5332.util.Constants;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

public class GearManipulatorSystem extends GearManipulatorSystemBase{
	
	private GearManipulatorHardwareBase hardwareLayer;
	private double motorSpeed;
	private int targetCounts;
	private boolean goToCounts;
	private boolean toggleGates;
	
	@Override
	public void init() {
		motorSpeed = 0;
		targetCounts = 0;
		goToCounts = false;
		toggleGates = true;
	}

	@Override
	public void periodicUpdate() {
		if(goToCounts && hardwareLayer.getEncoderCounts() < targetCounts){
			hardwareLayer.driveGearManiupulator(motorSpeed * Constants.gearManipulatorMoveSpeed);
		}else if(goToCounts && hardwareLayer.getEncoderCounts() > targetCounts){
			hardwareLayer.driveGearManiupulator(-motorSpeed * Constants.gearManipulatorMoveSpeed);
		}
		
		hardwareLayer.driveGearManiupulator(motorSpeed * Constants.gearManipulatorMoveSign);
		
	}

	@Override
	public void setChild(GearManipulatorHardwareBase c) {
		hardwareLayer = c;
	}

	@Override
	public void toggleGates() {
		hardwareLayer.toggleLowerPistons();
		hardwareLayer.toggleUpperPistons();
	}

	@Override
	public void goLeft() {
		motorSpeed = Constants.gearManipulatorMoveSpeed;
	}

	@Override
	public void goRight() {
		motorSpeed = -Constants.gearManipulatorMoveSpeed;
	}

	@Override
	public void goLeft(double speed) {
		motorSpeed = speed;
	}

	@Override
	public void goRight(double speed) {
		motorSpeed = -speed;
	}

	@Override
	public void gotoCounts(int counts) {
		motorSpeed = Constants.gearManipulatorMoveSpeed;
		targetCounts = counts;
		goToCounts = true;
	}

	@Override
	public void gotoCounts(double speed, int counts) {
		motorSpeed = speed;
		targetCounts = counts;
		goToCounts = true;
	}

	@Override
	public void centerManipulator() {
		gotoCounts(0);
	}

	@Override
	public int getCounts() {
		return hardwareLayer.getEncoderCounts();
	}
	
	@Override
	public String getName() {
		return "FIXED_LAYER_DNU";
	}

	@Override
	public void userControlOverride() {
		goToCounts = false;
	}
	
}