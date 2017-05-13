package com.ortega.rollingring;

import static com.ortega.rollingring.Constants.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class RingGeneratingListener implements ActionListener {
	
	private RollingPanel panel;
	private int ringsToCreate;
	private int ringsCreated = 0;
	private int creationStep;
	private int totalFrames = 0;
	
	public RingGeneratingListener(RollingPanel panel, int ringsToCreate, int creationStep) {
		this.panel = panel;
		this.ringsToCreate = ringsToCreate;
		this.creationStep = creationStep;
		
		panel.addTimerListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (ringsCreated == ringsToCreate)
			((Timer) e.getSource()).removeActionListener(this);
		totalFrames++;
		if (totalFrames % creationStep == 0) {
			panel.addRing(RING_RADIUS, Math.PI / ringsToCreate * ringsCreated, 0);
			ringsCreated++;
		}
			
	}
}