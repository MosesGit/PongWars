package PongWars;

import static java.lang.Math.*;
import java.awt.*;

public class Ball
{
	double x, y;
	double xVel, yVel;
	int r;
	Rectangle rect;
	int stayCount;
	double speedIncrease = 1.0;
	
	public Ball()
	{
		this (PongScreen.width / 2 - 5, PongScreen.width / 2 - 5, 1, 1, 5);
	}
	public Ball(double x, double y, double xVel, double yVel, int r)
	{
		this.x = x;
		this.y = y;
		this.xVel = 0.75;
		this.yVel = 0.75;
		this.r = r;
		rect = new Rectangle((int) round(x), (int) round(y), r * 2, r * 2);
		stayCount = 0;
	}
	
	public void update()
	{
		if (stayCount < 200)
		{
			stayCount++;
			return;
		}
		x += xVel;
		y += yVel;
		if (y < 0)
			bounceY();
		if (y + r * 2 > PongScreen.width)
			bounceY();
		rect.setLocation((int) round(x), (int) round(y));
	}
	
	public void reset()
	{
		x = PongScreen.width /2 - 5;
		y = PongScreen.width / 2 - 5;
		stayCount = 0;
		setMagnitude(sqrt(2));
		double angle = getAngle();
		if (angle > 0 && angle <= PI / 2)
			setAngle(PI / 4);
		else if (angle > PI / 2 && angle <= PI)
			setAngle(3 * PI / 4);
		else if (angle > PI && angle <= 3 * PI / 2)
			setAngle(5 * PI / 4);
		else if (angle > 3 * PI / 2)
			setAngle(7 * PI / 4);
	}
	
	public void bounceX(Paddle p)
	{
		double angle = getAngle();
		double mag = getMagnitude();
		if (p.y + p.height / 2 > y + r)
		{
			if (yVel > 0)
			{
				if (angle > PI / 2)
				{
					double diff = PI - angle;
					double percent = (double) (p.y + p.height / 2 - y - r) / (p.height / 2);
					percent *= 0.25;
					diff *= 1 - percent;
					double newAngle = PI - diff;
					setAngle(newAngle);
				}
				else if (angle < PI / 2)
				{
					double diff = angle;
					double percent = (double) (p.y + p.height / 2 - y - r) / (p.height / 2);
					percent *= 0.25;
					diff *= 1 - percent;
					double newAngle = diff;
					setAngle(newAngle);
				}
			}
			else if (yVel < 0)
			{
				if (angle < PI * 3 / 2 )
				{
					double diff = 3 * PI / 2 - angle;
					double percent = (double) (p.y + p.height / 2 - y - r) / (p.height / 2);
					percent *= 0.25;
					diff *= 1 - percent;
					double newAngle = 3 * PI / 2 - diff;
					setAngle(newAngle);
				}
				else if (angle > PI * 3 / 2)
				{
					double diff = angle - 3 * PI / 2;
					double percent = (double) (p.y + p.height / 2 - y - r) / (p.height / 2);
					percent *= 0.25;
					diff *= 1 - percent;
					double newAngle = diff + 3 * PI / 2;
					setAngle(newAngle);
				}
			}
		}
		else if (p.y + p.height / 2 < y + r)
		{
			if (yVel > 0)
			{
				if (angle > PI / 2)
				{
					double diff = angle - PI / 2;
					double percent = (double) (y + r - p.y - p.height / 2) / (p.height / 2);
					percent *= 0.25;
					diff *= 1 - percent;
					double newAngle = PI / 2 + diff;
					setAngle(newAngle);
				}
				else if (angle < PI / 2)
				{
					double diff = PI / 2 - angle;
					double percent = (double) (y + r - p.y - p.height / 2) / (p.height / 2);
					percent *= 0.25;
					diff *= 1 - percent;
					double newAngle = PI / 2 - diff;
					setAngle(newAngle);
				}
			}
			else if (yVel < 0)
			{
				if (angle < PI * 3 / 2)
				{
					double diff = angle - PI;
					double percent = (double) (y + r - p.y - p.height / 2) / (p.height / 2);
					percent *= 0.25;
					diff *= 1 - percent;
					double newAngle = PI + diff;
					setAngle(newAngle);
				}
				else if (angle > PI * 3 / 2)
				{
					double diff = 2 * PI - angle;
					double percent = (double) (y + r - p.y - p.height / 2) / (p.height / 2);
					percent *= 0.25;
					diff *= 1 - percent;
					double newAngle = 2 * PI - diff;
					setAngle(newAngle);
				}
			}
		}
		xVel *= -1;
		setMagnitude(mag * 1);
	}
	public void bounceY(Paddle p)
	{
		yVel *= -1;
	}
	public void bounceX()
	{
		xVel *= -1;
	}
	public void bounceY()
	{
		yVel *= -1;
	}
	
	public double getAngle()
	{
		double angle = atan(yVel / xVel);
		if (xVel < 0)
			angle += PI;
		if (angle < 0)
			angle += 2 * PI;
		return angle;
	}
	public void setAngle(double angle)
	{
		double mag = getMagnitude();
		xVel = cos(angle) * mag;
		yVel = sin(angle) * mag;
	}
	public double getMagnitude()
	{
		double mag = 0.7;
		return mag;
	}
	public void setMagnitude(double mag)
	{
		double angle = getAngle();
		xVel = cos(angle) * mag;
		yVel = sin(angle) * mag;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.yellow);
		g.fillOval((int) round(x), (int) round(y) ,r * 2, r * 2);
	}
	public void undraw(Graphics g)
	{
		g.setColor(Color.black);
		g.fillOval((int) round(x), (int) round(y), r * 2, r * 2);
	}
}