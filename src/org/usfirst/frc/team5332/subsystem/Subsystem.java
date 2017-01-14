package org.usfirst.frc.team5332.subsystem;

public class Subsystem<H extends BaseLayer, S extends Layer<H>, C extends Layer<S>>{
	
	private H hardwareLayer;
	private S systemLayer;
	private C commandLayer;
	
	public Subsystem(H hardwareLayer, S systemLayer, C commandLayer){
		this.hardwareLayer = hardwareLayer;
		this.systemLayer = systemLayer;
		this.commandLayer = commandLayer;
		this.systemLayer.setChild(hardwareLayer);
		this.commandLayer.setChild(systemLayer);
	}
	
	public void setCommandLayer(C commandLayer){
		this.commandLayer = commandLayer;
		this.commandLayer.setChild(systemLayer);
		this.commandLayer.init();
	}
	
	public void init(){
		hardwareLayer.init();
		systemLayer.init();
		commandLayer.init();
	}
	
	public void periodicUpdate(){
		hardwareLayer.periodicUpdate();
		systemLayer.periodicUpdate();
		commandLayer.periodicUpdate();
	}
	
}