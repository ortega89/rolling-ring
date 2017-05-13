package com.ortega.rollingring;

import java.awt.Graphics;
import java.awt.Point;

public class SmallRing {
	private Point center;
	private int ringRadius;
	private int pathRadius;
	private double angleDegrees;
	private double angleRadians;
	private double phase;
	
	public SmallRing(Point center, int ringRadius, int pathRadius, double angleRadians, double phase) {
		this.center = center;
		this.ringRadius = ringRadius;
		this.pathRadius = pathRadius;
		this.angleRadians = angleRadians;
		this.angleDegrees = angleRadians / Math.PI * 180;
		this.phase = phase;
	}
	
	public void paint(Graphics g) {
		g.drawArc(this.getX(), this.getY(), this.getWidth(), this.getHeight(), 0, 360);
	}
	
	public void stepPhase(double step) {
		phase += step;
	}
	
	public int getX() {
		int x = (int) Math.round(center.x + pathRadius * Math.cos(angleRadians) * Math.sin(phase) - ringRadius);
		return x;
	}
	
	public int getY() {
		int y = (int) Math.round(center.y - pathRadius * Math.sin(angleRadians) * Math.sin(phase) - ringRadius);
		return y;
	}
	
	public int getWidth() {
		return ringRadius * 2;
	}
	
	public int getHeight() {
		return ringRadius * 2;
	}
	
	public String toString() {
		return "angle = "+angleDegrees+", phase="+(phase / Math.PI * 180);
	}
}