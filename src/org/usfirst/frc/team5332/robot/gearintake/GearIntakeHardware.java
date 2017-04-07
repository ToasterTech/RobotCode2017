package org.usfirst.frc.team5332.robot.gearintake;

import org.usfirst.frc.team5332.robot.gearintake.base.GearIntakeHardwareBase;
import org.usfirst.frc.team5332.util.constants.HardwareConstants;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TalonSRX;

public class GearIntakeHardware extends GearIntakeHardwareBase {
	
	private DoubleSolenoid solenoidGate;
	private TalonSRX intakeMotor;
	private double speed;
	
	public GearIntakeHardware() {
		solenoidGate = new DoubleSolenoid(HardwareConstants.solenoidGatePort1,HardwareConstants.solenoidGatePort2);
		intakeMotor = new TalonSRX(HardwareConstants.gearIntakeMotor);
	}
	
	@Override
	public void extendPneumatics() {
		solenoidGate.set(DoubleSolenoid.Value.kForward);
	}

	@Override
	public void retractPneumatics() {
		solenoidGate.set(DoubleSolenoid.Value.kReverse);
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
