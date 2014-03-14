package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * The Config class opens an xml file and creates all the necessary objects to run the game
 * @author Blake Rollins & Wyatt Reynolds
 */
public class Config {

	private Frog frog;
	private Map<String, Image> imageMap;
	private Lane[] lanes;
	private String mapBackgroundImageKey;
	private int mapColumnWidth;
	private int mapLaneHeight;
	private String mapName;
	private int mapNumberOfColumns;
	private int mapNumberOfRows;
	private String resourcePath;
	private int startingLives;

	/**
	 * Takes in an xml file and creates all the required objects for the game
	 * @param xmlName The name of the xml file (Just the file name, the path is pulled from the jar location)
	 */
	public Config(String xmlName) throws ConfigurationException {

		//CSC150 Identified Requirement – 9.1 Try - Catch
		/*
		*  Anything that has to load and read a file might fail, so it is wrapped in a try-catch block
		*/
		try {
			this.imageMap = new HashMap<String, Image>();

			this.resourcePath = ClassLoader.getSystemClassLoader().getResource(".").getPath();
			File xmlFile = new File(resourcePath + xmlName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			addImageToMap("you-win.png");
			addImageToMap("you-lose.png");
			
			// needed? read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			// Start with the Map element
			NodeList mapNodes = doc.getElementsByTagName("Map");

			for (int i = 0; i < mapNodes.getLength(); i++) {
				Element curNode = (Element) mapNodes.item(i);

				this.mapName = curNode.getElementsByTagName("Name").item(0).getFirstChild().getTextContent();
				this.resourcePath += curNode.getElementsByTagName("LevelFolder").item(0).getFirstChild().getTextContent() + "/";
				// We use the StartingLives element to create the Srites for the life icons in the last lane
				this.startingLives = Integer.parseInt(curNode.getElementsByTagName("StartingLives").item(0).getFirstChild().getTextContent());
				
				this.mapBackgroundImageKey = curNode.getElementsByTagName("BackgroundImage").item(0).getFirstChild().getTextContent();
				addImageToMap(mapBackgroundImageKey);
				this.mapColumnWidth = Integer.parseInt(curNode.getElementsByTagName("ColumnWidth").item(0).getFirstChild().getTextContent());
				this.mapLaneHeight = Integer.parseInt(curNode.getElementsByTagName("LaneHeight").item(0).getFirstChild().getTextContent());
				this.mapNumberOfColumns = Integer.parseInt(curNode.getElementsByTagName("NumberOfColumns").item(0).getFirstChild().getTextContent());

				NodeList laneNodes = curNode.getElementsByTagName("Lane");

				this.mapNumberOfRows = laneNodes.getLength();
				this.lanes = new Lane[this.mapNumberOfRows];

				NodeList frogNodes = curNode.getElementsByTagName("Frog");
				Element frogElement = (Element) frogNodes.item(0);
				int frogColumn = Integer.parseInt(frogElement.getElementsByTagName("Column").item(0).getFirstChild().getTextContent());
				ArrayList<String> frogImageKeys = new ArrayList<String>();

				String frogImageKey = frogElement.getElementsByTagName("ImageUp").item(0).getFirstChild().getTextContent();
				addImageToMap(frogImageKey);
				frogImageKeys.add(frogImageKey);

				String frogImageLeftKey = frogElement.getElementsByTagName("ImageLeft").item(0).getFirstChild().getTextContent();
				addImageToMap(frogImageLeftKey);
				frogImageKeys.add(frogImageLeftKey);

				String frogImageRightKey = frogElement.getElementsByTagName("ImageRight").item(0).getFirstChild().getTextContent();
				addImageToMap(frogImageRightKey);
				frogImageKeys.add(frogImageRightKey);

				String frogImageDownKey = frogElement.getElementsByTagName("ImageDown").item(0).getFirstChild().getTextContent();
				addImageToMap(frogImageDownKey);
				frogImageKeys.add(frogImageDownKey);

				this.frog = new Frog(new Point(frogColumn * this.mapColumnWidth, (this.mapNumberOfRows - 2) * this.mapLaneHeight), frogImageKeys, this.imageMap.get(frogImageKeys.get(0)).getWidth(null));

				// Create the Lanes
				for (int laneIndex = 0; laneIndex < laneNodes.getLength(); laneIndex++) {
					// System.out.println("lane " + laneIndex + ":");
					Element curLane = (Element) laneNodes.item(laneIndex);
					String friendlyString = curLane.getElementsByTagName("IsFriendly").item(0).getFirstChild().getTextContent();
					boolean laneIsFriendly = Boolean.parseBoolean(friendlyString);

					// Create the Lane object and add it the the Array
					this.lanes[laneIndex] = new Lane(new ArrayList<Sprite>(), laneIsFriendly);

					// Find the enemies and add them to the Lane
					NodeList spriteList = curLane.getElementsByTagName("Sprite");
					for (int spriteIndex = 0; spriteIndex < spriteList.getLength(); spriteIndex++) {
						Element curSprite = (Element) spriteList.item(spriteIndex);

						String spriteType = curSprite.getElementsByTagName("Type").item(0).getFirstChild().getTextContent();
						// System.out.println(spriteType);
						int spriteXCoord = Integer.parseInt(curSprite.getElementsByTagName("XCoord").item(0).getFirstChild().getTextContent());
						int spriteYCoord = laneIndex * this.mapLaneHeight;
						int spriteSpeed = Integer.parseInt(curSprite.getElementsByTagName("Speed").item(0).getFirstChild().getTextContent());
						NodeList spriteImageNodes = curSprite.getElementsByTagName("Image");
						ArrayList<String> spriteImageKeys = new ArrayList<String>();
						for (int imageIndex = 0; imageIndex < spriteImageNodes.getLength(); imageIndex++) {
							String imageNameKey = spriteImageNodes.item(imageIndex).getFirstChild().getTextContent();
							addImageToMap(imageNameKey);
							spriteImageKeys.add(imageNameKey);
						}
						int spriteChangeTime = Integer.parseInt(curSprite.getElementsByTagName("ChangeTime").item(0).getFirstChild().getTextContent());

						if (spriteType.equals("Turtle")) {
							this.lanes[laneIndex].addSprite(new Turtle(new Point(spriteXCoord, spriteYCoord), spriteImageKeys, this.imageMap.get(spriteImageKeys.get(0)).getWidth(null), spriteSpeed, spriteChangeTime));
						} else if (spriteType.equals("Log")) {
							this.lanes[laneIndex].addSprite(new Log(new Point(spriteXCoord, spriteYCoord), spriteImageKeys, this.imageMap.get(spriteImageKeys.get(0)).getWidth(null), spriteSpeed, spriteChangeTime));
						} else if (spriteType.equals("Car")) {
							this.lanes[laneIndex].addSprite(new Enemy(new Point(spriteXCoord, spriteYCoord), spriteImageKeys, this.imageMap.get(spriteImageKeys.get(0)).getWidth(null), spriteSpeed, spriteChangeTime));
						} else if (spriteType.equals("LillyPad")) {
							this.lanes[laneIndex].addSprite(new LillyPad(new Point(spriteXCoord, spriteYCoord), spriteImageKeys, this.imageMap.get(spriteImageKeys.get(0)).getWidth(null)));
						}
					} // end Sprites
				} // end Lanes
				// Create the Sprites for the life icons
				String lifeImage = curNode.getElementsByTagName("LifeImage").item(0).getFirstChild().getTextContent();
				addImageToMap(lifeImage);
				for (int lifeIndex = 0; lifeIndex < this.startingLives; lifeIndex++) {
					ArrayList<String> lifeImageKeys = new ArrayList<String>();
					lifeImageKeys.add(lifeImage);
					lifeImageKeys.add("Transparent.png"); // The second image is the "Transparent" image
					int laneIndex = lanes.length - 1;
					this.lanes[laneIndex].addSprite(new Life(new Point(lifeIndex * 25, laneIndex * this.mapLaneHeight), lifeImageKeys, this.imageMap.get(lifeImage).getWidth(null)));
				}
			} // end Map
		} catch (Exception e) {
			//CSC150 Selected Requirement – 9.3 Exception propagation
			/*
			* I am propagating the exception to the calling class, since it can handle the error better than the Config class can.
			*/

			throw new ConfigurationException("Error reading config file:");
		}
	}

	/**
	 * Adds and image to the Map of images
	 * @param imageName	The name of the image, relative to the resourcePath set earlier
	 */
	private void addImageToMap(String imageName) {
		try {
			if (!this.imageMap.containsKey(imageName)) {
				String imagePath = this.resourcePath + imageName;
				BufferedImage bfImage = ImageIO.read(new File(imagePath));
				this.imageMap.put(imageName, bfImage);
			} else {
				//System.out.println("image already in map: " + imageName);
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + imageName);
			System.out.println(e.getMessage());
		}
	}

	public String getBackgroundImageKey() {
		return this.mapBackgroundImageKey;
	}

	public int getColumnWidth() {
		return this.mapColumnWidth;
	}

	public Map<String, Image> getImageMap() {
		return this.imageMap;
	}

	public Frog getFrog() {
		return this.frog;
	}

	public int getLaneHeight() {
		return this.mapLaneHeight;
	}

	public Lane[] getLanes() {
		return this.lanes;
	}

	public String getMapName() {
		return this.mapName;
	}

	public int getNumberOfColumns() {
		return this.mapNumberOfColumns;
	}

	public int getNumberOfRows() {
		return this.mapNumberOfRows;
	}
	
	public int getStartingLives() {
		return this.startingLives;
	}
}
