package org.usfirst.frc.team5332.subsystem;

/**
 * The Layer interface specifies a Layer component of the Subsystem that is not bottom-level. The Layer interface extends
 * the functionality of the BaseLayer but also adds the ability to set a child layer.
 * 
 * @author J-Dierberger
 *
 * @param <Child> The type of class to accept as a child.
 */
public interface Layer<Child extends BaseLayer> extends BaseLayer{
	
	/**
	 * Set the new child of this Layer.
	 * 
	 * @param c The Child object to set to.
	 */
	public void setChild(Child c);
	
}