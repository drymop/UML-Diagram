package gui;

import com.umldiagram.model.ContainerStructure;
import com.umldiagram.model.Method;
import com.umldiagram.model.Property;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    private ContainerStructure structure = null;
    private int x,y,w,h,h1,h2;

    public MyPanel(){}

    public MyPanel(ContainerStructure s){
        structure = s;
        h= 28;
        w = structure.getName().length()*8 + 10;
        h1 = 0; h2=0;

        Property[] pros = structure.getProperties().toArray(new Property[0]);
        for (int j=0; j<pros.length; j++) {
            w = Math.max(w, (pros[j].getName().length() + pros[j].getType().length()+2)*8);
            h1+= 23;
        }

        Method[] methods = structure.getMethods().toArray(new Method[0]);
        for (int j=0; j<methods.length; j++) {
            w = Math.max(w, methods[j].toString().length()*8);
            h2+= 23;
        }

        this.setSize(new Dimension(w+1,h+h1+h2+1));
    }

    public void setStructure(ContainerStructure s){
        structure = s;
    }

    public ContainerStructure getStructure() {
        return structure;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        x=0; y=0;

        g.setColor(Color.PINK);
        g.fillRect(x,y,w,h);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,w,h);
        g.drawRect(x,y+h,w,h1);
        g.drawRect(x,y+h+h1,w,h2);
        g.drawString(structure.getName(), x+5, y+19);
        g.drawString(structure.getName(), x+6, y+19);

        Property[] pros = structure.getProperties().toArray(new Property[0]);
        for (int j=0; j<pros.length; j++) {
            y+= 23;
            g.drawString(pros[j].getType() + "  " + pros[j].getName(),x+5,y+19);
        }
        Method[] methods = structure.getMethods().toArray(new Method[0]);
        for (int j=0; j<methods.length; j++) {
            y+= 23;
            g.drawString(methods[j].toString(),x+5,y+19);
        }
    }
}