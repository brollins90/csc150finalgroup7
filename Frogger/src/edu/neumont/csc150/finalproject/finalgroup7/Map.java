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
	private int mapColumnWidth;
	private int mapLaneHeight;
	private String mapName;
	private int mapNumberOfColumns;
	private int mapNumberOfRows;
	
	private Frog frog;
	private Lane[] lanes;
	
	public String getBackgroundImage() {
		return this.mapBackgroundImage;
	}
	
	public int getColumnWidth() {
		return this.mapColumnWidth;
	}
	
	public int getNumberOfColumns() {
		return this.mapNumberOfColumns;
	}
	
	public Frog getFrog() {
		return this.frog;
	}
	
	public Lane[] getLanes() {
		return this.lanes;
	}
	
	
	//public String backgroundImage;
	//public int frogStartCol;
	//public int frogStartRow;
	//public ArrayList<Sprite> enemies;
	
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
				this.mapColumnWidth = Integer.parseInt(curNode.getElementsByTagName("ColumnWidth").item(0).getFirstChild().getTextContent());
				this.mapLaneHeight = Integer.parseInt(curNode.getElementsByTagName("LaneHeight").item(0).getFirstChild().getTextContent());
				this.mapNumberOfColumns = Integer.parseInt(curNode.getElementsByTagName("NumberOfColumns").item(0).getFirstChild().getTextContent());
				//this.mapNumberOfRows = Integer.parseInt(curNode.getElementsByTagName("NumberOfRows").item(0).getFirstChild().getTextContent());
				
				
				NodeList laneNodes = curNode.getElementsByTagName("Lane");
				
				this.mapNumberOfRows = laneNodes.getLength();
				this.lanes = new Lane[this.mapNumberOfRows];
				

				NodeList frogNodes = curNode.getElementsByTagName("Frog");
				Element frogElement = (Element) frogNodes.item(0);
				int frogColumn = Integer.parseInt(frogElement.getElementsByTagName("Column").item(0).getFirstChild().getTextContent());
				String frogImage = frogElement.getElementsByTagName("Image").item(0).getFirstChild().getTextContent();
				this.frog = new Frog(new Point(frogColumn * this.mapColumnWidth, (this.mapNumberOfRows - 1) * this.mapLaneHeight), false, 0, frogImage);
				
				
				for (int laneIndex = 0; laneIndex < laneNodes.getLength(); laneIndex++) {
					Element curLane = (Element) laneNodes.item(laneIndex);
					boolean laneIsBad = Boolean.parseBoolean(curNode.getElementsByTagName("Lane").item(0).getFirstChild().getTextContent());
					
					// Create the Lane object and add it the the Array
					this.lanes[laneIndex] = new Lane(new ArrayList<Sprite>(), laneIsBad);
					
					// Find the enemies and add them to the Lane
					NodeList spriteList = curLane.getElementsByTagName("Sprite");
					for (int spriteIndex = 0; spriteIndex < spriteList.getLength(); spriteIndex++) {
						Element curSprite = (Element) spriteList.item(spriteIndex);

						int spriteXCoord = Integer.parseInt(curSprite.getElementsByTagName("XCoord").item(0).getFirstChild().getTextContent());
						int spriteYCoord = laneIndex * this.mapLaneHeight;
						boolean spriteMovesLeft = Boolean.parseBoolean(curSprite.getElementsByTagName("MovesLeft").item(0).getFirstChild().getTextContent());
						int spriteSpeed = Integer.parseInt(curSprite.getElementsByTagName("Speed").item(0).getFirstChild().getTextContent());
						String spriteImage = curSprite.getElementsByTagName("Image").item(0).getFirstChild().getTextContent();
						
						this.lanes[laneIndex].addSprite(new Enemy(new Point(spriteXCoord, spriteYCoord), spriteMovesLeft, spriteSpeed, spriteImage));
					} // end Sprites					
				} // end Lanes
			} // end Map
		} catch (Exception e) {
			System.out.println("wait");
		}
	}
}
