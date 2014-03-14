package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The LoaderPanel displays all the levels that the User can use
 * @author Blake Rollins & Wyatt Reynolds
 *
 */
public class LoaderPanel extends JPanel {

	private static final long serialVersionUID = 8L;
	JFrame loaderFrame;
	ArrayList<File> levelArray;
	ActionListener loaderListener;

	/**
	 * The LoaderPanel requires a listener from the Controller class to notify what level to create
	 * @param lListener	The Listener from the Controller clas
	 */
	public LoaderPanel(ActionListener lListener) {

		
		this.levelArray = new ArrayList<File>();
		this.loaderListener = lListener;

		/**
		 * Get all the files in the current folder
		 */
		File currentFolder = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		File[] currentFolderFiles = currentFolder.listFiles();
		for (int fileIndex = 0; fileIndex < currentFolderFiles.length; fileIndex++) {
			// Split the file name into different parts so I can add only the xml files
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
		
		// TODO: dynamically set the preferredSize
		this.setPreferredSize(new Dimension(350, 520));

		// Add a button for each file
		for (File f : levelArray) {
			JButton levelButton = new JButton(f.getName());
			levelButton.addActionListener(new levelButtonListener());
			this.add(levelButton);
		}

		this.loaderFrame.setContentPane(this);
		this.loaderFrame.pack();
		this.loaderFrame.setVisible(true);
	}

	/**
	 * Pains all the Sprites on the panel
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
		// Paint the background
		g.drawImage(ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "TitleScreen.png")), 0, 0, null);
		} catch (Exception e) {
			
		}
	}

	/**
	 * Gets a reference for the Frame that the LoaderPanel is displayed on (For the controller class to set focus)
	 * @return	A reference to the Frame that the LoaderPanel lives in
	 */
	public JFrame getLoaderFrame() {
		return this.loaderFrame;
	}

	/**
	 * Listens for the button selection from the User
	 * @author Blake Rollins & Wyatt Reynolds
	 *
	 */
	private class levelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			loaderListener.actionPerformed(arg0);
		}

	}
}
