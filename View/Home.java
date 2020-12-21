package View;

import Model.TabCreation;
import Model.XMLReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class Home {
    private XMLReader xml;
    private JFrame frame;
    private Tableau tab;

    public Home(XMLReader xml) {
        this.xml=xml;
        frame = new JFrame("Projet PCOO");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1500,800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        setTab();
        setMenu();

        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    private void setTab(){
        TabCreation tabCreation=new TabCreation(xml);
        tab=new Tableau(tabCreation);
        frame.add(tab.getPanel());
    }

    private void setMenu(){
        frame.add(new Menu().getMenuBar(),BorderLayout.NORTH);
    }

}
