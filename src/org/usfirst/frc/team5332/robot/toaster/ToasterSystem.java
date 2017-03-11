package org.usfirst.frc.team5332.robot.toaster;

import org.usfirst.frc.team5332.robot.toaster.base.ToasterHardwareBase;
import org.usfirst.frc.team5332.robot.toaster.base.ToasterSystemBase;
import org.usfirst.frc.team5332.util.constants.TuningConstants;

public class ToasterSystem extends ToasterSystemBase{

	private ToasterHardwareBase hardwareLayer;
	
	private double shooterSpeed;
	private double feederSpeed;
	//private double oldRp]\[]]\[];
	//PIDController shootControl(.1, 0, 0, )
	private double P;
	private double I;
	private double D;
	private double Pc = .1;
	private double Ic = 0;
	private double Dc = 0;
	private double targetSpeed=1000;
	private double error;
	private double rpm = 800;
	private double oldvalue;
	private double old_error;
	private double dt;
	private double lastTime = System.currentTimeMillis();
	private double vals = 0;

	private int count = 0;
	
	
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
//		
//		while(count<20) {
//			vals = hardwareLayer.readEncoder() - oldvalue;
//			if(count == 19) {
//				count = 0;
//				vals = 0;
//				rpm = Math.abs(((vals)/20.0));
//			}
//		}
//		
//		
//		//rpm = hardwareLayer.readEncoder() - oldvalue;
//		oldvalue = hardwareLayer.readEncoder();
//		dt = System.currentTimeMillis() - lastTime;
//		lastTime = System.currentTimeMillis();
//		//old_error = targetSpeed - rpm;
//		error = targetSpeed - rpm;
//		I = I + error*dt;
//		D = (error - old_error)/dt;
//		
//		shooterSpeed = (TuningConstants.shooterShooterSpeed)*(Pc*error + I*Ic + D*Dc);
//		old_error = targetSpeed - rpm;
		shooterSpeed = (TuningConstants.shooterShooterSpeed);
	}

	@Override
	public void feed() {
		feederSpeed = TuningConstants.shooterFeederSpeed;
		
	}
	
	public void unfeed() {
		feederSpeed = -TuningConstants.shooterFeederSpeed;
		
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