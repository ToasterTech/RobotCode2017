package org.usfirst.frc.team5332.vision;
import java.io.*;
import java.net.*;


public class ArmSink {
	
	private DatagramSocket clientSocket;
	private double distance;
	private double angle;
	
	public ArmSink() throws SocketException {
		clientSocket = new DatagramSocket();
	}
	
	public void recievePacket() throws IOException {
		byte[] data = new byte[1024];
		DatagramPacket recievedPacket = new DatagramPacket(data, data.length);
		clientSocket.receive(recievedPacket);
		String distAndAngle = new String(recievedPacket.getData());
		distance = Double.parseDouble(distAndAngle.substring(0, distAndAngle.indexOf(',')));
		angle = Double.parseDouble(distAndAngle.substring(distAndAngle.indexOf(',') + 1));
	}
	
	public void close() {
		clientSocket.close();
	}
	
	public double getDist(){
		return distance;
	}
	public double getAngle(){
		return angle;
	}
	
} 