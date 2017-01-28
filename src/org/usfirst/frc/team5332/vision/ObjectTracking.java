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
		
	public static ArrayList<MatOfPoint> getContour(Mat coloredImg) {
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
		ArrayList<MatOfPoint> tape = new ArrayList<>();
		tape.add(biggestContour1);
		tape.add(biggestContour2);
		return tape;
	}
	
	public static void locateTarget(Mat uneditedImg, ArrayList<MatOfPoint> tape) {
		if (uneditedImg != null && tape.get(0) != null && tape.get(1) != null) {
			MatOfPoint2f tape1 = new MatOfPoint2f();
			MatOfPoint2f tape2 = new MatOfPoint2f();
			double xSum = 0;
			double ySum = 0;
			Imgproc.drawContours(uneditedImg, tape, -1, new Scalar(60, 255, 255), 5);
			Imgproc.approxPolyDP(new MatOfPoint2f(tape.get(0).toArray()), tape1, 3.0, true);
			Imgproc.approxPolyDP(new MatOfPoint2f(tape.get(1).toArray()), tape2, 3.0, true);
			for (Point center1: tape1.toArray()) {
				xSum += center1.x;
				ySum += center1.y;
				
			}
			for (Point center2: tape2.toArray()) {
				xSum += center2.x;
				ySum += center2.y;
			}
			double xCenter = xSum / ((tape1.toArray().length) + (tape2.toArray().length));
			double yCenter = ySum / ((tape1.toArray().length) + (tape2.toArray().length));
			Imgproc.circle(uneditedImg, new Point(xCenter, yCenter), 5, new Scalar(0, 255, 255));	
		}
	}
}
