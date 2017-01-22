package org.usfirst.frc.team5332.robot.gearmanipulator;

import org.usfirst.frc.team5332.robot.gearmanipulator.base.GearManipulatorHardwareBase;
import org.usfirst.frc.team5332.robot.gearmanipulator.base.GearManipulatorSystemBase;
import org.usfirst.frc.team5332.util.Constants;

public class GearManipulatorSystem extends GearManipulatorSystemBase{
	
	private GearManipulatorHardwareBase hardwareLayer;
	private double motorSpeed;
	private int targetCounts;
	private boolean goToCounts;
	
	@Override
	public void init() {
		// Initialize motorSpeed, targetCounts, and goToCounts.
		motorSpeed = 0;
		targetCounts = 0;
		goToCounts = false;
	}

	@Override
	public void periodicUpdate() {
		// Encoder controlled movement if we are less than the target counts.
		if(goToCounts && (hardwareLayer.getEncoderCounts() < targetCounts - 5 || hardwareLayer.getEncoderCounts() > targetCounts + 5)){
			stop();
		}else if(goToCounts && hardwareLayer.getEncoderCounts() < targetCounts){
			hardwareLayer.driveGearManiupulator(motorSpeed * Constants.gearManipulatorMoveSpeed);
		}
		// Encoder controlled movement if we are greater than the target counts.
		else if(goToCounts && hardwareLayer.getEncoderCounts() > targetCounts){
			hardwareLayer.driveGearManiupulator(-motorSpeed * Constants.gearManipulatorMoveSpeed);
		}
		
		// In any other case.
		hardwareLayer.driveGearManiupulator(motorSpeed * Constants.gearManipulatorMoveSign);
		
	}

	@Override
	public void setChild(GearManipulatorHardwareBase c) {
		// Set hardware layer to commandLayer.
		hardwareLayer = c;
	}

	@Override
	public void toggleGates() {
		// Toggle pistons.
		hardwareLayer.toggleLowerPistons();
		hardwareLayer.toggleUpperPistons();
	}
	
	

	@Override
	public void goLeft() {
		// Set motorSpeed to positive gearManipulatorMoveSpeed.
		motorSpeed = Constants.gearManipulatorMoveSpeed;
	}

	@Override
	public void goRight() {
		// Set motor speed to negative gearManipulatorMoveSpeed;
		motorSpeed = -Constants.gearManipulatorMoveSpeed;
	}

	@Override
	public void goLeft(double speed) {
		// Set the motor speed to the positive parameter. 
		motorSpeed = speed;
	}

	@Override
	public void goRight(double speed) {
		// Set the motor speed to the negative parameter.
		motorSpeed = -speed;
	}

	@Override
	public void gotoCounts(int counts) {
		// Set the speed to the constant speed.
		motorSpeed = Constants.gearManipulatorMoveSpeed;
		
		// Set our counts variable
		targetCounts = counts;
		
		// Set goToCounts to true.
		goToCounts = true;
	}

	@Override
	public void gotoCounts(double speed, int counts) {
		// Set the speed to a double speed variable.
		motorSpeed = speed;
		
		// Set our counts variable
		targetCounts = counts;
		
		// Set goToCounts to true.
		goToCounts = true;
	}

	@Override
	public void centerManipulator() {
		// Set goToCounts parameters to 0.
		gotoCounts(0);
	}

	@Override
	public int getCounts() {
		// Return getEncoderCounts.
		return hardwareLayer.getEncoderCounts();
	}
	
	@Override
	public String getName() {
		return "FIXED_LAYER_DNU";
	}

	@Override
	public void userControlOverride() {
		// Set goToCounts to false.
		goToCounts = false;
	}
	
	@Override
	public void stop(){
		// Stop the motors.
		motorSpeed = 0;
	}
	
}