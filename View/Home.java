package View;

import Controller.Arbre;
import Model.HierarchieSelection;
import Model.ProgramSelection;
import Model.TabCreation;
import Model.XMLReader;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

public class Home {
    private XMLReader xml;
    private JFrame frame;
    private Tableau tab;
    private JDialog dialog;

    public Home(XMLReader xml) {
        this.xml=xml;
        frame = new JFrame("Projet PCOO");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1500,800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        setTabInit();
        setMenu();

        frame.setVisible(true);
    }

    private void setTabInit(){
        TabCreation tabCreation=new TabCreation(xml.getCourseList(),xml.getProgramList(), xml.getStudentList());
        tab=new Tableau(tabCreation);
        frame.add(tab.getPanel());
    }

    public void setTab(Tableau t){
        tab=t;
    }

    private void setMenu(){
        frame.add(new Menu(this).getMenuBar(),BorderLayout.NORTH);
    }

    public XMLReader getXml(){
        return xml;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Tableau getTab() {
        return tab;
    }

}
