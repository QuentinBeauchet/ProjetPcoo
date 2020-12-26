package Controller;

import Controller.MenuBoutons;
import Model.FileChooser;
import View.Home;
import View.Menu;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;

public class FichierBouton extends MenuBoutons {
    private final String action;

    public FichierBouton(JMenuItem item, Home home) {
        super(home);
        this.action=item.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(action.equals("Ouvrir")){
            new FileChooser();
        }
        else if(action.equals("Enregistrer")){
            //TODO save les csv une fois modifiés
        }
        else{
            //TODO popup de confirmation pour quitter et sauvegarder ou sans
            System.exit(0);
        }
    }
}
