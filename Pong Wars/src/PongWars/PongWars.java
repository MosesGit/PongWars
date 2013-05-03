package PongWars;

//By Moses Chen & Luren Wang
//McLean High School
//AP Computer Science A
//Sophomore Year 2011
//End of the year project (graphics)
//Credits to Longhorndude08 (on YouTube) for Pong tutorial (with all the complicated math equations)

import java.awt.*;

public class PongWars
{
	static ControlScreen cs;
	static PongScreen ps;
	static SoundPlayer sp;
	static Dimension dim;
	static int w, h, x, y;
	
	public static void main(String[] args)
	{
		cs = new ControlScreen();
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		w = cs.getSize().width;
		h = cs.getSize().height;
		x = (dim.width - w) / 2;
		y = (dim.height-h) / 2;
		cs.setLocation(x, y);
	}
	
	public static void close()
	{
		sp.dispose();
		ps.dispose();
		System.out.println("Game Over!");
		if (PongPanel.left.score > PongPanel.right.score)
			System.out.println(PongPanel.left.name + " wins!");
		else if (PongPanel.left.score < PongPanel.right.score)
			System.out.println(PongPanel.right.name + " wins!");
		else System.out.println("It was a tie!");
		System.exit(0);
	}
}