package org.usfirst.frc.team5332.vision;
import org.opencv.videoio.VideoCapture;
import org.usfirst.frc.team5332.util.constants.HardwareConstants;
import org.opencv.core.Mat;

public class VideoConvtMat {
	
	private VideoCapture vidCapture;
	private int address;
	
	public VideoConvtMat() {
		address = HardwareConstants.address;
		vidCapture = new VideoCapture(address);
	}
	
	public VideoConvtMat(int address) {
		vidCapture = new VideoCapture(address);
	}
	
	public int getAddress() {
		return address;
	}
	
	public void setAddress(int address) {
		this.address = address;
	}
	
	public Mat read() {
		Mat magic = new Mat();
		vidCapture.read(magic);
		return magic;
	}
	
}