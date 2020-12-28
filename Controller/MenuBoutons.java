package Controller;

import View.Home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MenuBoutons implements ActionListener {
    private Home home;

    /**
     * Classe abstraite qui regroupe tous les boutons dont l'EventListener
     * est un ActionListener dans Home.
     *
     * @param home
     */

    public MenuBoutons(Home home){
        this.home=home;
    }

    /**
     * Classe abstraite qui regroupe tous les boutons dont l'EventListener
     * est un ActionListener dans StartView.
     */

    public MenuBoutons(){}

    /**
     * Methode abstraite actionPerformed.
     *
     * @param e
     */

    public abstract void actionPerformed(ActionEvent e);

    /**
     * Renvoit Home.
     *
     * @return
     */

    public Home getHome() {
        return home;
    }
}
