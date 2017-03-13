	package org.usfirst.frc.team5332.dashboard;

import java.util.HashMap;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class LabviewDashboard{
	private static LabviewDashboard instance=null;
	private HashMap<String,Double> doubleValMap;
	private NetworkTable datatable;
	
	public void addData(String name, double val) {
		// TODO Auto-generated method stub
		doubleValMap.put(name, val);
	}

	
	public void init() {
		// TODO Auto-generated method stub
		doubleValMap=new HashMap<String,Double>();
		datatable = NetworkTable.getTable("Datatable");
		System.out.println("NT Connected: "+datatable.isConnected());
	}

	
	public void sendAllData() {
		// TODO Auto-generated method stub
		for(String key:doubleValMap.keySet()){
			datatable.putNumber(key, doubleValMap.get(key));
		}
	}
 
	
	public static LabviewDashboard getDashboard() {
		if(instance==null)
			instance=new LabviewDashboard();
		return instance;
	}

	
	public double getDouble(String name) {
		// TODO Auto-generated method stub
		return datatable.getNumber(name, 0);
	}

	
	public String getString(String name) {
		// TODO Auto-generated method stub
		return datatable.getString(name, "Not Found");
	}
	
	
	public boolean getBoolean(String name) {
		// TODO Auto-generated method stub
		return datatable.getBoolean(name, false);
	}
	
}