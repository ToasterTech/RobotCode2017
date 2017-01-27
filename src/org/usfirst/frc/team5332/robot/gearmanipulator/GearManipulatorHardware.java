package org.usfirst.frc.team5332.robot.gearmanipulator;

import org.usfirst.frc.team5332.robot.gearmanipulator.base.GearManipulatorHardwareBase;
import org.usfirst.frc.team5332.util.Constants;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.TalonSRX;

public class GearManipulatorHardware extends GearManipulatorHardwareBase{
	
	private TalonSRX motor;
	private Encoder encoder;
	private DoubleSolenoid solenoid;
	
	private double speed;
	private boolean upperPistons;
	private boolean lowerPistons;
	
	public GearManipulatorHardware(){
		motor = new TalonSRX(Constants.gmShiftMotorPort);
		encoder = new Encoder(Constants.gmShiftEncoderPort1, Constants.gmShiftEncoderPort2);
		solenoid = new DoubleSolenoid(Constants.gmUpperSolenoidPort1, Constants.gmUpperSolenoidPort2);
		speed = 0;
		upperPistons = false;
		lowerPistons = false;
	}
	
	@Override
	public void init() {
		speed = 0;
		upperPistons = false;
		lowerPistons = false;
	}

	@Override
	public void periodicUpdate(){
		motor.set(speed);
		if(upperPistons){
			solenoid.set(Constants.gmUpperSolenoidOpenValue);
		}else{
			solenoid.set(Constants.gmUpperSolenoidClosedValue);
		}
		if(lowerPistons){
			solenoid.set(Constants.gmLowerSolenoidOpenValue);
		}else{
			solenoid.set(Constants.gmLowerSolenoidClosedValue);
		}
	}

	@Override
	public void driveGearManiupulator(double speed) {
		this.speed = speed;
	}

	@Override
	public void toggleUpperPistons() {
		upperPistons = !upperPistons;
	}

	@Override
	public void toggleLowerPistons() {
		lowerPistons = !lowerPistons;
	}

	@Override
	public int getEncoderCounts() {
		return encoder.get();
	}

	@Override
	public void resetEncoderCounts() {
		encoder.reset();
	}
	
	@Override
	public String getName() {
		return "FIXED_LAYER_DNU";
	}
	
}