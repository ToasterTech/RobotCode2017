package org.usfirst.frc.team5332.robot.gearintake;

import org.usfirst.frc.team5332.robot.gearintake.base.GearIntakeHardwareBase;
import org.usfirst.frc.team5332.robot.gearintake.base.GearIntakeSystemBase;

public class GearIntakeSystem extends GearIntakeSystemBase {
 
	private GearIntakeHardwareBase hardwareLayer;	
	
	public GearIntakeSystem() {
		
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void periodicUpdate() {
		
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void moveIntakeDown() {
		hardwareLayer.extendPneumatics();
	}
	
	@Override
	public void moveIntakeUp() {
		hardwareLayer.retractPneumatics();
	}

	@Override
	public void runForwards() {
		hardwareLayer.setIntakeSpeed(1);
	}

	@Override
	public void runReverse() {
		hardwareLayer.setIntakeSpeed(-1);
	}

	@Override
	public void runStop() {
		hardwareLayer.setIntakeSpeed(0);
	}

	@Override
	public void setChild(GearIntakeHardwareBase c) {
		hardwareLayer = c;
	}
	
}
