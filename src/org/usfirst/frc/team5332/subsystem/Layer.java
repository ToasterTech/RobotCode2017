package org.usfirst.frc.team5332.subsystem;

public interface Layer<Child extends BaseLayer> extends BaseLayer{
	
	public void setChild(Child c);
	
}