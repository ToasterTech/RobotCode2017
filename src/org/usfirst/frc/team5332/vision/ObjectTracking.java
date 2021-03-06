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
import org.usfirst.frc.team5332.util.constants.TuningConstants;

public class ObjectTracking {
	
	public static Mat filterColor(Mat unedited) {
		Mat coloredImg = new Mat();
		Mat hsvImage = new Mat();
		Imgproc.cvtColor(unedited, hsvImage, Imgproc.COLOR_BGR2HSV);
		Core.inRange(unedited, TuningConstants.colorTrackingMin, TuningConstants.colorTrackingMax, coloredImg);
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
		}
		contours.remove(biggestContour1);
		for (MatOfPoint contour: contours) {
			if (Imgproc.contourArea(contour) > biggestArea2) {
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
			  // Area Calculations are now prehistoric
			  /*double horizontalAngle = 0;
			  if (xValues1.get(0) < xValues2.get(0)) {
				  horizontalAngle = Math.atan((yValues2.get(3) - yValues1.get(2)) / (xValues2.get(3) - xValues1.get(0)));
				  horizontalAngle = Math.toDegrees(horizontalAngle);
			  }
			  else {
				  horizontalAngle = Math.atan((yValues1.get(3) - yValues2.get(2)) / (xValues1.get(0) - xValues2.get(3)));
				  horizontalAngle = Math.toDegrees(horizontalAngle);
			  }*/
			  double centerFrameX = uneditedImg.width() / 2.0;
			  double centerFrameY = uneditedImg.height() / 2.0;
			  Point centerTot = new Point(centerFrameX, centerFrameY);
			  double yDisplace = centerTot.y - yCenter;
			  double xDisplace = centerTot.x - xCenter;
			  //System.out.printf("x: %6.3f,    y: %6.3f \n", xDisplace, yDisplace);
			  double yAngle = yDisplace * 43.3 / centerFrameY;
			  double xAngle = xDisplace * 70.42 / centerFrameX;
			  //System.out.printf("xAngle: %5.3f, yAngle: %5.3f \n", xAngle, yAngle);
			  
			  double distanceY = TuningConstants.constantH/Math.tan(yAngle);
			  double distanceToGoal = distanceY/Math.cos(xAngle);
			  System.out.println("D: " + distanceToGoal);
			  Point pointTopLeft = new Point(xValues1.get(0), yValues1.get(1));
			  Point pointBottomLeft = new Point(xValues1.get(1), yValues1.get(2));
			  Point pointTopRight = new Point(xValues2.get(2), yValues2.get(0));
			  Point pointBottomRight = new Point(xValues2.get(3), yValues2.get(3));
			  double yNewOne = (pointTopLeft.y + pointTopRight.y) / 2;
			  double yNewTwo = (pointBottomLeft.y + pointBottomRight.y) / 2;
			  Point pointIdealTopLeft = new Point(pointTopLeft.x, yNewOne);
			  Point pointIdealBottomLeft = new Point(pointBottomLeft.x, yNewTwo);
			  Point pointIdealTopRight = new Point(pointTopRight.x, yNewOne);
			  Point pointIdealBottomRight = new Point(pointBottomRight.x, yNewTwo);
			  double height = pointIdealBottomRight.y - pointIdealTopRight.y;
			  double width = height * (41 / 20);
			  double xCenterValue = (pointTopLeft.x + pointTopRight.x) / 2;
			  Point pointHeadOnOne = new Point((xCenterValue - (width / 2)), pointIdealTopLeft.y);
			  Point pointHeadOnTwo = new Point((xCenterValue - (width / 2)), pointIdealBottomLeft.y);
			  Point pointHeadOnThree = new Point((xCenterValue + (width / 2)), pointIdealTopRight.y);
			  Point pointHeadOnFour = new Point((xCenterValue + (width / 2)), pointIdealBottomRight.y);
			  MatOfPoint2f rotatedShape = new MatOfPoint2f(pointTopLeft, pointBottomLeft, pointTopRight, pointBottomRight);
			  MatOfPoint2f idealRotation = new MatOfPoint2f(pointHeadOnOne, pointHeadOnTwo, pointHeadOnThree, pointHeadOnFour);
			  Mat rotation = Imgproc.getPerspectiveTransform(rotatedShape, idealRotation);
			  double[] angleData = new double[1];
			  rotation.get(0, 1, angleData);
			  double angle = Math.asin(angleData[0])*180/(Math.PI);
			  System.out.println("A: " + angle);
			}
		}
	}
}
