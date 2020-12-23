package Controller;

import Model.HierarchieCreation;
import View.Home;

import javax.swing.*;
import javax.swing.tree.TreeSelectionModel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class Dialog implements WindowListener {
    private JTree arbre;
    private Home home;

    public Dialog(Home home, JTree arbre){
        this.arbre=arbre;
        this.home=home;
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        TreeSelectionModel selectionModel = arbre.getSelectionModel();
        int[] selection=selectionModel.getSelectionRows();
        ArrayList<String> ue=new ArrayList<>();
        for(int i: selection){
            ue.add(arbre.getPathForRow(i).getLastPathComponent().toString());
        }
        new HierarchieCreation(home,ue);
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
