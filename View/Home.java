package View;

import Model.Etudiant;
import Model.TabCreation;
import Model.XMLReader;

import javax.swing.*;
import java.awt.*;

public class Home {
    private  JFrame frame;
    private  Tableau tab;

    public Home() {
        frame = new JFrame("Projet PCOO");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1500,800);
        //frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setTab(XMLReader xml){
        TabCreation tabCreation=new TabCreation(xml);
        tab=new Tableau(tabCreation);
        frame.add(tab.getPanel());
        frame.setVisible(true);
    }

}
