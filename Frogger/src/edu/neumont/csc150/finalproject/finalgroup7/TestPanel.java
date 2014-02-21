package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TestPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	Image background;
	Image front;
	
	ArrayList<Sprite> sprites;

	public TestPanel() {
		this.background = new ImageIcon(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "Frogger with pipes.png").getImage();
		this.front = new ImageIcon(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "1up shroom.png").getImage();
		this.setPreferredSize(new Dimension(350,520));
		//this.setFocusable(true);		
	}
	
	public TestPanel(Map currentMap) {
		sprites = new ArrayList<>();
		this.background = new ImageIcon(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "Frogger with pipes.png").getImage();
		for (Lane l : currentMap.lanes) {
			for (Sprite s : l.sprites) {
				sprites.add(s);
			}
		}
		this.front = new ImageIcon(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "1up shroom.png").getImage();
		this.setPreferredSize(new Dimension(350,520));
		//this.setFocusable(true);		
	}
	

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//String path = "C:/350x520.png"; //ClassLoader.getSystemClassLoader().getResource(".").getPath() + "350x520.jpg";
		//this.background = Toolkit.getDefaultToolkit().createImage(path);
		
		g.drawImage(this.background, 0, 0, null);//, getWidth(), getHeight(), this);
		g.drawImage(this.front, 0,0,null);
		
		for (Sprite s : sprites) {
			g.drawImage(s.image, s.position.x, s.position.y, null);
		}
		
	}
}
