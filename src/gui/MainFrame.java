package gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.umldiagram.model.Class;
import com.umldiagram.model.*;

public class MainFrame extends JFrame implements ActionListener{

    private Diagram diagram ;

    private JMenuBar menubar;
    private JToolBar toolbar;
    private JTree tree;
    private JScrollPane treeScrollPane;
    private Diagram grid = new Diagram();

    public void Init() throws StructureException {

        Method m = grid.mb.withName("amethod")
                .isAbstract(true)
                .withArgument(new Argument("x", "int"))
                .withReturnType("String")
                .withVisibility(Structure.Visibility.PUBLIC)
                .build();
        Property pp = grid.pb.withName("aproperty")
                .isStatic(true)
                .withVisibility(Structure.Visibility.PUBLIC)
                .withType("int")
                .build();
        Interface i = grid.ib.withName("AInterface")
                .withMethod(m)
                .withProperty(pp)
                .build();
        grid.p.add(i);

        Class c1 = grid.cb.withName("AClass1")
                .isAbstract(true)
                .implement(i)
                .build();
        Class c2 = grid.cb.withName("AClass2")
                .extend(c1)
                .build();

        grid.p.add(c1);
        grid.p.add(c2);
    }

    public void InitFrame(){
        createMenuBar();
        createToolBar();
        createJTree();
        createJPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(1200, 600));
        this.setLocationRelativeTo(null);
    }

    private void creatFileMenu() {
        // Create File Menu
        JMenu fileMenu = new JMenu("File");

        // File -> Clear
        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(this);
        clearItem.setActionCommand("CLEAR");
        fileMenu.add(clearItem);

        // File -> Fine
        JMenuItem findItem = new JMenuItem("Find");
        findItem.addActionListener(this);
        findItem.setActionCommand("FIND");
        fileMenu.add(findItem);

        // File ->  Import Source File
        JMenuItem importFileItem = new JMenuItem("Import Source File");
        importFileItem.addActionListener(this);
        importFileItem.setActionCommand("IMPORT");
        fileMenu.add(importFileItem);
        fileMenu.addSeparator();

        // File -> Export Source File
        JMenuItem exportFileItem = new JMenuItem("Export Source File");
        exportFileItem.addActionListener(this);
        exportFileItem.setActionCommand("EXPORT");
        fileMenu.add(exportFileItem);
        fileMenu.addSeparator();

        // File -> Save as Image
        JMenuItem saveImageItem = new JMenuItem("Save as Image");
        saveImageItem.addActionListener(this);
        saveImageItem.setActionCommand("IMAGE");
        fileMenu.add(saveImageItem);

        // File -> Exit
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(this);
        exitItem.setActionCommand("EXIT");
        fileMenu.add(exitItem);

        // Finish File Menu
        menubar.add(fileMenu);
    }

    private void createNewMenu(){
        JMenu newMenu = new JMenu("New");
        // New -> Class
        JMenuItem classItem = new JMenuItem("Class");
        classItem.addActionListener(this);
        classItem.setActionCommand("CLASS");
        newMenu.add(classItem);

        // New -> Interface
        JMenuItem interfaceItem = new JMenuItem("Interface");
        interfaceItem.addActionListener(this);
        interfaceItem.setActionCommand("INTERFACE");
        newMenu.add(interfaceItem);

        // New -> Property
        JMenuItem propertyItem = new JMenuItem("Property");
        propertyItem.addActionListener(this);
        propertyItem.setActionCommand("PROPERTY");
        newMenu.add(propertyItem);

        // New -> Method
        JMenuItem methodItem = new JMenuItem("Method");
        methodItem.addActionListener(this);
        methodItem.setActionCommand("METHOD");
        newMenu.add(methodItem);

        // New -> Argument
        JMenuItem argumentItem = new JMenuItem("Argument");
        argumentItem.addActionListener(this);
        argumentItem.setActionCommand("ARGUMENT");
        newMenu.add(argumentItem);

        // Finish File Menu
        menubar.add(newMenu);
    }

    private void createHelpMenu(){
        JMenu helpMenu = new JMenu("Help");
        menubar.add(helpMenu);
    }

    /**
     * Create a menu bar
     */
    private void createMenuBar() {
        menubar = new JMenuBar();
        creatFileMenu();
        createNewMenu();
        createHelpMenu();
        setJMenuBar(menubar);
    }

    /**
     * Create Tool bar
     */
    private void createToolBar() {
        JToolBar toolbar = new JToolBar();

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

    private DefaultMutableTreeNode ArgNode(Method me){
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(me);
        for (Argument arg : me.getArguments().toArray(new Argument[0]))
            root.add(new DefaultMutableTreeNode(arg));
        return root;
    }

    private DefaultMutableTreeNode makeNode(ContainerStructure s){
        DefaultMutableTreeNode root= new DefaultMutableTreeNode(s.getName());
        for (Property p : s.getProperties().toArray(new Property[0]))
            root.add(new DefaultMutableTreeNode(p));
        for (Method me : s.getMethods().toArray(new Method[0]))
            root.add(ArgNode(me));
        return root;
    }

    /**
     * Create a Tree
     */
    private void createJTree(){

        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        ContainerStructure[] s =  grid.p.getStructures().toArray(new ContainerStructure[0]);
        for (int i=0; i<s.length; i++)
            root.add(makeNode(s[i]));

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

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT); // create a split pane
        splitPane.setLeftComponent(treeScrollPane);
        splitPane.setRightComponent(grid);

        add(splitPane, BorderLayout.CENTER);
    }

    public MainFrame() throws StructureException{
        Init();
        InitFrame();

        this.setVisible(true);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            try {
                MainFrame main = new MainFrame();
                main.setVisible(true);
            } catch (StructureException e) {
                e.printStackTrace();
            }
        });
    }

    private void showImportDialog(){
        final JFileChooser fileDialog = new JFileChooser();
        fileDialog.setDialogTitle("Load Source Files");
        fileDialog.setApproveButtonText("Load");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Java Files", "java");
        FileNameExtensionFilter filter3 = new FileNameExtensionFilter("Zip Files", "zip");
        FileNameExtensionFilter filter4 = new FileNameExtensionFilter("WinRar Files", "rar");
        fileDialog.addChoosableFileFilter(filter);
        fileDialog.addChoosableFileFilter(filter2);
        fileDialog.addChoosableFileFilter(filter3);
        fileDialog.addChoosableFileFilter(filter4);
        fileDialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileDialog.setMultiSelectionEnabled(true);

        int returnValue = fileDialog.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File[] file = fileDialog.getSelectedFiles();
        }
    }

    private void showAddClassDialog(){
        final JPanel addingPanel = new JPanel();
        JTextField nameField = new JTextField(10);

        // add text field
        addingPanel.add(new JLabel("Name :"));
        addingPanel.add(nameField);
        addingPanel.add(Box.createHorizontalStrut(10)); // a spacer

        // add radio button
        JRadioButton defaultButton = new JRadioButton("default");
        JRadioButton publicButton = new JRadioButton("public");
        ButtonGroup modifier = new ButtonGroup();
        modifier.add(defaultButton);
        modifier.add(publicButton);
        addingPanel.add(defaultButton);
        addingPanel.add(publicButton);
        defaultButton.setSelected(true);

        // add check box
        JCheckBox abstractCheckbox = new JCheckBox("abstract");
        JCheckBox addmoreCheckbox = new JCheckBox("add more");
        addingPanel.add(abstractCheckbox);
        addingPanel.add(addmoreCheckbox);

        int result;
        while(true){
            result = JOptionPane.showConfirmDialog(null, addingPanel, "Enter class", JOptionPane.OK_CANCEL_OPTION);
            String name = nameField.getText();
            if (result == JOptionPane.OK_OPTION) {
                if(name.length() == 0){
                    JOptionPane.showMessageDialog(addingPanel,"Please enter class's name","Alert",JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                try {
                    Class c = grid.cb.withName(name)
                                         .build();
                    grid.p.add(c);
                    grid.fixedStructure = true;
                } catch (StructureException e1) {
                    JOptionPane.showConfirmDialog(addingPanel,e1.getMessage(),"Warning",JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                if (addmoreCheckbox.isSelected()) continue;
                break;
            }
            break;
        }
    }

    private void showAddInterfaceDialog(){
        final JPanel addingPanel = new JPanel();
        JTextField nameField = new JTextField(10);

        // add text field
        addingPanel.add(new JLabel("Name :"));
        addingPanel.add(nameField);
        addingPanel.add(Box.createHorizontalStrut(10)); // a spacer

        // add radio button
        JRadioButton defaultButton = new JRadioButton("default");
        JRadioButton publicButton = new JRadioButton("public");
        ButtonGroup modifier = new ButtonGroup();
        modifier.add(defaultButton);
        modifier.add(publicButton);
        addingPanel.add(defaultButton);
        addingPanel.add(publicButton);
        defaultButton.setSelected(true);

        // add check box
        JCheckBox addmoreCheckbox = new JCheckBox("add more");
        addingPanel.add(addmoreCheckbox);

        int result;
        while(true){
            result = JOptionPane.showConfirmDialog(null, addingPanel, "Enter interface", JOptionPane.OK_CANCEL_OPTION);
            String name = nameField.getText();
            if (result == JOptionPane.OK_OPTION) {
                if(name.length() == 0){
                    JOptionPane.showMessageDialog(addingPanel,"Please enter interface's name","Alert",JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                try {
                    Interface i = grid.ib.withName(name)
                                         .build();
                    grid.p.add(i);
                    grid.fixedStructure = true;
                } catch (StructureException e1) {
                    JOptionPane.showConfirmDialog(addingPanel,e1.getMessage(),"Warning",JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                if (addmoreCheckbox.isSelected()) continue;
                break;
            }
            break;
        }
    }

    private void showClearDialog(){
        if (JOptionPane.showConfirmDialog(this,"Are you sure you want to clear the Diagram?\nThis action can not be undone!",
                "WARNING!!!",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            grid.clear();
        }
    }

    private void addMethodTo(Method me, ContainerStructure s){
        if (s instanceof Class){
            try {
                Class c = grid.cb.withName(s.getName())
                        .withProperties(s.getProperties())
                        .withMethods(s.getMethods())
                        .withMethod(me)
                        .build();
                grid.p.replace((Class) s,c);
            } catch (StructureException e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            Interface i = grid.ib.withName(s.getName())
                    .withProperties(s.getProperties())
                    .withMethods(s.getMethods())
                    .withMethod(me)
                    .build();
        } catch (StructureException e) {
            e.printStackTrace();
        }
    }

    private void addMethodTo(ContainerStructure s){
        if (s==null) return;

        final JPanel addPanel = new JPanel();
        JTextField nameField = new JTextField(10);
        JTextField typeField = new JTextField(10);

        // add text field
        addPanel.add(new JLabel("Name :"));
        addPanel.add(nameField);
        addPanel.add(Box.createHorizontalStrut(5)); // a spacer
        addPanel.add(new JLabel("Type :"));
        addPanel.add(typeField);
        addPanel.add(Box.createHorizontalStrut(10)); // a spacer

        // add radio button
        JRadioButton defaultButton = new JRadioButton("default");
        JRadioButton publicButton = new JRadioButton("public");
        JRadioButton privateButton = new JRadioButton("private");
        JRadioButton protectedButton = new JRadioButton("protected");
        ButtonGroup modifier = new ButtonGroup();
        modifier.add(defaultButton);
        modifier.add(publicButton);
        modifier.add(privateButton);
        modifier.add(protectedButton);
        addPanel.add(defaultButton);
        addPanel.add(publicButton);
        addPanel.add(privateButton);
        addPanel.add(protectedButton);
        defaultButton.setSelected(true);

        // add check box
        JCheckBox abstractCheckbox = new JCheckBox("abstract");
        JCheckBox staticCheckbox = new JCheckBox("static");
        JCheckBox addmoreCheckbox = new JCheckBox("add more");
        addPanel.add(abstractCheckbox);
        addPanel.add(staticCheckbox);
        addPanel.add(addmoreCheckbox);

        Method me = null;
        int result;
        boolean addmore = true;
        while(true){
            if (addmore){
                nameField.setText("");
                typeField.setText("");
                addmore = false;
            }

            result = JOptionPane.showConfirmDialog(null, addPanel, "Adding Method to "+s.getName(), JOptionPane.OK_CANCEL_OPTION);
            String name = nameField.getText();
            String type = typeField.getText();
            if (result == JOptionPane.OK_OPTION) {
                if(name.length() == 0){
                    JOptionPane.showMessageDialog(addPanel,"Please enter class's name","Alert",JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                if(type.length() == 0){
                    JOptionPane.showMessageDialog(addPanel,"Please enter class's type","Alert",JOptionPane.WARNING_MESSAGE);
                    continue;
                }

                try {
                    me = grid.mb.withName(name)
                                .withReturnType(type)
                                .isAbstract(abstractCheckbox.isSelected())
                                .isStatic(staticCheckbox.isSelected())
                                .build() ;
                    grid.fixedStructure = true;
                } catch (StructureException e1) {
                    JOptionPane.showConfirmDialog(addPanel,e1.getMessage(),"Warning",JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                if (s instanceof Class)
                    if (me.isAbstract()!=((Class) s).isAbstract()) {
                        String message = "Class "+s.getName()+" is ";
                        message+= ((Class) s).isAbstract()? "abstract" : "non-abstract";
                        JOptionPane.showConfirmDialog(addPanel,message,"Warning",JOptionPane.WARNING_MESSAGE);
                        continue;
                    }
                if (s instanceof Interface)
                    if (!me.isAbstract()){
                        JOptionPane.showConfirmDialog(addPanel,me.getName()+" must to be abstract","Warning",JOptionPane.WARNING_MESSAGE);
                        continue;
                    }

                addMethodTo(me,s);

                if (addmoreCheckbox.isSelected()) {
                    addmore=true;
                    continue;
                }
                break;
            }
            break;
        }

    }

    private void showAddMethodDialog(){
        final JPanel addPanel = new JPanel();

        // add text field
        addPanel.add(new JLabel("Class/Interface :"));
        ContainerStructure[] s = grid.p.getStructures().toArray(new ContainerStructure[0]);
        JList<String> list = new JList(s);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 20));

        addPanel.add(listScroller);
        addPanel.add(Box.createHorizontalStrut(5)); // a spacer

        String result = JOptionPane.showInputDialog(this, "Class/Interface: ","New Method",JOptionPane.QUESTION_MESSAGE,
                                                    null, s,s[0]).toString();

        addMethodTo(grid.p.getClassByName(result));
        addMethodTo(grid.p.getInterfaceByName(result));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("EXIT")) {
            System.exit(0);
        }
        if (command.equals("CLEAR")) {
            showClearDialog();
        }
        if (command.equals("FIND")) {
            System.out.println(command);
        }
        if (command.equals("IMPORT")) {
            showImportDialog();
        }
        if (command.equals("EXPORT")) {
            System.out.println(command);
        }
        if (command.equals("IMAGE")) {
            System.out.println(command);
        }
        if (command.equals("CLASS")) {
            showAddClassDialog();
        }
        if (command.equals("INTERFACE")) {
            showAddInterfaceDialog();
        }
        if (command.equals("METHOD")) {
            showAddMethodDialog();
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