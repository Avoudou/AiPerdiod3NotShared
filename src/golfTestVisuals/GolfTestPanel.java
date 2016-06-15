package golfTestVisuals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import components.Position;

import entities.Ball;

public class GolfTestPanel extends JPanel {

	private Ball ball;

	private CustomKeyListener listener = new CustomKeyListener();

	public GolfTestPanel(Ball ball) {

		this.ball = ball;

		addKeyListener(new CustomKeyListener());
		setFocusable(true);
	}

	@Override
	public void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		drawCourt(graph);
		drawBall(graph);

	}

	public void drawCourt(Graphics graph) {
		graph.setColor(Color.GREEN);
		graph.fillRect(0, 0, 800, 600);
		// drawTargetArea(graph);
		// drawToxicArea(graph);
	}

	public void drawBall(Graphics graph) {
		graph.setColor(Color.WHITE);
		graph.fillOval((int) (ball.getComponent(Position.class).x), (int) (ball.getComponent(Position.class).y), 5, 5);

	}

	public void drawTargetArea(Graphics graph) {
		graph.setColor(Color.BLACK);
		graph.fillOval((int) 0, 0, 20, 20);

	}

	public void drawToxicArea(Graphics graph) {
		
		

	}

	class CustomKeyListener implements KeyListener {
		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_B) {
				

		}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}
}

