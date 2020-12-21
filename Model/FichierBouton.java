package Model;

import View.Menu;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FichierBouton extends MenuBoutons{
    private final String action;

    public FichierBouton(Menu menu, JMenuItem item) {
        super(menu);
        this.action=item.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(action.equals("Ouvrir")){
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers .xml", "xml");
            chooser.setFileFilter(filter);
            chooser.showOpenDialog(null);
            if(chooser.getSelectedFile()!=null){
                //TODO passer le chemin a xmlreader
            }
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
