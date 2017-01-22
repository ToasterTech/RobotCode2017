package org.usfirst.frc.team5332.robot.westtoastdrive.path;

import java.io.*;
import java.util.ArrayList;

import org.usfirst.frc.team5332.util.Constants;

/**
 * 
 * Path object.
 * 
 * @author J-Dierberger
 *
 */
public class Path{
	
	// Array of coordinates.
	private ArrayList<Double> x;
	private ArrayList<Double> y;
	
	// Name of the path.
	private String name;
	
	// Current index we're reading at.
	private int currentReadIndex;
	
	// The mark to reset to.
	private int mark;
	
	/**
	 * Create a path with the given name.
	 * 
	 * @param name The name of the path.
	 */
	public Path(String name){
		// Create the array lists.
		this.x = new ArrayList<Double>();
		this.y = new ArrayList<Double>();
		
		// Set the name.
		this.name = name;
		
		// Set the current read index and mark to 0.
		currentReadIndex = 0;
		mark = 0;
	}
	
	/**
	 * Create a path with the given array of doubles as coordinates.
	 * 
	 * @param x The x coordinate array.
	 * @param y The y coordinate array.
	 * @param name The name of the path
	 */
	public Path(double[] x, double[] y, String name){
		// Create the array lists.
		this.x = new ArrayList<Double>();
		this.y = new ArrayList<Double>();
		
		// Populate the array lists.
		for(double i: x){
			this.x.add(i);
		}
		
		// Populate the array lists.
		for(double j: y){
			this.y.add(j);
		}
		
		// Set the name.
		this.name = name;
		
		// Set the current read index and mark to 0.
		currentReadIndex = 0;
		mark = 0;
	}
	
	/**
	 * Create a path that loads from a file.
	 * 
	 * @param path The file name. The directory is tacked on automatically.
	 */
	public Path(File path){
		try{
			// Create the BufferedReader.
			BufferedReader br = new BufferedReader(new FileReader(Constants.pathDirectory+path));
			
			// Create the line being read.
			String line;
			
			// Read each line as long as there is one.
			while((line = br.readLine()) != null){
				// Get the coordinates from the line.
				String[] coords = line.split(",");
				
				// Get the actual numbers from the coordinate pair and add them to the coordinate arrays.
				x.add(Double.parseDouble(coords[0]));
				y.add(Double.parseDouble(coords[1]));
			}
			
			// Close the reader.
			br.close();
		}catch(IOException e){
			System.err.println("Failed to load file "+path);
			e.printStackTrace();
		}
		
		// Set the name.
		name = path.getName();
		
		// Set the current read index and mark to 0.
		currentReadIndex = 0;
		mark = 0;
	}
	
	/**
	 * Add a coordinate to this path object.
	 * 
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 */
	public void addCoordinate(double x, double y){
		// Add the coordinates to the arrays.
		this.x.add(x);
		this.y.add(y);
	}
	
	/**
	 * Get the next coordinate in the path.
	 * 
	 * @return The next coordinate in the path.
	 */
	public double[] getNextCoordinate(){
		// Create the pair of doubles to return.
		double[] pair = new double[2];
		
		// Set the first of the pair to the x coordinate stored in the array.
		pair[0] = x.get(currentReadIndex);
		
		// Ditto for the second of the pair and the y coordinate.
		pair[1] = y.get(currentReadIndex);
		
		// Increase the current index we're at in our reading.
		currentReadIndex++;
		
		// Return the pair we got.
		return pair;
	}
	
	/**
	 * Start reading the path from the mark.
	 */
	public void reset(){
		// Set the current read index to our mark.
		currentReadIndex = mark;
	}
	
	/**
	 * Set the read index to parameter.
	 */
	public void goTo(int index){
		// Set the current read index to the given index.
		currentReadIndex = index;
	}
	
	/**
	 * Mark the position to reset to to the index given in the parameter.
	 * 
	 * @param index The index to start reading from again.
	 */
	public void mark(int index){
		// Set the mark to the parameter.
		mark = index;
	}
	
	/**
	 * Serialize the path object to a file.
	 */
	public void serialize(){
		// Set the file we're writing to to the path directory plus the name
		File file = new File(Constants.pathDirectory+name);
		try{
			// Create a print stream that writes to the given file.
			PrintStream fileWriter = new PrintStream(file);
			
			// For the current index of what we have stored in the coordinate arrays...
			for(int i = 0; i < x.size(); i++){
				// ...print it to the file.
				fileWriter.println(x.get(i)+","+y.get(i));
			}
			
			// Close the writer.
			fileWriter.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
}