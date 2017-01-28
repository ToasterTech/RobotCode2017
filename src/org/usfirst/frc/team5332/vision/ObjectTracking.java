package org.usfirst.frc.team5332.vision;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
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
			else if (Imgproc.contourArea(contour) > biggestArea2) {
				biggestArea2 = Imgproc.contourArea(contour);
				biggestContour2 = contour;
			}
		}
		if (biggestContour1 != null) {
			MatOfPoint2f biggestContour12f = new MatOfPoint2f();
			MatOfPoint2f biggestContour22f = new MatOfPoint2f();
			double xSum = 0;
			double ySum = 0;
			contours.remove(biggestContour1);
			contours.remove(biggestContour2);
			Imgproc.drawContours(coloredImg, contours, -1, new Scalar(0, 255, 255), -1);
			Imgproc.approxPolyDP(new MatOfPoint2f(biggestContour1.toArray()), biggestContour12f, 3.0, true);
			Imgproc.approxPolyDP(new MatOfPoint2f(biggestContour2.toArray()), biggestContour22f, 3.0, true);
			for (Point center1: biggestContour12f.toArray()) {
				xSum += center1.x;
				ySum += center1.y;
			}
			for (Point center2: biggestContour22f.toArray()) {
				xSum += center2.x;
				ySum += center2.y;
			}
			double xCenter = xSum / ((biggestContour12f.toArray().length) + (biggestContour22f.toArray().length));
			double yCenter = ySum / ((biggestContour12f.toArray().length) + (biggestContour22f.toArray().length));
			Imgproc.circle(coloredImg, new Point(xCenter, yCenter), 5, new Scalar(0, 255, 255));
				
		}
	}
}
