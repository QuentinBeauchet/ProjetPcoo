package Controller;

import Model.FileChooser;
import Model.WriteCsv;
import Model.XMLMaker;
import Model.XMLReader;
import View.Home;
import View.StartView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FichierBouton extends MenuBoutons {
    private final String action;
    private StartView view;

    /**
     * Classe de l'EventListener des boutons du JMenu Fichier.
     *
     * @param s
     * @param home
     */

    public FichierBouton(String s, Home home) {
        super(home);
        this.action=s;
    }

    /**
     * Classe de l'EventListener des JButton de la JFrame de StartView
     *
     * @param s
     * @param view
     */

    public FichierBouton(String s, StartView view){
        this.view=view;
        this.action=s;
    }

    /**
     * Selon le String passé dans le constructeur le bouton va effectué differentes actions:
     * "Ouvrir"->Appel d'un JFileChooser dans Home
     * "Start"->Appel de JFileChooser dans StartView
     * "StartNew"->Passage de la StartView a Home avec un XMLReader()
     * "Launch"->Passage de la StartView a Home avec un XMLReader(fichier)
     * "Enregistrer"->Sauvegarde les donnés sous le format xml et csv
     * ...->Ferme le programme
     *
     * @param e
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(action.equals("Ouvrir")){
            FileChooser chooser=new FileChooser();
            if(chooser.getOption()==JFileChooser.APPROVE_OPTION){
                //TODO c'est pas ouf comme façon de faire
                XMLReader xml=new XMLReader(chooser.getChooser().getSelectedFile().toString());
                super.getHome().getFrame().dispose();
                new Home(xml);
            }
        }
        else if(action.equals("Start")){
            FileChooser chooser=new FileChooser();
            setConfirmation(chooser);
        }
        else if(action.equals("StartNew")){
            view.dispose();
            XMLReader xml=new XMLReader();
            new Home(xml);
        }
        else if(action.equals("Launch")){
            view.dispose();
            XMLReader xml=new XMLReader(view.getFile().toString());
            new Home(xml);
        }
        else if(action.equals("Enregistrer")){
            XMLReader xml=super.getHome().getXml();
            new XMLMaker(xml);
            new WriteCsv(xml);
        }
        else{
            //TODO popup de confirmation pour quitter et sauvegarder ou sans
            System.exit(0);
        }
    }

    /**
     * Affiche le JPanel de confirmation dans la StartView si un fichier xml a été choisit.
     *
     * @param chooser
     */

    private void setConfirmation(FileChooser chooser){
        if(chooser.getOption()==JFileChooser.APPROVE_OPTION){
            view.showConfirmation(true);
            view.setText("<html>Vous avez choisit "+chooser.getChooser().getSelectedFile().getName()+", voulez vous confirmer ?</html>");
            view.setPath(chooser.getChooser().getSelectedFile());
        }
    }

}
