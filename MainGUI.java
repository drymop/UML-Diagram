package com.umldiagram.ui;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MainGUI extends JPanel {
	private JButton button;
	private JMenuBar menuBar;

	
	public MainGUI(ActionListener buttonListener) {
		initComponents();
		button.addActionListener(buttonListener);
	}
	
	private void initComponents() {
		button = new JButton("Click me");
		add(button);
		menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu newMenu = new JMenu("New");
		menuBar.add(fileMenu);
		menuBar.add(newMenu);
		
		add(menuBar);
	}
}
