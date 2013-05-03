package PongWars;

import java.awt.event.*;
import javax.swing.*;

public abstract class EscapableFrame extends JFrame
{
	public EscapableFrame()
	{
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
		getRootPane().getActionMap().put("Cancel", new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				close();
			}
		});
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent evt) 
			{
				close();
			}
		});
	}
	abstract public boolean close();
}