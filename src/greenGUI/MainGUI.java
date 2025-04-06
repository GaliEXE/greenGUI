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
		
		Color randomGreen = generateGreen();
		
		dateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDateTime now = LocalDateTime.now();
				dateFld.setText("Current Date And Time: " + now);
			}
		});
		
		logBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					FileWriter writer = new FileWriter("log.txt");
					
					String textInput = dateFld.getText();
					writer.write(textInput);
					
					writer.close();
					
				} catch (IOException e1) {
					System.out.println("There Was A Problem Writing To File, " + e1);
				}
			}
		});
		
		greenBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuPnl.setBackground(randomGreen);
				barPnl.setBackground(randomGreen);
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
	
	private static Color generateGreen() {
		Random num = new Random();
		int green = 100 + num.nextInt(156);
		int red = num.nextInt(50);
		int blue = num.nextInt(50);
		return new Color(red, green, blue);
	}
	
	
}
