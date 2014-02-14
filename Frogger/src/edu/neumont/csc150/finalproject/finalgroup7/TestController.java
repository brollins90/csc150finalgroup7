package edu.neumont.csc150.finalproject.finalgroup7;

import javax.swing.JFrame;

public class TestController {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		frame.setContentPane(new TestPanel());
		
		frame.pack();
		frame.setVisible(true);
	}
}
