package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoaderPanel extends JPanel {

	JFrame loaderFrame;
	ArrayList<File> levelArray;
	ActionListener loaderListener;

	public LoaderPanel(ActionListener lListener) {

		this.levelArray = new ArrayList<File>();
		this.loaderListener = lListener;

		File currentFolder = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		File[] currentFolderFiles = currentFolder.listFiles();
		for (int fileIndex = 0; fileIndex < currentFolderFiles.length; fileIndex++) {
			File currentFile = currentFolderFiles[fileIndex];
			String filePath = currentFile.getAbsolutePath();
			int extIndex = filePath.lastIndexOf('.');
			String extension = filePath.substring(extIndex + 1);
			if (extension.toLowerCase().equals("xml")) {
				levelArray.add(currentFile);
			}
		}

		this.loaderFrame = new JFrame("Super Frogger");
		this.loaderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.loaderFrame.setLocationRelativeTo(null);

		this.setFocusable(true);

		this.setPreferredSize(new Dimension(350, 600));

		for (File f : levelArray) {
			JButton levelButton = new JButton(f.getName());
			levelButton.addActionListener(new levelButtonListener());
			this.add(levelButton);
		}

		this.loaderFrame.setContentPane(this);
		this.loaderFrame.pack();
		this.loaderFrame.setVisible(true);
	}
	
	public JFrame getLoaderFrame() {
		return this.loaderFrame;
	}

	private class levelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//System.out.println("Level Butt list: " + ((JButton) arg0.getSource()).getText());
			loaderListener.actionPerformed(arg0);
		}

	}
}
