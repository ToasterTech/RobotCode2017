package org.usfirst.frc.team5332.util.constants;

import org.opencv.core.Scalar;

public class TuningConstants{
	
	// Shooter variables
	public static final double shooterShooterSpeed = 0.75;
	public static final double shooterFeederSpeed = 1.0;
	
	// Driver variables
	public static final double turningScalar = 1;
	
	// Joystick variables
	public static final double gamePadBumperThresh = 0.5;

	// Vision Tracking
	public static final Scalar colorTrackingMin = new Scalar(40, 100, 100); // Values subject to change
	public static final Scalar colorTrackingMax = new Scalar (70, 100, 50); // Values subject to change 
	public static final double constantH = -6.0;
	
	// Path Plotting
	public static final double plottingTimeInterval = 1;
	
	// Path following
	public static final double followSecondsBetweenChecks = 0.01;
	public static final double distConst = .1;
	public static final double angleConst = .2;
	public static final double ultrasonicDistInRobot = 12;

	
}