package greenGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MainGUI {

	public static void main(String[] args) {

		JFrame mainGUI = new JFrame();
		mainGUI.setSize(600, 600);
		mainGUI.setTitle("Random Green");
		mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel container = new JPanel();
		JPanel menuPnl = new JPanel();
		JPanel barPnl = new JPanel();

		menuPnl.setLayout(new FlowLayout());
		menuPnl.setBorder(BorderFactory.createBevelBorder(0));
		menuPnl.setMaximumSize(new Dimension(600, 200));

		JButton dateBtn = new JButton("Date & Time");
		JButton logBtn = new JButton("Save Date/Time");
		JButton greenBtn = new JButton("Random Green");
		JButton exitBtn = new JButton("Exit");

		menuPnl.add(dateBtn);
		menuPnl.add(logBtn);
		menuPnl.add(greenBtn);
		menuPnl.add(exitBtn);

		barPnl.setLayout(new FlowLayout());
		JTextField dateFld = new JTextField(20);
		dateFld.setHorizontalAlignment(SwingConstants.CENTER);
		dateFld.setEditable(false);
		barPnl.add(dateFld, BorderLayout.CENTER);

		/**
		 * Generates and stores the random green color by calling the generateGreen()
		 * method
		 */
		Color randomGreen = generateGreen();

		dateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayTime(dateFld);
			}
		});

		logBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logTime(dateFld);
			}
		});

		greenBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setRandomGreen(barPnl, menuPnl, randomGreen);
			}
		});

		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		container.add(menuPnl);
		container.add(barPnl);
		mainGUI.add(container);

		mainGUI.setVisible(true);

	}

	/**
	 * This method returns a random shade of green by randomizing high levels of
	 * green and randomizing low levels of blue and red. It Then Returns the RGB
	 * value as a Color variable to be stored to ensure that the color only changes
	 * once when the colorBtn is pressed.
	 * 
	 * @return random green color.
	 */
	private static Color generateGreen() {
		Random num = new Random();
		int green = 100 + num.nextInt(156);
		int red = num.nextInt(50);
		int blue = num.nextInt(50);
		return new Color(red, green, blue);
	}

	/**
	 * Method To Write The Current Date And Time To The Text Field
	 * 
	 * @param dateFld
	 */
	public static void displayTime(JTextField dateFld) {
		LocalDateTime now = LocalDateTime.now();
		dateFld.setText("Current Date And Time: " + now);
	}

	/**
	 * Method To Save The Current Date And Time In The Text Field To The "log.txt"
	 * file. Will Create "log.txt" If It Doesn't Already Exist
	 * 
	 * @param dateFld
	 */
	public static void logTime(JTextField dateFld) {
		try {
			FileWriter writer = new FileWriter("log.txt");

			String textInput = dateFld.getText();
			writer.write(textInput);

			writer.close();

		} catch (IOException e1) {
			System.out.println("There Was A Problem Writing To File, " + e1);
		}
	}

	/**
	 * Method To Set The menePnl and barPnl to a random green color which is
	 * dictated by the randomGreen variable.
	 * 
	 * @param menuPnl
	 * @param barPnl
	 * @param randomGreen
	 */
	public static void setRandomGreen(JPanel menuPnl, JPanel barPnl, Color randomGreen) {
		menuPnl.setBackground(randomGreen);
		barPnl.setBackground(randomGreen);
	}

}
