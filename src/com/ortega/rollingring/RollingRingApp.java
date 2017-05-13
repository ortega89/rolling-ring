package com.ortega.rollingring;

import java.awt.Window.Type;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import static com.ortega.rollingring.Constants.*;

public class RollingRingApp implements Runnable {

	private JFrame frame;
	private RollingPanel panel;
	public static Object sync = new Object();
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new RollingRingApp());
	}

	@Override
	public void run() {
		frame = new JFrame("Rolling Ring");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setType(Type.UTILITY);
		
		panel = new RollingPanel(PANEL_SIZE, PANEL_SIZE);
		panel.addTimerListener(new RingGeneratingListener(panel, 
				Constants.RINGS_COUNT, Constants.RINGS_CREATION_DELAY_FRAMES));
		frame.add(panel);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		//new Thread(new RingGenerator(panel, RINGS_COUNT, RINGS_CREATION_DELAY))
				//.start();
	}
}
