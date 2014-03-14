package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;
import java.util.ArrayList;

/**
 * A Message that is a Sprite displayed at the end of the game
 * 
 * @author Blake Rollins & Wyatt Reynolds
 */
public class Message extends Friendly {
	
	public enum MessageType {
		NONE, WIN, LOSE;
	}

	public Message(Point startPosition, ArrayList<String> images, int width) {
		super(startPosition, images, width);
	}

}
