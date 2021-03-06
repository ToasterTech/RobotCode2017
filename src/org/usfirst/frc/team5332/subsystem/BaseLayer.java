package org.usfirst.frc.team5332.subsystem;

/**
 * The BaseLayer interface provides the basic functionality that all layers of a Subsystem will inherit.
 * 
 * @author J-Dierberger
 */
public interface BaseLayer{
	
	/**
	 * Initialize the layer.
	 */
	public void init();
	
	/**
	 * Called during the periodic loops of the Robot.
	 */
	public void periodicUpdate();
	
	/**
	 * Get the name of the Layer. Generally used for autonomous command layers. 
	 * 
	 * @return The name of the Layer.
	 */
	public String getName();
	
}