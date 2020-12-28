package Controller;

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

    /**
     * Classe de l'EventListener du JTree qui permet de changer dynamiquement sa taille.
     *
     * @param dialog
     * @param arbre
     */

    public Arbre(JDialog dialog, JTree arbre){
        this.dialog=dialog;
        this.arbre=arbre;
        dialog.setSize(MAX_WIDTH, arbre.getRowCount()*ROW_HEIGHT);
        dialog.setMinimumSize(new Dimension(MAX_WIDTH,max((arbre.getRowCount()+1)*ROW_HEIGHT,ROW_HEIGHT)));
    }

    /**
     * Appele l'update jusqu'a une taille maximum.
     *
     * @param treeExpansionEvent
     */

    @Override
    public void treeExpanded(TreeExpansionEvent treeExpansionEvent) {
        if(dialog.getHeight()<MAX_WIDTH){
            update();
        }
    }

    /**
     * Appele l'update.
     *
     * @param treeExpansionEvent
     */

    @Override
    public void treeCollapsed(TreeExpansionEvent treeExpansionEvent) {
        update();
    }

    /**
     * Update sa taille selon le nombre d'elements affichés.
     */

    private void update(){
        dialog.setSize(MAX_WIDTH, arbre.getRowCount()*ROW_HEIGHT);
        dialog.setLocationRelativeTo(null);
    }
}
