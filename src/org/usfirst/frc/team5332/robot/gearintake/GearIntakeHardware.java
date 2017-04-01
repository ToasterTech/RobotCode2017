package org.usfirst.frc.team5332.robot.gearintake;

import org.usfirst.frc.team5332.robot.gearintake.base.GearIntakeHardwareBase;
import org.usfirst.frc.team5332.util.constants.HardwareConstants;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TalonSRX;

public class GearIntakeHardware extends GearIntakeHardwareBase {
	
	private Solenoid solenoidGate;
	private TalonSRX intakeMotor;
	private double speed;
	
	public GearIntakeHardware() {
		solenoidGate = new Solenoid(HardwareConstants.solenoidGatePort);
		intakeMotor = new TalonSRX(HardwareConstants.gearIntakeMotor);
	}
	
	@Override
	public void extendPneumatics() {
		solenoidGate.set(true);
	}

	@Override
	public void retractPneumatics() {
		solenoidGate.set(false);
	}

	@Override
	public void setIntakeSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public void init() {
		speed = 0;
		retractPneumatics();
		Compressor c = new Compressor(0);
		c.setClosedLoopControl(true);
	}

	@Override
	public void periodicUpdate() {
		intakeMotor.set(speed);
	}

	@Override
	public String getName() {
		return null;
	}
	
}
