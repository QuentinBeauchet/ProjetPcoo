package Controller;

import View.Home;
import View.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MenuBoutons implements ActionListener {
    private Home home;

    public MenuBoutons(Home home){
        this.home=home;
    }

    public MenuBoutons(){}

    public abstract void actionPerformed(ActionEvent e);

    public Home getHome() {
        return home;
    }
}
