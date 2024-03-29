package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable, KeyListener {

	public static final int WIDTH = 800, HEIGHT = 500;

	private Thread thread;

	private Paddle paddle;
	private AIPaddle aiPaddle;

	private Ball ball;
	

	public Panel() {

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		addKeyListener(this);
		ball = new Ball();
		paddle = new Paddle();
		aiPaddle = new AIPaddle(ball);

	

		thread = new Thread(this);
		thread.start();

	}

	@Override
	public void paint(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		if(!ball.isGameOver(paddle)) {
		
		g.setColor(Color.white);
		g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);

		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("Player: " + ball.getScore(), 30, 30);

		paddle.paint(g);
		aiPaddle.paint(g);
		ball.paint(g);
		} else {
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("Game Over  " , 300, 250);
	

		}

	}

	@Override
	public void run() {

		while (true) {
			System.out.println("PAddle" + ball.getY());

			ball.checkCollision(paddle, aiPaddle);
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_UP) {
			paddle.setUp(true);
		}

		if (keyCode == KeyEvent.VK_DOWN) {
			paddle.setDown(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		paddle.setDown(false);

		paddle.setUp(false);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
