package org.usfirst.frc.team5332.robot.intake;

import org.usfirst.frc.team5332.robot.intake.base.IntakeHardwareBase;
import org.usfirst.frc.team5332.robot.intake.base.IntakeSystemBase;

public class IntakeSystem extends IntakeSystemBase{
	
	// Hardware layer object.
	private IntakeHardwareBase hardwareLayer;
	
	// Intake speed variable.
	private int speed;
	
	public IntakeSystem(){
		// Set intake speed variable to 0.
		speed = 0;
	}

	@Override
	public void init(){
		// Initialize the speed to 0.
		speed = 0;
	}

	@Override
	public void periodicUpdate(){
		// Periodically update the intake speed.
		hardwareLayer.setIntakeSpeed(speed);
	}

	@Override
	public void setChild(IntakeHardwareBase c){
		// Set the hardware layer to the parameter.
		hardwareLayer = c;
	}

	@Override
	public void intake(){
		// Set the intake speed to 1.
		speed = -1;
	}

	@Override
	public void reverse(){
		// Set the intake reverse speed to -1.
		speed = 1;
	}

	@Override
	public void stopIntake(){
		// Set the intake speed to 0.
		speed = 0;
	}
	
	@Override
	public String getName() {
		return "FIXED_LAYER_DNU";
	}
	
}