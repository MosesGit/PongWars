package PongWars;
import javax.swing.*;

public class PongScreen extends JFrame
{
	static int width = 500;
	static int height = 500;
	
	public PongScreen()
	{
		setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new PongPanel());
		setResizable(false);
		setTitle("Pong Wars");
		setVisible(true);
	}
}