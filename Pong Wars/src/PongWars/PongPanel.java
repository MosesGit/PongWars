package PongWars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PongPanel extends JPanel implements KeyListener
{
	static PongPanel mainPanel;
	Image dbi;
	Graphics dbg;
	boolean redraw = true;
	static Paddle left, right;
	Ball ball;
	ArrayList<Integer> keys;
	ArrayList<LaserShot> l;
	ArrayList<Missile> mm;
	static int count1, count2, count3, count4;
	
	public PongPanel()
	{
		mainPanel = this;
		left = new Paddle(0, 225, 10, 50, true, true, false, false, "assets/images/Blue Paddle.png", Color.cyan);
		right = new Paddle(490, 225, 10, 50, true, true, false, false, "assets/images/Red Paddle.png", Color.red);
		ball = new Ball();
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
		keys = new ArrayList<Integer>();
		l = new ArrayList<LaserShot>();
		mm = new ArrayList<Missile>();
		count1 = 0;
		count2 = 0;
		count3 = 0;
		count4 = 0;
	}
	
	public void paint(Graphics g)
	{
		if (getWidth() != PongScreen.width || getHeight() != PongScreen.height)
			PongWars.ps.setSize(PongScreen.width * 2 - getWidth(), PongScreen.height * 2 - getHeight());
		if (dbg == null)
		{
			while (dbi == null)
				dbi = createImage(PongScreen.width, PongScreen.height);
			while (dbg == null)
				dbg = dbi.getGraphics();
		}
		draw(dbg);
		paintLaserShot(dbg);
		paintMissile(dbg);
		
		g.drawImage(dbi, 0, 0, this);
		repaint();
	}
	
	public void paintLaserShot(Graphics g)
	{
		if (left.HP > 0 && right.HP > 0)
		{
			for(LaserShot ls:l)
				ls.draw(g);
			update();
		}
	}
	
	public void paintMissile(Graphics g)
	{
		if(left.HP > 0 && right.HP > 0)
		{
			for(Missile ms:mm)
				ms.draw(g);
			update();
		}
	}
	
	public void draw(Graphics g)
	{
		left.undraw(g);
		right.undraw(g);
		ball.undraw(g);		
		update();
		
		if(redraw)
		{
			g.setColor(Color.black);
			g.fillRect(0, 0, PongScreen.width, PongScreen.height);
			redraw = false;
		}
		
		left.draw(g);
		right.draw(g);
		ball.draw(g);
		g.setColor(Color.white);
		g.drawString(left.name, 50, 10);
		g.drawString(right.name, PongScreen.width - 100, 10);
		g.drawString("Score: " + left.score, 50, 20);
		g.drawString("Score: " + right.score, PongScreen.width - 100, 20);
		g.drawString("HP: " + left.HP, 50, 30);
		g.drawString("HP: " + right.HP, PongScreen.width - 100, 30);
		g.drawString("Ammo: " + left.ammo, 50, 40);
		g.drawString("Ammo: " + right.ammo, PongScreen.width - 100, 40);
		g.drawString("Missiles:" + left.missileAmmo, 50, 50);
		g.drawString("Missiles:" + right.missileAmmo, PongScreen.width - 100, 50);
		g.drawString("Bullets fired: " + count1, 50, 60);
		g.drawString("Bullets fired: " + count2, PongScreen.width - 100, 60);
	}
	
	public void update()
	{
		try
		{
			Thread.sleep(4);
		}
		catch(Exception e){}
		
		if (keys.contains(KeyEvent.VK_ESCAPE))
			PongWars.close();
		
		if (keys.contains(KeyEvent.VK_W))
			left.moveUp();
		if (keys.contains(KeyEvent.VK_S))
			left.moveDown();
		if (keys.contains(KeyEvent.VK_UP))
			right.moveUp();
		if (keys.contains(KeyEvent.VK_DOWN))
			right.moveDown();
			
		if (keys.contains(KeyEvent.VK_SPACE))
			left.space++;
		if (keys.contains(KeyEvent.VK_ENTER))
			right.enter++;
		if(keys.contains(KeyEvent.VK_BACK_SLASH))
			right.slash++;
		if(keys.contains(KeyEvent.VK_V))
			left.vv++;
		
		if (left.space >= 20 && left.ammo > 0)
		{	
			LaserShot newLaserShot = left.shoot(3);
			l.add(newLaserShot);
			left.space = 0;
			count1++;
		}
		if (right.enter >= 20 && right.ammo > 0)
		{
			LaserShot newLaserShot2 = right.shoot(-3);
			l.add(newLaserShot2);
			right.enter = 0;
			count2++;
		}
		if(left.vv >= 20 && left.missileAmmo > 0)
		{
			Missile newMissile = left.shootM(1);
			mm.add(newMissile);
			left.vv = 0;
			count3++;
		}
		if(right.slash >= 20 && right.missileAmmo > 0)
		{
			Missile newMissile2 = right.shootM(-1);
			mm.add(newMissile2);
			right.slash = 0;
			count4++;
		}
		for (int i = 0; i < l.size(); i++)
		{
			l.get(i).move();
			if (l.get(i).rect.intersects(left.rect) && l.get(i).xVel < 0)
			{
				left.HP -= 2;
				l.get(i).kill();
				l.remove(i);
				i--;
				continue;
			}
			if (l.get(i).rect.intersects(right.rect) && l.get(i).xVel > 0)
			{
				right.HP -= 2;
				l.get(i).kill();
				l.remove(i);
				i--;
				continue;
			}
			if (l.get(i).x > PongScreen.width)
			{
				l.get(i).kill();
				l.remove(i);
				i--;
				continue;
			}
			if (l.get(i).x < 0)
			{
				l.get(i).kill();
				l.remove(i);
				i--;
				continue;
			}
		}
		for (int i = 0; i < mm.size(); i++)
		{
			mm.get(i).move();
			if (mm.get(i).rect.intersects(left.rect) && mm.get(i).xVel < 0)
			{
				left.HP-=10;
				mm.get(i).kill();
				mm.remove(i);
				i--;
				continue;
			}
			if (mm.get(i).rect.intersects(right.rect) && mm.get(i).xVel > 0)
			{
				right.HP-=10;
				mm.get(i).kill();
				mm.remove(i);
				i--;
				continue;
			}
			if (mm.get(i).x > PongScreen.width - 5)
			{
				mm.get(i).kill();
				mm.remove(i);
				i--;
				continue;
			}
			if (mm.get(i).x < -5)
			{
				mm.get(i).kill();
				mm.remove(i);
				i--;
				continue;
			}
		}
		
		ball.update();
		if (ball.rect.intersects(right.rect) && ball.xVel > 0)
		{
			ball.bounceX(right);
			right.ammo += 25;
			right.missileAmmo += 5;
		}
		if (ball.rect.intersects(left.rect) && ball.xVel < 0)
		{
			ball.bounceX(left);
			left.ammo += 25;
			left.missileAmmo += 5;
		}
		if (ball.x + ball.r * 2 < 0)
		{
			right.score++;
			ball.reset();
			right.HP = 100;
			left.HP = 100;
			right.ammo += 100;
			left.ammo += 0;
			right.missileAmmo += 10;
			left.missileAmmo += 0;
			try
			{
				Thread.sleep(10);
			}
			catch(Exception e){}
		}
		if (ball.x > PongScreen.width)
		{
			left.score++;
			ball.reset();
			right.HP = 100;
			left.HP = 100;
			right.ammo += 0;
			left.ammo += 100;
			right.missileAmmo += 0;
			left.missileAmmo += 10;
			try
			{
				Thread.sleep(10);
			}
			catch(Exception e){}
		}
		if (left.HP <= 0)
		{
			right.score++;
			ball.reset();
			right.HP = 100;
			left.HP = 100;
			right.ammo += 100;
			left.ammo += 0;
			right.missileAmmo += 10;
			left.missileAmmo += 0;
			try
			{
				Thread.sleep(10);
			}
			catch(Exception e){}
		}
		if (right.HP <= 0)
		{
			left.score++;
			ball.reset();
			right.HP = 100;
			left.HP = 100;
			right.ammo += 0;
			left.ammo += 100;
			right.missileAmmo += 0;
			left.missileAmmo += 10;
			try
			{
				Thread.sleep(10);
			}
			catch(Exception e){}
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		if (!keys.contains(e.getKeyCode()))
			keys.add(e.getKeyCode());
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e)
	{
		keys.remove(new Integer(e.getKeyCode()));
	}
}