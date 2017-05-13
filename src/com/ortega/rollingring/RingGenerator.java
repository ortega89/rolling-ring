package com.ortega.rollingring;

import static com.ortega.rollingring.Constants.*;

public class RingGenerator implements Runnable {
	
	private RollingPanel panel;
	private int count;
	private int delay;
	
	public RingGenerator(RollingPanel panel, int count, int delay) {
		this.panel = panel;
		this.count = count;
		this.delay = delay;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(delay / 2);
			for (int i = 0; i < count; i++) {
				panel.addRing(RING_RADIUS, Math.PI / count * i, 0);
				Thread.sleep(delay);
			}
		} catch (InterruptedException e) {
			return;
		}
		System.out.println("Rings creation done");
	}
	
}