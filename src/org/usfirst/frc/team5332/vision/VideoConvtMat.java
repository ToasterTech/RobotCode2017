package org.usfirst.frc.team5332.vision;
import org.opencv.videoio.VideoCapture;
import org.opencv.core.Mat;

public class VideoConvtMat {
	
	private VideoCapture vidCapture;
	
	public VideoConvtMat() {
		vidCapture = new VideoCapture();
	}
	
	public Mat read() {
		Mat magic = new Mat();
		vidCapture.read(magic);
		return magic;
	}

}
