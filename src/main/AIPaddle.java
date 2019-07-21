package main;

import java.awt.Color;
import java.awt.Graphics;

public class AIPaddle extends PaddleModel {

	private int x;
	private double y;
	private Ball ball;

	public AIPaddle() {
		x = Panel.WIDTH - 20;
		y = 520 / 2 - 40;
		ball = new Ball();
	}

	@Override
	void move() {

		y = ball.getY() - 40;
		System.out.println("PAddle" + ball.getY());
		if (y <= 0)
			y = 0;
		if (y >= 420)
			y = 420;

	}

	@Override
	void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, (int) y, 20, 80);
		move();

	}

	int getY() {
		return (int) y;
	}

	@Override
	void setUp(boolean input) {

	}

	@Override
	void setDown(boolean input) {

	}

	int getX() {
		return x;
	}

}
