package org.usfirst.frc.team5332.vision;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ObjectTracking {
	
	private static Mat blurredImage = new Mat();
	private static Mat hsvImage = new Mat();
	
	public static void getContour(Mat frame){
		Imgproc.blur(frame, blurredImage, new Size(7,7));
		Imgproc.cvtColor(blurredImage, hsvImage, Imgproc.COLOR_BGR2HSV);
	}

}
