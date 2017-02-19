package org.usfirst.frc.team5332.robot.westtoastdrive.path;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.usfirst.frc.team5332.util.constants.HardwareConstants;

public class PathManager{
	
	/**
	 * Load a Path from a file.
	 * 
	 * @param name The name of the Path object to load.
	 * @return The Path object loaded.
	 */
	public static Path getPathFromFile(String name){
		return new Path(new File(HardwareConstants.pathDirectory+"name"));
	}
	
	/**
	 * Delete a Path by the given name.
	 * 
	 * @param name The name of the Path to delete.
	 */
	public static void deletePath(String name){
		new File(HardwareConstants.pathDirectory+name).delete();
	}
	
	/**
	 * Write a Path object to the RIO.
	 * 
	 * @param path The Path object to write.
	 */
	public static void serializePath(Path path){
		File file = new File(HardwareConstants.pathDirectory+path.getName());
		try{
			// Create a print stream that writes to the given file.
			PrintStream fileWriter = new PrintStream(file);
			
			double[] currentCoord;
			while((currentCoord = path.getNextCoordinate()) != null){
				fileWriter.println(currentCoord[0]+","+currentCoord[1]);
			}
			
			// Close the writer.
			fileWriter.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Enumerate all paths currently stored on the RIO.
	 * @return
	 */
	public static String[] listPaths(){
		return new File(HardwareConstants.pathDirectory).list();
	}
	
}