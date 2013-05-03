package PongWars;

import java.awt.*;
import javax.swing.*;

public class ControlPanel extends JPanel
{
	static ControlPanel cp;
	Image ctrls;
	
	public ControlPanel()
	{
		cp = this;
		setFocusable(true);
		requestFocus();
		ctrls = new ImageIcon("assets/images/Controls.png").getImage();
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(ctrls, 0, 0, this);
	}
}