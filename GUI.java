import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/*
 * GUI class
 */
public class GUI extends JFrame implements ActionListener{
	JMenuBar menubar = new JMenuBar(); // create a menu bar
	JToolBar toolbar = new JToolBar(); // create a tool bar
	JTree tree; // create a tree
	JScrollPane treeScrollPane; // create a ScrollPane for tree
	/*
	 * GUI Constructor
	 */
    public GUI() {

        initUI();
    }

    /**
     * Initialize GUI
     */
    private void initUI() {
        
        createMenuBar();
        createToolBar();
        createJTree();
        createJPanel();

        setTitle("Simple menu");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Create a menu bar
     */
    private void createMenuBar() {
    	// Create Menu
        JMenu fileMenu = new JMenu("File");
        JMenu newMenu = new JMenu("New");
        JMenu helpMenu = new JMenu("Help");
        
        // Create children in menu
        JMenuItem clearItem = new JMenuItem("Clear");
        JMenuItem findItem = new JMenuItem("Find");
        JMenuItem importFileItem = new JMenuItem("Import Source File");
        JMenuItem exportFileItem = new JMenuItem("Export Source File");
        JMenuItem saveImageItem = new JMenuItem("Save as Image");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        JMenuItem classItem = new JMenuItem("Class");
        JMenuItem interfaceItem = new JMenuItem("Interface");
        JMenuItem fieldItem = new JMenuItem("Property");
        JMenuItem methodItem = new JMenuItem("Method");
        JMenuItem argumentItem = new JMenuItem("Argument");
        
        
        // add action for "Exit"
        exitItem.addActionListener(this);
        exitItem.setActionCommand("EXIT");
        // add action for "Clear"
        clearItem.addActionListener(this);
        clearItem.setActionCommand("CLEAR");
        // add action for "Import"
        importFileItem.addActionListener(this);
        importFileItem.setActionCommand("IMPORT");
        // add action for "Export"
        exportFileItem.addActionListener(this);
        exportFileItem.setActionCommand("EXPORT");
        // add action for "Image"
        saveImageItem.addActionListener(this);
        saveImageItem.setActionCommand("IMAGE");
        
        // add action for "Class"
        classItem.addActionListener(this);
        classItem.setActionCommand("CLASS");
        // add action for "Interface"
        interfaceItem.addActionListener(this);
        interfaceItem.setActionCommand("INTERFACE");
        // add action for "Property"
        fieldItem.addActionListener(this);
        fieldItem.setActionCommand("PROPERTY");
        // add action for "Method"
        methodItem.addActionListener(this);
        methodItem.setActionCommand("METHOD");
        // add action for "Argument"
        argumentItem.addActionListener(this);
        argumentItem.setActionCommand("ARGUMENT");

        // add children items for "File" menu
        fileMenu.add(clearItem);
        fileMenu.add(findItem);
        fileMenu.add(importFileItem);
        fileMenu.addSeparator();
        fileMenu.add(exportFileItem);
        fileMenu.addSeparator();
        fileMenu.add(saveImageItem);
        fileMenu.add(exitItem);
        
        // add children items for "New" menu
        newMenu.add(classItem);
        newMenu.add(interfaceItem);
        newMenu.add(fieldItem);
        newMenu.add(methodItem);
        newMenu.add(argumentItem);
        
        // add menu to the menu bar
        menubar.add(fileMenu);
        menubar.add(newMenu);
        menubar.add(helpMenu);

        // show menu bar
        setJMenuBar(menubar);
    }
    /**
     * Create Tool bar
     */
    private void createToolBar() {
    	
        JButton zoominButton = new JButton("Zoom in"); // create zoom in button
        JButton zoomoutButton = new JButton("Zoom out"); // create zoom out button
        JButton deleteButton = new JButton("Delete"); // create delete button
        
        // add buttons to the tool bar
        toolbar.addSeparator();
        toolbar.add(zoominButton);
        toolbar.add(zoomoutButton);
        toolbar.addSeparator();
        toolbar.add(deleteButton);
        
        // add action for "Zoom in"
        zoominButton.addActionListener(this);
        zoominButton.setActionCommand("ZOOM_IN");	
        // add action for "Zoom out"
        zoomoutButton.addActionListener(this);
        zoomoutButton.setActionCommand("ZOOM_OUT");
        // add action for "Delete"
        deleteButton.addActionListener(this);
        deleteButton.setActionCommand("DELETE");

        add(toolbar, BorderLayout.NORTH);
    }
    /**
     * Create a Tree
     */
    private void createJTree(){
    	 
        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        
        //create children node
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        vegetableNode.add(new DefaultMutableTreeNode("Capsicum"));
        vegetableNode.add(new DefaultMutableTreeNode("Carrot"));
        vegetableNode.add(new DefaultMutableTreeNode("Tomato"));
        vegetableNode.add(new DefaultMutableTreeNode("Potato"));
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
        fruitNode.add(new DefaultMutableTreeNode("Banana"));
        fruitNode.add(new DefaultMutableTreeNode("Apple"));
        fruitNode.add(new DefaultMutableTreeNode("Grapes"));
        fruitNode.add(new DefaultMutableTreeNode("Orange"));
        
        //create the tree by passing in the root node
        root.add(vegetableNode);
        root.add(fruitNode);
        
        // create a Tree
        tree = new JTree(root);
        
        // create a scroll pane for tree
        treeScrollPane = new JScrollPane(tree);
        treeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        treeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    }
    
    /**
     * Create Panel
     */
    private void createJPanel(){

        JComponent grid = new JPanel(); // create right panel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT); // create a split pane
        splitPane.setLeftComponent(treeScrollPane);
        splitPane.setRightComponent(grid);
        
    	grid.setBackground(Color.gray);
        add(splitPane, BorderLayout.CENTER);
    }
    
    /*
     * Main
     */
    public static void main(String[] args) {
    	
    	// show Window to the screen
        EventQueue.invokeLater(() -> {
            GUI ex = new GUI();
            ex.setVisible(true);
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("EXIT")) {
			System.exit(0);
		}
		if (command.equals("CLEAR")) {
			System.exit(0);
		}
		if (command.equals("FIND")) {
			System.exit(0);
		}
		if (command.equals("IMPORT")) {
			System.exit(0);
		}
		if (command.equals("EXPORT")) {
			System.exit(0);
		}
		if (command.equals("IMAGE")) {
			System.exit(0);
		}
		if (command.equals("CLASS")) {
			System.exit(0);
		}
		if (command.equals("INTERFACE")) {
			System.exit(0);
		}
		if (command.equals("METHOD")) {
			System.exit(0);
		}
		if (command.equals("PROPERTY")) {
			System.exit(0);
		}
		if (command.equals("ARGUMENT")) {
			System.exit(0);
		}
		if (command.equals("ZOOM_IN")) {
			System.exit(0);
		}
		if (command.equals("ZOOM_OUT")) {
			System.exit(0);
		}
		if (command.equals("DELETE")) {
			System.exit(0);
		}	
	}
}