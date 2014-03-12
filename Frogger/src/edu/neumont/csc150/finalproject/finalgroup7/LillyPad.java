package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;
import java.util.ArrayList;

public class LillyPad extends Friendly {

	public enum lillypad_completed {
		NOT_COMPLETED, // = 0
		COMPLETED // = 1
	}

	public LillyPad(Point startPosition, ArrayList<String> images, int width) {
		super(startPosition, images, width);
	}

}
