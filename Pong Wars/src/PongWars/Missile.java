package PongWars;

import java.awt.*;

public class Missile
{
	int x, y;
	int xVel;
	int height, width;
	Rectangle rect;
	Color col;
	
	public Missile(int x, int y, int xV, int h, int w)
	{
		this.x = x;
		this.y = y;
		xVel = xV;
		height = h;
		width = w;
		rect = new Rectangle(x, y, width, height);
		col = Color.green;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(col);
		g.fillOval(x, y, width, height);
	}
	
	public void move()
	{
		x += xVel;
		rect.setLocation(x, y);
	}
	
	public void kill()
	{
		x = 0;
		y = 0;
		height = 0;
		width = 0;
		rect = null;
		col = null;
	}
}