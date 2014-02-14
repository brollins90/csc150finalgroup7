package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TestPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	Image background;

	public TestPanel() {
		this.background = new ImageIcon(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "350x520.png").getImage();
		this.setPreferredSize(new Dimension(350,520));
		//this.setFocusable(true);
		
	}
	

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//String path = "C:/350x520.png"; //ClassLoader.getSystemClassLoader().getResource(".").getPath() + "350x520.jpg";
		//this.background = Toolkit.getDefaultToolkit().createImage(path);
		
		g.drawImage(this.background, 0, 0, null);//, getWidth(), getHeight(), this);
	}
}
