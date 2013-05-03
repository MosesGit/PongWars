package PongWars;

import java.awt.*;
import javax.swing.*;

public class Paddle
{
	int x, y;
	int width, height;
	boolean up, down, left, right;
	int space, enter, slash, vv;
	Rectangle rect;
	int score, HP, ammo, missileAmmo;
	Image paddle, bg;
	Color col;
	String name;
	static int count = 1;
	
	public Paddle(int x, int y, int width, int height, boolean up, boolean down, boolean left, boolean right, String s, Color c)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		space = 0;
		enter = 0;
		rect = new Rectangle(x, y, width, height);
		score = 0;
		HP = 100;
		ammo = 100;
		missileAmmo = 10;
		paddle = new ImageIcon(s).getImage();
		String ss = "assets/images/" + (int)(Math.random() * 10 + 1) + ".png";
		bg = new ImageIcon(ss).getImage();
		col = c;
		name = JOptionPane.showInputDialog("Enter name for player " + count + ": ");
		count++;
	}
	
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
		keepInBounds();
	}
	
	public void moveUp()
	{
		if(up)
			moveUp(1);
	}
	
	public void moveUp(int dy)
	{
		if(up)
			y -= dy;
		keepInBounds();
	}
	
	public void moveDown()
	{
		if(down)
			moveDown(1);
	}
	
	public void moveDown(int dy)
	{
		if(down)
			y += dy;
		keepInBounds();
	}
	
	public void moveLeft()
	{
		if(left)
			moveLeft(1);
	}
	
	public void moveLeft(int dx)
	{
		if(left)
			x -= dx;
		keepInBounds();
	}
	
	public void moveRight()
	{
		if(right)
			moveRight(1);
	}
	
	public void moveRight(int dx)
	{
		if(right)
			x += dx;
		keepInBounds();
	}
	
	public void keepInBounds()
	{
		if(x < 0)
			x = 0;
		if(y < 0)
			y = 0;
		if(x + width > PongScreen.width)
			x = PongScreen.width - width;
		if(y + height > PongScreen.height)
			y = PongScreen.height - height;
		rect.setLocation(x, y);
	}
	
	public void moveLeftRight()
	{
		left = right = true;
		up = down = false;
	}
	
	public void moveUpDown()
	{
		up = down = true;
		left = right = false;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		g.drawImage(paddle, x, y, null);
	}
	
	public void undraw(Graphics g)
	{
		g.drawImage(bg, 0, 0, null);
	}
	
	public LaserShot shoot(int a)
	{
		ammo--;
		return new LaserShot(x - 2, y + 22, a, 5, 15, col);
	}
	
	public Missile shootM(int a)
	{
		missileAmmo--;
		return new Missile(x - 2, y + 22, a, 9, 19);
	}
}