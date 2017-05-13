package com.ortega.rollingring;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import static com.ortega.rollingring.Constants.*;

@SuppressWarnings("serial")
public class RollingPanel extends JPanel implements ActionListener {
	
	private Point barrelPos;
	private Dimension barrelSize;
	private List<SmallRing> rings;
	private Timer timer;
	
	public RollingPanel(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
		this.barrelPos = new Point(BARREL_MARGIN, BARREL_MARGIN);
		this.barrelSize = new Dimension(
				width-2*BARREL_MARGIN, height-2*BARREL_MARGIN);
		
		setLayout(null);
		rings = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());
		timer = new Timer(TIMER_DELAY, this);
		timer.setInitialDelay(TIMER_DELAY);
		timer.start();
	}
	
	public void addRing(int ringRadius, double angle, double phase) {
		SmallRing ring = new SmallRing(
				new Point(getWidth() / 2 - 1, getHeight() / 2 - 1), 
				ringRadius, barrelSize.width / 2 - BARREL_PADDING, angle, phase);
		rings.add(ring);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for (SmallRing ring : rings) {
			ring.stepPhase(Math.PI / 180 * RING_PHASE_STEP);
		}
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D gg = prepareGraphics(g);

		gg.setColor(Color.WHITE);
		gg.fillRect(0, 0, getWidth(), getHeight());
		
		gg.setColor(Color.BLACK);
		gg.setStroke(new BasicStroke(PEN_WIDTH));
		gg.drawArc(barrelPos.x, barrelPos.y, barrelSize.width, barrelSize.height, 0, 360);
		
		for (SmallRing ring : rings)
			ring.paint(gg);
	}
	
	private Graphics2D prepareGraphics(Graphics g) {
		Graphics2D gg = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	    gg.setRenderingHints(rh);
	    return gg;
	}

	public void addTimerListener(ActionListener listener) {
		timer.addActionListener(listener);
	}
}