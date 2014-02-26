package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Map {

	private String mapBackgroundImage;
	private int mapFrogColumn;
	private int mapFrogRow;
	private int mapLaneHeight;
	private String mapName;
	private int mapNumberOfColumns;
	private int mapNumberOfRows;
	
	public String getBackgroundImage() {
		return this.mapBackgroundImage;
	}
	
	
	//public String backgroundImage;
	//public int frogStartCol;
	//public int frogStartRow;
	public Lane[] lanes;
	public ArrayList<Sprite> enemies;
	
	public Map(String xmlName) {
		
		try {
			File xmlFile = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath() + xmlName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			// needed? read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
			
			NodeList mapNodes = doc.getElementsByTagName("Map");
			
			for (int i = 0; i < mapNodes.getLength(); i++) {
				Element curNode = (Element) mapNodes.item(i);
				System.out.println("Current Node: " + curNode.getNodeName());

				this.mapName = curNode.getElementsByTagName("Name").item(0).getFirstChild().getTextContent();
				this.mapBackgroundImage = curNode.getElementsByTagName("BackgroundImage").item(0).getFirstChild().getTextContent();
				this.mapFrogColumn = Integer.parseInt(curNode.getElementsByTagName("FrogColumn").item(0).getFirstChild().getTextContent());
				this.mapFrogRow = Integer.parseInt(curNode.getElementsByTagName("FrogRow").item(0).getFirstChild().getTextContent());
				this.mapLaneHeight = Integer.parseInt(curNode.getElementsByTagName("LaneHeight").item(0).getFirstChild().getTextContent());
				this.mapNumberOfColumns = Integer.parseInt(curNode.getElementsByTagName("NumberOfColumns").item(0).getFirstChild().getTextContent());
				this.mapNumberOfRows = Integer.parseInt(curNode.getElementsByTagName("NumberOfRows").item(0).getFirstChild().getTextContent());
				
				
				
				
			}
			
	//		backgroundImage = "SuperFrogger\Map\BackgroundImage";
			//backgroundImage = "Frogger with pipes.png";
			
//			numberOfColumns = 13;
//			numberOfRows = 13;
			
//			frogStartCol = 5;
//			frogStartRow = 9;
			
			lanes = new Lane[this.mapNumberOfRows];
			
			// Lane 0 - Pipes
			lanes[0] = new Lane(new ArrayList<Sprite>(), true);
			
			// Lane 1 - Wiggler left
			lanes[1] = new Lane(new ArrayList<Sprite>(), true);
			lanes[1].addSprite(new Enemy(new Point(0, 1 * this.mapLaneHeight), true, 4, "Wiggler Floating.png"));
			lanes[1].addSprite(new Enemy(new Point(100, 1 * this.mapLaneHeight), true, 4, "Wiggler Floating.png"));
			
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
		} catch (Exception e) {
			System.out.println("wait");
		}
	}
}
