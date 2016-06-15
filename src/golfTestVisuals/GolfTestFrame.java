package golfTestVisuals;

import javax.swing.JFrame;

import entities.Ball;

public class GolfTestFrame extends JFrame {
	private GolfTestPanel represPanel;
	private Ball ball;

	private final int xAxisSize = 900;
	private final int yAxisSize = 700;

	public GolfTestFrame(Ball ball) {
		this.ball = ball;
		setSize(xAxisSize, yAxisSize);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("golf ai test");
		setResizable(false);
		represPanel = new GolfTestPanel(ball);

		getContentPane().add(represPanel);
		setVisible(true);
	}

	public void refresh() {
		represPanel.repaint();
	}

}
