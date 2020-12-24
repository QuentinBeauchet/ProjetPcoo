package Controller;

import Model.HierarchieCreation;
import View.Home;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class HierarchieBouton implements MouseListener{
    private Home home;

    public HierarchieBouton(Home home) {
        this.home=home;
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        new HierarchieCreation(home);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
