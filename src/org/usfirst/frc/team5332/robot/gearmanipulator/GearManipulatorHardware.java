package org.usfirst.frc.team5332.robot.gearmanipulator;

import org.usfirst.frc.team5332.robot.gearmanipulator.base.GearManipulatorHardwareBase;
import org.usfirst.frc.team5332.robot.util.Constants;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.TalonSRX;

public class GearManipulatorHardware extends GearManipulatorHardwareBase{
	
	private TalonSRX shiftMotor;
	private Encoder shiftEncoder;
	private DoubleSolenoid upperSolenoid;
	private DoubleSolenoid lowerSolenoid;
	
	private double speed;
	private boolean upperPistons;
	private boolean lowerPistons;
	
	public GearManipulatorHardware(){
		shiftMotor = new TalonSRX(Constants.gmShiftMotorPort);
		shiftEncoder = new Encoder(Constants.gmShiftEncoderPort1, Constants.gmShiftEncoderPort2);
		upperSolenoid = new DoubleSolenoid(Constants.gmUpperSolenoidPort1, Constants.gmUpperSolenoidPort2);
		lowerSolenoid = new DoubleSolenoid(Constants.gmLowerSolenoidPort1, Constants.gmLowerSolenoidPort2);
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
		shiftMotor.set(speed);
		if(upperPistons){
			upperSolenoid.set(Constants.gmUpperSolenoidOpenValue);
		}else{
			upperSolenoid.set(Constants.gmUpperSolenoidClosedValue);
		}
		if(lowerPistons){
			lowerSolenoid.set(Constants.gmLowerSolenoidOpenValue);
		}else{
			lowerSolenoid.set(Constants.gmLowerSolenoidClosedValue);
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
		return shiftEncoder.get();
	}

	@Override
	public void resetEncoderCounts() {
		shiftEncoder.reset();
	}
	
}