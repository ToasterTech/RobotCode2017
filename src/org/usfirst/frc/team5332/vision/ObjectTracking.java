package org.usfirst.frc.team5332.vision;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team5332.util.Constants;

public class ObjectTracking {
	
	private static Mat blurredImage = new Mat();
	private static Mat hsvImage = new Mat();
	private static Mat coloredImg = new Mat();
	private static List<MatOfPoint> contours = new ArrayList<>();
	private static Mat contouredImg = new Mat();
	
	public static Mat filterColor(Mat unedited) {
		Imgproc.cvtColor(blurredImage, hsvImage, Imgproc.COLOR_BGR2HSV);
		Core.inRange(unedited, Constants.colorTrackingMin, Constants.colorTrackingMax, coloredImg);
		return coloredImg;
	}
		
	public static void getContour(Mat coloredImg) {
		Imgproc.blur(coloredImg, blurredImage, new Size(7, 7));
		Imgproc.findContours(coloredImg, contours, contouredImg, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
		MatOfPoint largestBlob = contours.get(0);
		MatOfPoint2f finalImg = new MatOfPoint2f();
		
		for (MatOfPoint contour: contours) {
			if (contour.size().area() >= largestBlob.size().area()) {
				largestBlob = contour;
			}
		}
		
		Imgproc.drawContours(contouredImg, contours, -1, new Scalar(250, 0, 0));
		Imgproc.approxPolyDP(new MatOfPoint2f(contouredImg), finalImg, 3.0, true);
	}

}
