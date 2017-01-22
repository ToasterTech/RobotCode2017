package org.usfirst.frc.team5332.vision;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team5332.util.Constants;

public class ObjectTracking {
	
	public static Mat filterColor(Mat unedited) {
		Mat coloredImg = new Mat();
		Mat hsvImage = new Mat();
		Imgproc.cvtColor(unedited, hsvImage, Imgproc.COLOR_BGR2HSV);
		Core.inRange(unedited, Constants.colorTrackingMin, Constants.colorTrackingMax, coloredImg);
		return coloredImg;
	}
		
	public static void getContour(Mat coloredImg) {
		Mat blurredImage = new Mat();
		Imgproc.blur(coloredImg, blurredImage, new Size(7, 7));
		List<MatOfPoint> contours = new ArrayList<>();
		Mat contourHierarchy = new Mat();
		Imgproc.findContours(coloredImg, contours, contourHierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
		double biggestArea1 = 0;
		MatOfPoint biggestContour1 = null;
		double biggestArea2 = 0;
		MatOfPoint biggestContour2 = null;
		for (MatOfPoint contour: contours) {
			if (Imgproc.contourArea(contour) > biggestArea1) {
				biggestArea1 = Imgproc.contourArea(contour);
				biggestContour1 = contour;
			}
			if (Imgproc.contourArea(contour) > biggestArea2) {
				biggestArea2 = Imgproc.contourArea(contour);
				biggestContour2 = contour;
			}
		}
		if (biggestContour1 != null) {
			contours.remove(biggestContour1);
			contours.remove(biggestContour2);
			Imgproc.drawContours(coloredImg, contours, -1, new Scalar(0, 255, 255), -1);
		}
	}

}
