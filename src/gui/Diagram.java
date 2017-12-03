package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;

import com.umldiagram.util.*;
import com.umldiagram.test.*;
import com.umldiagram.model.*;
import com.umldiagram.model.Class;

public class Diagram extends JPanel implements MouseListener, MouseMotionListener,
        MouseWheelListener, KeyListener{
    private Collection<ContainerStructure> structures = new ArrayList<>();
    private List<Relationship> relationships = new ArrayList<>();
    private HashMap<ContainerStructure, MyPanel> components = new LinkedHashMap<>();
    private MyPanel[] bounds = null;
    private JScrollPane scrollPane = null;
    private JPanel pressedComponent=null;
    private Point pressedLocation = null;
    private Point componentLocation = null;
    public boolean fixedStructure = true;
    private double scale = 1.0;

    public JavaProject p = (JavaProject) Project.getInstance();
    public Class.Builder cb = p.getClassBuilder();
    public Interface.Builder ib = p.getInterfaceBuilder();
    public Property.Builder pb = p.getPropertyBuilder();
    public Method.Builder mb = p.getMethodBuilder();

    public Diagram(){
        super();
        structures = p.getStructures();

        setLayout(null);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addKeyListener(this);

        scrollPane = new JScrollPane(this);
        int scrollUnit = 15;
        scrollPane.getVerticalScrollBar().setUnitIncrement(scrollUnit);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(scrollUnit);
        scrollPane.setWheelScrollingEnabled(false);
    }

    /**
    public boolean add(ContainerStructure structure){
        if (!structures.contains(structure)) {
            synchronized (structures){
                structures.add(structure);
            }
            return true;
        }
        return false;
    }
     */

    public void updateRelationships(){
        int n = structures.size();
        synchronized (relationships) {
            relationships.clear();
        }

        synchronized (structures) {
            synchronized (relationships) {
                Iterator<ContainerStructure> itr = structures.iterator();
                while (itr.hasNext()) {
                    ContainerStructure s = itr.next();
                    if (s instanceof Class){
                        Class c = (Class) s;
                        if (c.getSuperClass() != null)
                            relationships.add(new Relationship(getBoundsOf(c), getBoundsOf(c.getSuperClass())));

                        ContainerStructure[] interfaces = c.getInterfaces().toArray(new ContainerStructure[0]);
                        for (int j=0; j<interfaces.length; j++)
                            relationships.add(new Relationship(getBoundsOf(c), getBoundsOf(interfaces[j])));
                        continue;
                    }

                    Interface i = (Interface) s;
                    ContainerStructure[] interfaces = i.getSubInterfaces().toArray(new ContainerStructure[0]);
                    for (int j=0; j<interfaces.length; j++)
                        relationships.add(new Relationship(getBoundsOf(i), getBoundsOf(interfaces[j])));
                }
            }
        }
    }

    private void updateStructures(){
        synchronized (components) {
            components.clear();
        }

        synchronized (structures) {
            Iterator<ContainerStructure> it = structures.iterator();
            while (it.hasNext()){
                ContainerStructure s = it.next();
                components.put(s,new MyPanel(s));
            }
        }
        fixedStructure = false;
    }

    private MyPanel[] getAllBounds(){

        return components.values().toArray(new MyPanel[0]);
    }

    private MyPanel getBoundsOf(ContainerStructure s){
        if (components != null && s != null) return components.get(s);
        else return null;
    }

    private void setAllBounds(){
        if (components == null) return;
        int i=0;
        int s=0;
        int x = 0, y = 0;
        Iterator<Entry<ContainerStructure,MyPanel>> entries = components.entrySet().iterator();
        while (entries.hasNext()){
            Entry<ContainerStructure, MyPanel> entry = entries.next();
            MyPanel en_rect = entry.getValue();

            x= 50+ i*20 + s + i*50;
            y= 50;
            s+= en_rect.getWidth();
            if (en_rect.getX() == 0 && en_rect.getY() == 0) en_rect.setLocation(x,y);
            en_rect.addMouseMotionListener(this);
            en_rect.addMouseListener(this);
            i++;
        }
    }

    public void DrawAllBounds(){
        if (components == null) return;

        Iterator<Entry<ContainerStructure,MyPanel>> entries = components.entrySet().iterator();
        while (entries.hasNext()){
            Entry<ContainerStructure, MyPanel> entry = entries.next();
            MyPanel en_rect = entry.getValue();

            this.add(en_rect);
        }
    }

    public void clear(){
        this.removeAll();
        this.revalidate();
        synchronized (structures) {
            structures.clear();
        }
        synchronized (relationships) {
            relationships.clear();
        }
        synchronized (components) {
            components.clear();
        }
        p.removeAll();
        cb = p.getClassBuilder();
        ib = p.getInterfaceBuilder();
        pb = p.getPropertyBuilder();
        mb = p.getMethodBuilder();
        repaint();
    }

    public void DrawAllRelationphip(Graphics g){
        //g.setColor(Color.RED);
        for (int i=0; i<relationships.size(); i++) relationships.get(i).draw(g);
    }

    public void repaint(Graphics g){
        this.removeAll();
        this.revalidate();
        super.repaint();

        if (fixedStructure) {
            updateStructures();
            updateRelationships();
            setAllBounds();
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.scale(scale,scale);
        DrawAllBounds();
        DrawAllRelationphip(g);

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        repaint(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount()==2 && e.getSource()!=this && e.getButton()==MouseEvent.BUTTON1){
            Object s = e.getSource();
            if ((s instanceof MyPanel) && (components.containsValue(s))) {
                pressedComponent = (MyPanel) s;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() != this) {
            if (e.getButton()==MouseEvent.BUTTON1) {
                Object s = e.getSource();
                if (s instanceof MyPanel) {
                    pressedComponent = (MyPanel) s;
                    componentLocation = pressedComponent.getLocation();
                    pressedLocation = e.getLocationOnScreen();
                    requestFocus(); // prepare to use arrow keys later
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressedComponent = null;
        componentLocation = null;
        pressedLocation = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getSource()!=this){
            if (pressedComponent!=null) {
                int x = e.getLocationOnScreen().x - pressedLocation.x;
                int y = e.getLocationOnScreen().y - pressedLocation.y;
                pressedComponent.setLocation(componentLocation.x + x , componentLocation.y + y);
                repaint(getGraphics());
            }
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getSource()==this) {
            double delta = 0.05f * e.getPreciseWheelRotation();
            scale -= delta;
            if (scale>2) scale = 2;
            if (scale<0.60) scale = 0.60;
            revalidate();
            repaint();
        }
    }
}