package org.usfirst.frc.team5332.subsystem;

/**
 * The Subsystem class fully wraps a subsystem of robot components including the hardware control class, the system abstraction class, and
 * the command groupings for that subsystem. The Subsystem is a layered architecture, structured in the following manner:
 * <li>
 * <b>Hardware:</b> The hardware layer must implement the BaseLayer interface. The hardware layer provides simple control groupings for
 * receiving information from and sending information to the hardware of the robot.</li>
 * <li><b>System:</b> The system layer must implement the Layer interface and have the Child parameter of Layer set to the hardware layer.
 * The system layer provides the higher levels of abstraction for the hardware and retains fixed 'commands' that the command layers
 * can call later.</li>
 * <li><b>Command:</b></li> The command layer must imlement the Layer interface and have the Child parameter of Layer set to the system
 * layer. The command layer provides the actual control algorithms and groups that utilize system layer commands to control the robot.
 * 
 * @author J-Dierberger
 *
 * @param <H> The hardware component class of the Subsystem. Must implement BaseLayer.
 * @param <S> The system component class of the Subsystem. Must implement Layer, and Layer must have a Child parameter of type {@literal<H>}.
 * @param <C> The command component class of the Subsystem. Must implement Layer, and Layer must have a Child parameter of type {@literal<S>}.
 */
public class Subsystem<H extends BaseLayer, S extends Layer<H>, C extends Layer<S>>{
	
	// The three variables that will store the subsystem's hardware, system and command components.
	private H hardwareLayer;
	private S systemLayer;
	private C commandLayer;
	
	/**
	 * Constructor. Create a Subsystem with the given component objects.
	 * <br><br>
	 * Note that the command layer may later be swapped, but the system and hardware layers must be provided at initialization.
	 * 
	 * @param hardwareLayer The hardware layer object of this Subsystem.
	 * @param systemLayer The system layer object of this Subsystem.
	 * @param commandLayer The command layer object of this Subsystem.
	 */
	public Subsystem(H hardwareLayer, S systemLayer, C commandLayer){
		// Assign variables.
		this.hardwareLayer = hardwareLayer;
		this.systemLayer = systemLayer;
		this.commandLayer = commandLayer;
		
		// Set the children where needed,
		this.systemLayer.setChild(hardwareLayer);
		this.commandLayer.setChild(systemLayer);
	}
	
	/**
	 * Change the current running command layer. Will auto-initialize the command layer.
	 * 
	 * @param commandLayer The new command layer to run.
	 */
	public void setCommandLayer(C commandLayer){
		// Set the command layer.
		this.commandLayer = commandLayer;
		
		// Set the child.
		this.commandLayer.setChild(systemLayer);
		
		// Initialize the command layer.
		this.commandLayer.init();
	}
	
	/**
	 * Initialize the Subsystem layers.
	 */
	public void init(){
		// Initialize each layer.
		hardwareLayer.init();
		systemLayer.init();
		commandLayer.init();
	}
	
	/**
	 * Call the periodicUpdate method in each layer.
	 */
	public void periodicUpdate(){
		// Run each layer's periodicUpdate method.
		hardwareLayer.periodicUpdate();
		systemLayer.periodicUpdate();
		commandLayer.periodicUpdate();
	}
	
}