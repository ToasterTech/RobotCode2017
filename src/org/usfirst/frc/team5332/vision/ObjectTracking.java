package org.usfirst.frc.team5332.vision;

import java.util.ArrayList;
import java.util.Collections;
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
			double arcLength1 = Imgproc.arcLength(new MatOfPoint2f(tape.get(0).toArray()), true);
			double arcLength2 = Imgproc.arcLength(new MatOfPoint2f(tape.get(1).toArray()), true);
			Imgproc.approxPolyDP(new MatOfPoint2f(tape.get(0).toArray()), tape1, .02 * arcLength1, true);
			Imgproc.approxPolyDP(new MatOfPoint2f(tape.get(1).toArray()), tape2, .02 * arcLength2, true);
			ArrayList<Double> xValues1 = new ArrayList<>(4);
			ArrayList<Double> yValues1 = new ArrayList<>(4);
			ArrayList<Double> xValues2 = new ArrayList<>(4);
			ArrayList<Double> yValues2 = new ArrayList<>(4);
			if (tape1.toArray().length == 4 && tape2.toArray().length == 4) {
			  for (Point center1: tape1.toArray()) {
				  xSum += center1.x;
				  ySum += center1.y;
				  xValues1.add(center1.x);
				  yValues1.add(center1.y);
			  }
			  for (Point center2: tape2.toArray()) {
				  xSum += center2.x;
				  ySum += center2.y;
				  xValues2.add(center2.x);
				  yValues2.add(center2.y);
		      }
			  double xCenter = xSum / ((tape1.toArray().length) + (tape2.toArray().length));
			  double yCenter = ySum / ((tape1.toArray().length) + (tape2.toArray().length));
			  Imgproc.circle(uneditedImg, new Point(xCenter, yCenter), 25, new Scalar(0, 255, 255));	
			  Collections.sort(xValues1);
			  Collections.sort(xValues2);
			  Collections.sort(yValues1);
			  Collections.sort(yValues2);
			  double horizontalAngle = 0;
			  if (xValues1.get(0) < xValues2.get(0)) {
				  horizontalAngle = Math.atan((yValues2.get(3) - yValues1.get(2)) / (xValues2.get(3) - xValues1.get(0)));
				  horizontalAngle = Math.toDegrees(horizontalAngle);
			  }
			  else {
				  horizontalAngle = Math.atan((yValues1.get(3) - yValues2.get(2)) / (xValues1.get(0) - xValues2.get(3)));
				  horizontalAngle = Math.toDegrees(horizontalAngle);
			  }
			}
		}
	}
}
