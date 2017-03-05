package org.usfirst.frc.team5332.robot.intake;

import org.usfirst.frc.team5332.robot.intake.base.IntakeHardwareBase;
import org.usfirst.frc.team5332.util.constants.HardwareConstants;

import edu.wpi.first.wpilibj.TalonSRX;

public class IntakeHardware extends IntakeHardwareBase{
	
	// Motor object.
	private TalonSRX motor;
	
	// Speed variable.
	private double speed;
	
	/**
	 * Constructor, sets the speed of the motors to 0 by default.
	 */
	public IntakeHardware(){
		//Sets TalonSRX to a new intake motor port constant.
		motor = new TalonSRX(HardwareConstants.intakeMotorPort);
		//speed set to 0 by default
		speed = 0;
	}
	
	@Override
	public void init() {
		speed = 0;
	}

	@Override
	public void periodicUpdate() {
		motor.set(speed);
	}

	@Override
	public void setIntakeSpeed(double speed) {
		this.speed = speed;
	}
	
	@Override
	public String getName() {
		return "FIXED_LAYER_DNU";
	}
	
}