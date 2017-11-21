package com.umldiagram.ui;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	private JFrame frame;
	
	public void createAndShowGui() {
		frame = new JFrame("tuan");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ActionListener al = e -> System.out.println("Hello");
		frame.add(new MainGUI(al));
		
		frame.setSize(new Dimension(2000, 1000));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
  		SwingUtilities.invokeLater(() -> main.createAndShowGui());
	}
	
}
