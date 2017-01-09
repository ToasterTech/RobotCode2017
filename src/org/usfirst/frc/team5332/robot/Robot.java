package org.usfirst.frc.team5332.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The Linux JVM on the robot is configured to run this class upon startup. Do not refactor it.
 */
public class Robot extends IterativeRobot{
	
	/*
	 * Robot-wide initialization code goes here.
	 * NOTE: This is called when the robot first boots up and ONLY when the robot first boots up.
	 * If the DS is not attached but DS communication tasks are performed you will break your code.
	 * 
	 * NOTE: DO NOT PUT LOOPS IN HERE.
	 */
	@Override
	public void robotInit(){
		
	}
	
	/*
	 * This method is called once the driver station is attached.
	 */
	public void dsInitTasks(){
		
	}
	
	// Runs the dsInitTasks once the ds is attached. No other usage for this method.
	@Override
	public void robotPeriodic(){
		if(m_ds.isDSAttached()){
			dsInitTasks();
		}
	}
	
	/*
	 * Called at the beginning of the autonomous period. Autonomous initialization code should be run here.
	 */
	@Override
	public void autonomousInit(){
		
	}
	
	/*
	 * Called during the autonomous period.
	 */
	@Override
	public void autonomousPeriodic(){
		
	}
	
	/*
	 * Called at the beginning of the teleop period. teleop initialization code should be run here.
	 */
	@Override
	public void teleopInit(){
		
	}
	
	/*
	 * Called during the teleop period.
	 */
	@Override
	public void teleopPeriodic(){
		
	}
	
	/*
	 * Called when the robot is first disabled.
	 */
	@Override
	public void disabledInit(){
		
	}
	
	/*
	 * Called periodically when the robot is disabled.
	 */
	@Override
	public void disabledPeriodic(){
		
	}
	
	/*
	 * Don't worry about this.
	 */
	@Override
	public void testPeriodic(){
		
	}
}

