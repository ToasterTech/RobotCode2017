package org.usfirst.frc.team5332.robot.intake;

import org.usfirst.frc.team5332.robot.intake.base.IntakeHardwareBase;
import org.usfirst.frc.team5332.robot.intake.base.IntakeSystemBase;

public class IntakeSystem extends IntakeSystemBase{
	
	private IntakeHardwareBase hardwareLayer;
	
	private int speed;
	
	public IntakeSystem(){
		speed = 0;
	}
	
	@Override
	public void init(){
		speed = 0;
	}

	@Override
	public void periodicUpdate(){
		hardwareLayer.setIntakeSpeed(speed);
	}

	@Override
	public void setChild(IntakeHardwareBase c){
		hardwareLayer = c;
	}

	@Override
	public void intake(){
		speed = 1;
	}

	@Override
	public void reverse(){
		speed = -1;
	}

	@Override
	public void stopIntake(){
		speed = 0;
	}
	
	@Override
	public String getName() {
		return "FIXED_LAYER_DNU";
	}
	
}