package Controller;

import Exceptions.IdEtudiantInvalidException;
import Exceptions.NameEtudiantInvalidException;
import Exceptions.SurnameEtudiantInvalidException;
import Model.*;
import View.Home;
import View.StartView;
import View.Tableau;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Boutons implements ActionListener{
    private final String action;
    private final Object obj;
    private final Object[] args;

    public  Boutons(String s, Object obj,Object... args){
        this.obj=obj;
        this.action=s;
        this.args=args;
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
        JDialog dialog;
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
                new PopUp(action);
                break;
            case "Ajouter un etudiant": case "Ajouter un cours": case "Ajouter un programme":
                home=(Home)obj;
                new PopUp(action,home);
                break;
            case "Ajout Etudiant":
                home=(Home)obj;
                new AjoutEtudiant(home,args);
                /*
                JTextField nom= (JTextField) args[1];
                JTextField prenom = (JTextField) args[2];
                JTextField id = (JTextField) args[3];
                JList liste= (JList) args[4];
                Programme programme=(Programme)liste.getModel().getElementAt(liste.getFirstVisibleIndex());
                try {
                    Etudiant etudiant = new Etudiant(id.getText(), nom.getText(), prenom.getText());
                    if(!(programme.getNom().equals("Aucun"))){
                        etudiant.inscris(programme);
                    }
                    home.getXml().getStudentList().add(etudiant);
                    DefaultTableModel model= (DefaultTableModel) home.getTab().getTableau().getModel();
                    Object[] row=new Object[model.getColumnCount()];
                    row[0]=etudiant.getId();
                    row[1]=etudiant.getNom();
                    row[2]=etudiant.getPrenom();
                    row[3]=programme;
                    row[4]=Float.NaN;
                    model.addRow(row);
                }
                catch (IdEtudiantInvalidException exception){
                    nom.setBorder(prenom.getBorder());
                    prenom.setBorder(nom.getBorder());
                    id.setBorder(BorderFactory.createLineBorder(Color.red));
                }
                catch (NameEtudiantInvalidException exception){
                    id.setBorder(prenom.getBorder());
                    prenom.setBorder(id.getBorder());
                    nom.setBorder(BorderFactory.createLineBorder(Color.red));
                }
                catch (SurnameEtudiantInvalidException exception){
                    nom.setBorder(id.getBorder());
                    prenom.setBorder(BorderFactory.createLineBorder(Color.red));
                }
                */
                break;
            case "Annuler":
                dialog=(JDialog)obj;
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
