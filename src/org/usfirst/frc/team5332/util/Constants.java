package org.usfirst.frc.team5332.util;

import org.opencv.core.Scalar;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Constants{
	
	// Joystick USB ports
	public static int driverTurnJoystickPort = 0;
	public static int driverThrottleJoystickPort = 1;
	public static int operatorJoystickPort = 2;
	
	// Joystick Action IDs
	public static final int shootButtonID = 0;
	public static final int feedButtonID = 1;
	
	public static final int gmPistonToggleButton = 2;
	
	// File directories
	public static final String pathDirectory = "D:\\paths\\";
	
	// Turning variables
	public static final double turningJoystickFixedSpeed = 0.5;
	public static final double turningScalar = 1;
	
	public static final double gearManipulatorMoveSpeed = 0.5;
	public static final int gearManipulatorMoveSign = 1;
	
	public static final double shooterShooterSpeed = 1;
	public static final double shooterFeederSpeed = 1;
	
	// Motor ports
	public static final int driveLeftPort1 = 0;
	public static final int driveLeftPort2 = 1;
	public static final int driveRightPort1 = 14;
	public static final int driveRightPort2 = 15;
	
	public static final int gmShiftMotorPort = 4;
	
	public static final int intakeMotorPort = 5;
	
	public static final int shooterShooterMotorPort = 6;
	public static final int shooterFeederMotorPort = 7;
	
	// Encoder ports
	public static final int driveEncoderLeftPort1 = 0;
	public static final int driveEncoderLeftPort2 = 1;
	public static final int driveEncoderRightPort1 = 2;
	public static final int driveEncoderRightPort2 = 3;
	
	public static final int gmShiftEncoderPort1 = 4;
	public static final int gmShiftEncoderPort2 = 5;
	
	// Solenoid ports
	public static final int gmUpperSolenoidPort1 = 0;
	public static final int gmUpperSolenoidPort2 = 1;
	
	// Solenoid values
	public static final Value gmUpperSolenoidOpenValue = Value.kForward;
	public static final Value gmUpperSolenoidClosedValue = Value.kReverse;
	public static final Value gmLowerSolenoidOpenValue = Value.kForward;
	public static final Value gmLowerSolenoidClosedValue = Value.kReverse;
	
	// Joystick axes
	public static final int throttleJoystickAxisIndex = 0;
	public static final int turningJoystickRangeAxisIndex = 1;
	public static final int turningJoystickFixedAxisIndex = 2;
	public static final double turningJoystickFixedThresh = 0.5;
	
	public static final int operatorJoystickRightBumperAxisIndex = 3;
	public static final int operatorJoystickLeftBumperAxisIndex = 4;
	public static final double operatorJoystickBumperThresh = 0.5;
	
	// Camera address
	public static final int address = 0;

	// Vision Tracking
	public static final Scalar colorTrackingMin = new Scalar(40, 100, 100); // Values subject to change
	public static final Scalar colorTrackingMax = new Scalar (70, 100, 50); // Values subject to change 

	// Path Plotting
	public static final double timeInterval = 1;
}