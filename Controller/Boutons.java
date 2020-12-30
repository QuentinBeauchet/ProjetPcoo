package Controller;

import Model.*;
import View.Home;
import View.StartView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Boutons implements ActionListener{
    private final String action;
    private final Object obj;

    public  Boutons(String s, Object obj){
        this.obj=obj;
        this.action=s;
    }

    /**
     * Selon le String passé dans le constructeur le bouton va effectué differentes actions:
     * "Ouvrir"->Appel d'un JFileChooser dans Home
     * "Start"->Appel de JFileChooser dans StartView
     * "StartNew"->Passage de la StartView a Home avec un XMLReader()
     * "Launch"->Passage de la StartView a Home avec un XMLReader(fichier)
     * "Enregistrer"->Sauvegarde les donnés sous le format xml et csv
     * "ShortCut"->Appel la classe HierarchieCreation
     * "Quitter"->Appel a la classe PopUpConfirmation
     * "Annuler"->Ferme le popup de PopUpConfirmation sans quitter le programme
     * ...->Ferme le programme
     *
     * @param e ActionEvent
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        FileChooser chooser;
        XMLReader xml;
        StartView view;
        Home home;
        switch(action){
            case "Ouvrir":
                chooser=new FileChooser();
                if(chooser.getOption()==JFileChooser.APPROVE_OPTION){
                    //TODO c'est pas ouf comme façon de faire
                    xml=new XMLReader(chooser.getChooser().getSelectedFile().toString());
                    home=(Home)obj;
                    home.getFrame().dispose();
                    new Home(xml);
                }
                break;
            case "Start":
                chooser=new FileChooser();
                setConfirmation(chooser);
                break;
            case "StartNew":
                view=(StartView)obj;
                view.dispose();
                xml=new XMLReader();
                new Home(xml);
                break;
            case "Launch":
                view=(StartView)obj;
                view.dispose();
                xml=new XMLReader(view.getFile().toString());
                new Home(xml);
                break;
            case "Enregistrer":
                home=(Home)obj;
                xml=home.getXml();
                new XMLMaker(xml);
                new WriteCsv(xml);
                System.out.println("Sauvegarde effectué");
                //TODO popup de confirmation ?
                break;
            case "Shortcut":
                home=(Home)obj;
                new HierarchieCreation(home);
                break;
            case "Quitter":
                new PopUpConfirmation();
                break;
            case "Annuler":
                JDialog dialog=(JDialog)obj;
                dialog.dispose();
                break;
            default:
                System.exit(0);
        }
    }

    /**
     * Affiche le JPanel de confirmation dans la StartView si un fichier xml a été choisit.
     *
     * @param chooser FileChooser
     */

    private void setConfirmation(FileChooser chooser){
        if(chooser.getOption()==JFileChooser.APPROVE_OPTION){
            StartView view=(StartView)obj;
            view.showConfirmation(true);
            view.setText("<html>Vous avez choisit "+chooser.getChooser().getSelectedFile().getName()+", voulez vous confirmer ?</html>");
            view.setPath(chooser.getChooser().getSelectedFile());
        }
    }
}
