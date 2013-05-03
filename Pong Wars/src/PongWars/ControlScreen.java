package PongWars;

import javax.swing.*;

public class ControlScreen extends EscapableFrame
{
	static int width, height;
	static ControlScreen mainClass;
	
	public ControlScreen()
	{
		width = 500;
		height = 500;
		setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new ControlPanel());
		setResizable(false);
		setTitle("Controls");
		setVisible(true);
	}
	
	public boolean close()
	{
		dispose();
		try
		{
			Thread.sleep(1000);
		}
		catch(Exception e){}
		PongWars.ps = new PongScreen();
		PongWars.ps.setLocation(PongWars.x, PongWars.y);
		PongWars.sp = new SoundPlayer();
		return true;
	}
}