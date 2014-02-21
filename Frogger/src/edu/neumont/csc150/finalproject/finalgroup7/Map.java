package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;
import java.util.ArrayList;

public class Map {

	public int LANE_HEIGHT = 40;
	
	public int numberOfColumns;
	public int numberOfRows;
	public int frogStartCol;
	public int frogStartRow;
	public Lane[] lanes;
	public ArrayList<Sprite> enemies;
	
	public Map() {
		numberOfColumns = 13;
		numberOfRows = 13;
		frogStartCol = 5;
		frogStartRow = 9;
		
		lanes = new Lane[numberOfRows];
		
		// Lane 0 - Pipes
		lanes[0] = new Lane(new ArrayList<Sprite>(), true);
		
		// Lane 1 - Wiggler left
		lanes[1] = new Lane(new ArrayList<Sprite>(), true);
		lanes[1].addSprite(new Enemy(new Point(0, 1 * LANE_HEIGHT), true, 4, "Wiggler Floating.png"));
		lanes[1].addSprite(new Enemy(new Point(100, 1 * LANE_HEIGHT), true, 4, "Wiggler Floating.png"));
		
		// Lane 2 - Wiggler left
		lanes[2] = new Lane(new ArrayList<Sprite>(), true);
		
		// Lane 3 - Wiggler left
		lanes[3] = new Lane(new ArrayList<Sprite>(), true);
		
		// Lane 4 - Wiggler left
		lanes[4] = new Lane(new ArrayList<Sprite>(), true);
		
		// Lane 5 - Wiggler left
		lanes[5] = new Lane(new ArrayList<Sprite>(), true);
		
		// Lane 6 - Bricks
		lanes[6] = new Lane(new ArrayList<Sprite>(), true);
		
		// Lane 7 - Road
		lanes[7] = new Lane(new ArrayList<Sprite>(), true);
		
		// Lane 8 - Road
		lanes[8] = new Lane(new ArrayList<Sprite>(), true);
		
		// Lane 9 - Road
		lanes[9] = new Lane(new ArrayList<Sprite>(), true);
		
		// Lane 10 - Road
		lanes[10] = new Lane(new ArrayList<Sprite>(), true);
		
		// Lane 11 - Road
		lanes[11] = new Lane(new ArrayList<Sprite>(), true);
		
		// Lane 12 - Bricks
		
		enemies = new ArrayList<>();
		enemies.add(new Enemy(new Point(10,1), true, 1, "1up shroom.png"));
		enemies.add(new Enemy(new Point(50,1), true, 1, "1up shroom.png"));
		enemies.add(new Enemy(new Point(100,1), true, 1, "1up shroom.png"));
		lanes[12] = new Lane(enemies, false);
		
//		enemies.add(new Enemy(2, 8, -1, Color.RED, new ImageIcon("")));
//		enemies.add(new Enemy(4, 8, -1, Color.RED, new ImageIcon("")));
//		enemies.add(new Enemy(6, 8, -1, Color.RED, new ImageIcon("")));
//		enemies.add(new Enemy(3, 7, -2, Color.ORANGE, new ImageIcon("")));
//		enemies.add(new Enemy(5, 7, -2, Color.ORANGE, new ImageIcon("")));
//		enemies.add(new Enemy(7, 7, -2, Color.ORANGE, new ImageIcon("")));		
	}
}
