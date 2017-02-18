package org.usfirst.frc.team5332.util;

import org.opencv.core.Scalar;

public class Constants{
	
	// Network table string name
	public static final String netTableName = "";
	
	// Joystick USB ports
	public static int driverTurnJoystickPort = 0;
	public static int driverThrottleJoystickPort = 1;
	public static int operatorJoystickPort = 0;
	
	// Joystick Action IDs
	public static final int shootButtonID = 0;
	public static final int feedButtonID = 1;
	
	// File directories
	public static final String pathDirectory = "D:\\paths\\";
	
	// Turning variables
	public static final double turningJoystickFixedSpeed = 0.5;
	public static final double turningScalar = 1;
	
	public static final double shooterShooterSpeed = 1;
	public static final double shooterFeederSpeed = 1;
	
	// Motor ports
	public static final int driveLeftPort1 = 2;
	public static final int driveLeftPort2 = 3;
	public static final int driveRightPort1 = 0;
	public static final int driveRightPort2 = 1;
	
	public static final int intakeMotorPort = 4;
	
	public static final int shooterShooterMotorPort = 6;
	public static final int shooterFeederMotorPort = 5;
	
	// Encoder ports
	public static final int driveEncoderLeftPort1 = 4;
	public static final int driveEncoderLeftPort2 = 5;
	public static final int driveEncoderRightPort1 = 0;
	public static final int driveEncoderRightPort2 = 1;
	
	
	// Joystick axes	
	public static final int gamePadLeftAxis = 1;
	public static final int gamePadRightAxis = 5;
	public static final int gamePadRightBumperAxisIndex = 3;
	public static final int gamePadLeftBumperAxisIndex = 4;
	public static final double gamePadBumperThresh = 0.5;
	
	// Camera address
	public static final int address = 0;

	// Vision Tracking
	public static final Scalar colorTrackingMin = new Scalar(40, 100, 100); // Values subject to change
	public static final Scalar colorTrackingMax = new Scalar (70, 100, 50); // Values subject to change 
	
	public static final double constantH = -6.0;
	
	
	// Path Plotting
	public static final double timeInterval = 1;

	public static final double followSecondsBetweenChecks = 0.01;
	public static final double lengthBetweenWheels = 24;
	public static final double radiusOfWheels = 3;
	public static final int ticksPerRevolution = 130;
	
	public static final double distConst = .1;
	public static final double angleConst = .2;
	
}