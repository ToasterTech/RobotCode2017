package org.usfirst.frc.team5332.robot.control;

import java.util.HashMap;



import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Dashboard {
	
	private static Dashboard instance=null;
	private HashMap<String,Double> doubleValMap;
	private NetworkTable datatable;
	
	public void addData(String name, double val) {
		doubleValMap.put(name, val);
	}

	
	public void init() {
		doubleValMap=new HashMap<String,Double>();
		System.out.println("NT Connected: "+datatable.isConnected());
	}

	
	public void run() {
		for(String key:doubleValMap.keySet()){
			datatable.putNumber(key, doubleValMap.get(key));
		}
	}
 
	
	public static Dashboard getDashboard() {
		if(instance==null)
			instance=new Dashboard();
		return instance;
	}

	
	public double getDouble(String name) {
		return datatable.getNumber(name, 0);
	}

	
	public String getString(String name) {
		return datatable.getString(name, "Not Found");
	}
	
	
	public boolean getBoolean(String name) {
		return datatable.getBoolean(name, false);
	}
	
}
