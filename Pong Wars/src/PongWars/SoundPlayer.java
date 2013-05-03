package PongWars;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;

public class SoundPlayer extends JFrame
{
	AudioFormat audioFormat;
	AudioInputStream audioInputStream;
	SourceDataLine sourceDataLine;
	boolean stopPlayback = false;
	final JButton stopBtn = new JButton("Stop");
	final JButton playBtn = new JButton("Play");
	String ss = "assets/audio/" + (int)(Math.random() * 10 + 1) + ".wav";
	final JTextField textField = new JTextField(ss);
	PlayThread sound_thread;
	Timer t;
	
	public static void main(String args[])
	{
		new SoundPlayer();
	}
	
	public SoundPlayer()
	{
		stopBtn.setEnabled(false);
		playBtn.setEnabled(true);
		
		playAudio();
		playBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				stopBtn.setEnabled(true);
				playBtn.setEnabled(false);
				playAudio();
			}
		});
		stopBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				stopPlayback = true;
			}
		});
		
		getContentPane().add(playBtn, "West");
		getContentPane().add(stopBtn, "East");
		getContentPane().add(textField, "North");
		setTitle("Sound Player");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(250,100);
		setVisible(false);
		t = new Timer(120000, new ActionListener()
		{
          @SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e)
			 {
              sound_thread.stop();
				  playAudio();
           }
		});
		t.setRepeats(true);
		t.start();

	}
	
	private void playAudio()
	{
		try
		{
			String ss = "assets/audio/" + (int)(Math.random() * 10 + 1) + ".wav";
			File soundFile = new File(ss);
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			audioFormat = audioInputStream.getFormat();
			
			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
			
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			
			sound_thread = new PlayThread();
			sound_thread.start();
			
			}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	class PlayThread extends Thread
	{
		byte tempBuffer[] = new byte[10000];
		
		public void run()
		{
			try
			{
				sourceDataLine.open(audioFormat);
				sourceDataLine.start();
				
				int count;
				
				while((count = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1 && stopPlayback == false)
				{
					if(count > 0)
					{
						sourceDataLine.write(tempBuffer, 0, count);
					}
				}
				sourceDataLine.drain();
				sourceDataLine.close();
				
				stopBtn.setEnabled(false);
				playBtn.setEnabled(true);
				stopPlayback = false;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
}