package Controller;

import Model.HierarchieCreation;
import View.Home;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class HierarchieBouton extends MenuBoutons{

    public HierarchieBouton(Home home) {
        super(home);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new HierarchieCreation(super.getHome());
    }
}
