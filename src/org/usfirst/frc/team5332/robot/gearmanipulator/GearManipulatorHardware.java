package org.usfirst.frc.team5332.robot.gearmanipulator;

import org.usfirst.frc.team5332.robot.gearmanipulator.base.GearManipulatorHardwareBase;
import org.usfirst.frc.team5332.robot.util.Constants;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.TalonSRX;

public class GearManipulatorHardware extends GearManipulatorHardwareBase{
	
	private TalonSRX shiftMotor;
	private Encoder shiftEncoder;
	private DoubleSolenoid upperSolenoid1;
	private DoubleSolenoid upperSolenoid2;
	private DoubleSolenoid lowerSolenoid1;
	private DoubleSolenoid lowerSolenoid2;
	
	private double speed;
	private boolean upperPistons;
	private boolean lowerPistons;
	
	public GearManipulatorHardware(){
		shiftMotor = new TalonSRX(Constants.gmShiftMotorPort);
		shiftEncoder = new Encoder(Constants.gmShiftEncoderPort1, Constants.gmShiftEncoderPort2);
		upperSolenoid1 = new DoubleSolenoid(Constants.gmUpperSolenoidPort10, Constants.gmUpperSolenoidPort11);
		upperSolenoid2 = new DoubleSolenoid(Constants.gmUpperSolenoidPort20, Constants.gmUpperSolenoidPort21);
		lowerSolenoid1 = new DoubleSolenoid(Constants.gmLowerSolenoidPort10, Constants.gmLowerSolenoidPort11);
		lowerSolenoid2 = new DoubleSolenoid(Constants.gmLowerSolenoidPort20, Constants.gmLowerSolenoidPort21);
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
			upperSolenoid1.set(Constants.gmUpperSolenoidOpenValue);
			upperSolenoid2.set(Constants.gmUpperSolenoidOpenValue);
		}else{
			upperSolenoid1.set(Constants.gmUpperSolenoidClosedValue);
			upperSolenoid2.set(Constants.gmUpperSolenoidClosedValue);
		}
		if(lowerPistons){
			lowerSolenoid1.set(Constants.gmLowerSolenoidOpenValue);
			lowerSolenoid2.set(Constants.gmLowerSolenoidOpenValue);
		}else{
			lowerSolenoid1.set(Constants.gmLowerSolenoidClosedValue);
			lowerSolenoid2.set(Constants.gmLowerSolenoidClosedValue);
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