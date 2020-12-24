package Controller;

import Model.HierarchieSelection;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import java.awt.*;

import static java.lang.Math.max;

public class Arbre implements TreeExpansionListener {
    private final static int ROW_HEIGHT=24;
    private final static int MAX_WIDTH=600;
    private JDialog dialog;
    private JTree arbre;

    public Arbre(JDialog dialog, JTree arbre){
        this.dialog=dialog;
        this.arbre=arbre;
        dialog.setSize(MAX_WIDTH, arbre.getRowCount()*ROW_HEIGHT);
        dialog.setMinimumSize(new Dimension(MAX_WIDTH,max((arbre.getRowCount()+1)*ROW_HEIGHT,ROW_HEIGHT)));
    }

    @Override
    public void treeExpanded(TreeExpansionEvent treeExpansionEvent) {
        if(dialog.getHeight()<MAX_WIDTH){
            update();
        }
    }

    @Override
    public void treeCollapsed(TreeExpansionEvent treeExpansionEvent) {
        update();
    }

    private void update(){
        dialog.setSize(MAX_WIDTH, arbre.getRowCount()*ROW_HEIGHT);
        dialog.setLocationRelativeTo(null);
    }
}
