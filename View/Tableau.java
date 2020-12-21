package View;

import Model.Etudiant;
import Model.TabCreation;
import Model.XMLReader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Tableau {
    private JTable tableau;
    private JTable calculs;
    private JScrollPane PART1;
    private JScrollPane PART2;
    private JPanel Panel;

    public Tableau(TabCreation tab){
        String[] colones = tab.getColones();
        String[][] lignes = tab.getLignes();
        String[][] scores = tab.getScores();
        tableau=new JTable(lignes,colones);
        calculs=new JTable(scores,colones);
        setScrollBar();
        setLayout();
    }

    private void setScrollBar() {
        PART1= new JScrollPane(tableau);
        tableau.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        PART2= new JScrollPane(calculs);
        calculs.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        PART1.getHorizontalScrollBar().setModel(PART2.getHorizontalScrollBar().getModel());
        PART1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private void setLayout(){
        Panel=new JPanel();
        Panel.setLayout(new BorderLayout());
        Panel.add(PART1);
        Panel.add(PART2,BorderLayout.SOUTH);
    }

    public JTable getTab(){
        return tableau;
    }

    public JTable getCalculs() {
        return calculs;
    }

    public JScrollPane getPART1() {
        return PART1;
    }

    public JScrollPane getPART2() {
        return PART2;
    }

    public JPanel getPanel() {
        return Panel;
    }
}
