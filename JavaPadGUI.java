import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * 
 * @author Eashune Abenojar
 * 
 * @author Bogdan Gavrilyuk
 *
 */

public class JavaPadGUI extends JFrame {
	// Area to Write
	public JTextArea textArea;

	// Text Field / Text Label
	public JTextField textField;
	public JLabel slogan;

	// Buttons
	public JButton newButton, saveButton, loadButton, quitButton;

	// TextFile
	private static final File textFile = new File("hardcode.txt");
	private static final String text = " ";
	private static final String filename = "Microsoft JavaPad XP";

	/*
	 * Default constructor
	 */
	public JavaPadGUI() {
		// Slogan
		slogan = new JLabel("Macrosoft: Resistance is futile.", JLabel.CENTER);

		// TextArea
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		// Adds scrolling to textArea
		JScrollPane scrollPane = new JScrollPane(textArea);

		// Button Panel
		JPanel buttonPanel = new JPanel();
		newButton = new JButton("New");
		saveButton = new JButton("Save");
		loadButton = new JButton("Load");
		quitButton = new JButton("Quit");

		// Button action listener
		ButtonPressedListener buttonListener = new ButtonPressedListener();

		// Attached the action listener to the buttons & will go to method
		// "actionPreformed"
		newButton.addActionListener(buttonListener);
		saveButton.addActionListener(buttonListener);
		loadButton.addActionListener(buttonListener);
		quitButton.addActionListener(buttonListener);
		buttonPanel.add(newButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(loadButton);
		buttonPanel.add(quitButton);

		// JFrame Layout
		this.setLayout(new BorderLayout());

		add(slogan, BorderLayout.SOUTH);
		add(scrollPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.NORTH);

		// Closes Application when "X" is pressed
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Frame Info
		setPreferredSize(new Dimension(600, 500));
		setTitle("Macrosoft JavaPad XP");
		// DON'T REMOVE "PACK()" THIS HOLDS THE DIMENSION
		pack();
		setVisible(true);
	}

	// Gets called by JavaPadGUI
	class ButtonPressedListener implements ActionListener {
		/**
		 * this class implements the ActionListener interface
		 * 
		 * @param "Button"
		 */
		@Override
		public void actionPerformed(ActionEvent Button) {
			JButton pressed = (JButton) Button.getSource();

			if (pressed.equals(loadButton)) {// Load Section
				/**
				 * this @param Shows a confirmation box
				 */
				int response = JOptionPane.showConfirmDialog(null, "Would you like to Load the file?", "Load",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					File file = new File("hardcode.txt");
					BufferedReader sc = null;
					// If statement IF "hardcode.txt" exists
					if (file.exists()) {
						try {
							textArea.setText("");
							sc = new BufferedReader(new FileReader("hardcode.txt"));
							Scanner scan = new Scanner(new File("hardcode.txt"));
							ArrayList<String> list = new ArrayList<String>();
							while (scan.hasNextLine()) {
								String word = scan.nextLine();
								list.add(word);
							}
							for (int i = 0; i < list.size(); i++) {
								System.out.print(list.get(i));
								textArea.append(list.get(i));
								textArea.append("\n");
							}
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							try {
								sc.close();
							} catch (Exception ex) {
							}
						}

					} else {
						/**
						 * this @param Shows an I/O Error box
						 */
						final JPanel error = new JPanel();
						JOptionPane.showMessageDialog(error, "Could not access file hardcode.txt", "I/O Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				if (response == JOptionPane.NO_OPTION) {
					// Leave Empty
				}

			} else if (pressed.equals(saveButton)) { // Save Section
				/**
				 * this @param Shows a confirmation box
				 */
				int response = JOptionPane.showConfirmDialog(null, "Would you like to save the file?", "Save",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					File file = new File("hardcode.txt");
					String str = textArea.getText();
					BufferedWriter writer = null;
					// If statement IF "hardcode.txt" exists
					if (file.exists()) {
						try {
							PrintWriter print = new PrintWriter(file);
							String textInWindow = textArea.getText();
							System.out.println(textInWindow);
							print.write(textInWindow);
							print.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						/**
						 * this @param Shows an I/O Error box
						 */
						final JPanel error = new JPanel();
						JOptionPane.showMessageDialog(error, "Could not access file hardcode.txt", "I/O Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				if (response == JOptionPane.NO_OPTION) {
					// Leave Empty
				}
			} else if (pressed.equals(quitButton)) { // Quit Section
				// Shows a confirmation box
				int response = JOptionPane.showConfirmDialog(null, "Quitting; Save?", "Quit", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					File file = new File("hardcode.txt");
					String str = textArea.getText();
					BufferedWriter writer = null;

					// If statement IF "hardcode.txt" exists
					if (file.exists()) {
						try {
							PrintWriter print = new PrintWriter(file);
							String textInWindow = textArea.getText();
							System.out.println(textInWindow);
							print.write(textInWindow);
							print.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						// Shows an I/O Error box
						final JPanel error = new JPanel();
						JOptionPane.showMessageDialog(error, "Could not access file hardcode.txt", "I/O Error",
								JOptionPane.ERROR_MESSAGE);
					}
					System.exit(0);
				}
				if (response == JOptionPane.NO_OPTION) {
					System.exit(0);
				}

			} else if (pressed.equals(newButton)) { // New Section
				// Shows a confirmation box
				int response = JOptionPane.showConfirmDialog(null, "Would you like to start a new file?", "New",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					System.out.println("YES_OPTION");
					System.out.println("Cleared");
					// clear the text area
					textArea.setText("");
				}
				if (response == JOptionPane.NO_OPTION) {
					// Leave Empty
				}

			} else {
				System.out.println("Invalid Option");
			}
		}
	}

	public static void main(String[] args) {
		// Posts JavaPadGUI
		new JavaPadGUI();
	}

}
