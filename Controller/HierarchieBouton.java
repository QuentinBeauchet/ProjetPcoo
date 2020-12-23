package Controller;

import Model.HierarchieCreation;
import View.Home;

import java.awt.event.ActionEvent;

public class HierarchieBouton implements MenuBoutons{
    private Home home;

    public HierarchieBouton(Home home) {
        this.home=home;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        HierarchieCreation hierarchieCreation = new HierarchieCreation(home);
    }
}
