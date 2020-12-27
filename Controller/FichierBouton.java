package Controller;

import Controller.MenuBoutons;
import Model.FileChooser;
import Model.XMLReader;
import View.Home;
import View.Menu;
import View.StartView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.nio.file.Path;

public class FichierBouton extends MenuBoutons {
    private final String action;
    private StartView view;

    public FichierBouton(String s, Home home) {
        super(home);
        this.action=s;
    }

    public FichierBouton(String s, StartView view){
        this.view=view;
        this.action=s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(action.equals("Ouvrir")){
            FileChooser chooser=new FileChooser();
        }
        else if(action.equals("Start")){
            FileChooser chooser=new FileChooser();
            setConfirmation(chooser);
        }
        else if(action.equals("Launch")){
            view.dispose();
            XMLReader xml=new XMLReader(view.getFile().toString());
            new Home(xml);
        }
        else if(action.equals("Enregistrer")){
            //TODO save les csv une fois modifiés
        }
        else{
            //TODO popup de confirmation pour quitter et sauvegarder ou sans
            System.exit(0);
        }
    }

    private void setConfirmation(FileChooser chooser){
        if(chooser.getOption()==JFileChooser.APPROVE_OPTION){
            view.showConfirmation(true);
            view.setText("<html>Vous avez choisit "+chooser.getChooser().getSelectedFile().getName()+", voulez vous confirmer ?</html>");
            view.setPath(chooser.getChooser().getSelectedFile());
        }
    }

}
