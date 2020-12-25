package View;

import Model.TabCreation;
import Model.XMLReader;

import javax.swing.*;
import java.awt.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

public class Home {
    private XMLReader xml;
    private JFrame frame;
    private Tableau tab;

    public Home(XMLReader xml) {
        this.xml = xml;
        frame = new JFrame("Projet PCOO");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1445, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        setTabInit();
        setMenu();

        frame.setVisible(true);

    }

    private void setTabInit() {
        TabCreation tabCreation = new TabCreation(xml.getCourseList(), xml.getProgramList(), xml.getStudentList());
        tab = new Tableau(tabCreation);
        frame.add(tab.getPanel());
    }

    public void setTab(Tableau t) {
        tab = t;
    }

    private void setMenu() {
        frame.add(new Menu(this).getMenuBar(), BorderLayout.NORTH);
    }

    public XMLReader getXml() {
        return xml;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Tableau getTab() {
        return tab;
    }

}
