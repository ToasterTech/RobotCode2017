package org.usfirst.frc.team5332.robot.util;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Constants{
	
	///==========These values will likely be changing or need tuning from time to time==========
	
	/*
	 *  These are static so that they can have a known access space but I would like it so that, 
	 *  depending on the order they plug the joysticks in, it says "Is this the Turn, Throttle 
	 *  or Operator Joystick?" and changes the port here accordingly
	 */
	public static int driverTurnJoystickPort = 0;
	public static int driverThrottleJoystickPort = 1;
	public static int operatorJoystickPort = 2;
	
	/*
	 * These values will, in all likelyhood, need to be constantly adjusted to fit the driver's 
	 * varying needs
	 */
	public static final double turningJoystickFixedThresh = 0.5;
	public static final double turningJoystickFixedSpeed = 0.5;
	public static final double turningScalar = 1;
	
	///==========These values will likely be changed once (e.g motor ports)==========
	
	// Motor ports on the drive train
	public static final int driveLeftPort1 = 0;
	public static final int driveLeftPort2 = 1;
	public static final int driveRightPort1 = 2;
	public static final int driveRightPort2 = 3;
	
	// Encoder ports on the drive train
	public static final int driveEncoderLeftPort1 = 0;
	public static final int driveEncoderLeftPort2 = 1;
	public static final int driveEncoderRightPort1 = 2;
	public static final int driveEncoderRightPort2 = 3;
	
	// Gear Manipulator motor & encoder ports
	public static final int gmShiftMotorPort = 4;
	public static final int gmShiftEncoderPort1 = 4;
	public static final int gmShiftEncoderPort2 = 5;
	
	// Gear Manipulator Solenoid ports (note: currently we have four solenoids, we may only need 2)
	public static final int gmUpperSolenoidPort1 = 0;
	public static final int gmUpperSolenoidPort2 = 1;
	public static final int gmLowerSolenoidPort1 = 2;
	public static final int gmLowerSolenoidPort2 = 3;
	
	// Gear Manipulator open and closed values (these will need to be checked once depending on how they wire it)
	public static final Value gmUpperSolenoidOpenValue = Value.kForward;
	public static final Value gmUpperSolenoidClosedValue = Value.kReverse;
	public static final Value gmLowerSolenoidOpenValue = Value.kForward;
	public static final Value gmLowerSolenoidClosedValue = Value.kReverse;
	
	// Various axis indicies for the joysticks
	public static final int throttleJoystickAxisIndex = 0;
	public static final int turningJoystickRangeAxisIndex = 1;
	public static final int turningJoystickFixedAxisIndex = 2;
	
}