package Controller;

import View.Home;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RechercheField implements KeyListener {
    private JTextField texte;
    private Home home;

    /**
     * Classe de L'EventListener de la barre de recherche.
     *
     * @param texte
     * @param home
     */

    public RechercheField(JTextField texte,Home home){
        this.texte=texte;
        this.home=home;
    }

    /**
     * Actualise le sorter des lignes du tableau lorsque qu'une touche est relaché.
     *
     * @param keyEvent
     */

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        home.getTab().setSorter(texte.getText());
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }
}
