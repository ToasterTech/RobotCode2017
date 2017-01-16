package org.usfirst.frc.team5332.robot.intake;

import org.usfirst.frc.team5332.robot.intake.base.IntakeHardwareBase;
import org.usfirst.frc.team5332.util.Constants;

import edu.wpi.first.wpilibj.TalonSRX;

public class IntakeHardware extends IntakeHardwareBase{
	
	private TalonSRX motor;
	
	private double speed;
	
	public IntakeHardware(){
		motor = new TalonSRX(Constants.intakeMotorPort);
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