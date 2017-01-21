package org.usfirst.frc.team5332.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team5332.util.Constants;

public class ObjectTracking {
	
	private static Mat blurredImage = new Mat();
	private static Mat hsvImage = new Mat();
	private static Mat coloredImg = new Mat();
	
	public static Mat filterColor(Mat unedited) {
		Imgproc.cvtColor(blurredImage, hsvImage, Imgproc.COLOR_BGR2HSV);
		Core.inRange(unedited, Constants.colorTrackingMin, Constants.colorTrackingMax, coloredImg);
		return coloredImg;
	}
		
	public static void getContour(Mat frame) {
		Imgproc.blur(frame, blurredImage, new Size(7, 7));
		
	}

}
