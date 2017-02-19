package org.usfirst.frc.team5332.robot.toaster;

import org.usfirst.frc.team5332.robot.toaster.base.ToasterHardwareBase;
import org.usfirst.frc.team5332.robot.toaster.base.ToasterSystemBase;
import org.usfirst.frc.team5332.util.constants.TuningConstants;

public class ToasterSystem extends ToasterSystemBase{

	private ToasterHardwareBase hardwareLayer;
	
	private double shooterSpeed;
	private double feederSpeed;
	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "FIXED_LAYER_DNU";
	}

	@Override
	public void periodicUpdate() {
		hardwareLayer.setFeedSpeed(feederSpeed);
		hardwareLayer.setShooterSpeed(shooterSpeed);
		
	}

	@Override
	public void setChild(ToasterHardwareBase c) {
		hardwareLayer = c;
		
	}

	@Override
	public void shoot() {
		shooterSpeed = TuningConstants.shooterShooterSpeed;
		
	}

	@Override
	public void feed() {
		feederSpeed = TuningConstants.shooterFeederSpeed;
		
	}

	@Override
	public void shootStop() {
		shooterSpeed = 0.0;
		
	}

	@Override
	public void feedStop() {
		feederSpeed = 0.0;
		
	}
	
}