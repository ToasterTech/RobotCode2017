package org.usfirst.frc.team5332.robot.toaster;

import org.usfirst.frc.team5332.robot.toaster.base.ToasterHardwareBase;
import org.usfirst.frc.team5332.util.constants.HardwareConstants;

import edu.wpi.first.wpilibj.TalonSRX;

public class ToasterHardware extends ToasterHardwareBase{
	
	private TalonSRX shooterMotor;
	private TalonSRX feederMotor;
	
	private double shooterSpeed = 0;
	private double feederSpeed = 0;
	
	public ToasterHardware(){
		shooterMotor = new TalonSRX(HardwareConstants.shooterShooterMotorPort);
		feederMotor = new TalonSRX(HardwareConstants.shooterFeederMotorPort);
		shooterSpeed = 0;
		feederSpeed = 0;
	}
	
	@Override
	public void init(){
		shooterSpeed = 0;
		feederSpeed = 0;
	}

	@Override
	public void periodicUpdate(){
		shooterMotor.set(shooterSpeed);
		feederMotor.set(feederSpeed);
	}

	@Override
	public String getName(){
		return "FIXED_LAYER_DNU";
	}

	@Override
	public void setShooterSpeed(double speed) {
		shooterSpeed = -speed;
	}	

	@Override
	public void setFeedSpeed(double speed) {
		feederSpeed = speed;
	}
	
}