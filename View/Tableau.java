package View;

import Model.TabCreation;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

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
        setStyle();
        setEdition();
    }

    private void setScrollBar() {
        PART1= new JScrollPane(tableau);
        tableau.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        PART2= new JScrollPane(calculs);
        calculs.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        PART1.getHorizontalScrollBar().setModel(PART2.getHorizontalScrollBar().getModel());
        PART1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        PART2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        calculs.getTableHeader().setUI(null);
    }

    private void setLayout(){
        Panel=new JPanel();
        Panel.setLayout(new GridBagLayout());
        calculs.setPreferredScrollableViewportSize(new Dimension((int)calculs.getPreferredScrollableViewportSize().getWidth(),calculs.getRowCount()*calculs.getRowHeight()));
        PART2.setMinimumSize(new Dimension((int)calculs.getPreferredScrollableViewportSize().getWidth(),calculs.getRowHeight()*(calculs.getRowCount()+1)));

        GridBagConstraints c=new GridBagConstraints();
        c.weighty=1;
        c.weightx=1;
        c.fill=GridBagConstraints.BOTH;
        Panel.add(PART1,c);
        c.gridy=1;
        c.weighty=0;
        Panel.add(PART2,c);
    }

    private void setStyle(){
        JTableHeader header=tableau.getTableHeader();
        header.setForeground(new Color(153, 31, 0));
        header.setFont(header.getFont().deriveFont(Font.BOLD));
        calculs.setBackground(header.getBackground());
        //TODO soit id nom prenom une autre couleur soit une couleur differente une colone sur deux
        //TODO rendre plus jolie le tableau calcul
    }

    private void setEdition(){
        calculs.setCellSelectionEnabled(true);
        calculs.setDefaultEditor(Object.class, null);
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
