package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	Image background;
	private KeyListener gameListener;
	
	ArrayList<Sprite> sprites;
	Sprite frog;
	
	public GamePanel(String backgroundImage) {
		
		this.sprites = new ArrayList<Sprite>();
		//this.frog = new Enemy();
		
		JFrame frame = new JFrame("Frogger");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		// Set the background 
		String path = ClassLoader.getSystemClassLoader().getResource(".").getPath() + backgroundImage;
		this.background = new ImageIcon(path).getImage();
		
		// TODO set preferredSize
		this.setPreferredSize(new Dimension(350,520));
		

		frame.setContentPane(this);
		frame.pack();
		frame.setVisible(true);
	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Paint the background
		g.drawImage(this.background, 0, 0, null);//, getWidth(), getHeight(), this);
		
		// Paint the Enemies
		for (Sprite s : this.sprites) {
			g.drawImage(s.getimage(), s.getPosition().x, s.getPosition().y, null);
		}
		
		// Paint the frog
		
	}
	
	public void addGameListener(KeyListener gameListener) {
		this.gameListener = gameListener;
	}
	
	public void addSprites(ArrayList<Sprite> newSprites) {
		this.sprites = newSprites;
	}
	

}
