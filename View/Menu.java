package View;

import Controller.FichierBouton;
import Controller.HierarchieBouton;
import Controller.ProgrammeBouton;
import Controller.RechercheField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Menu {
    private Home home;
    private JMenuBar MenuBar;

    /**
     * Classe qui contient tous les composants de la JMenuBar,
     * c'est aussi elle qui initialise les EventListener pour les boutons du menu
     * et qui defini leurs style.
     *
     * @param home
     */

    public Menu(Home home){
        this.home=home;
        MenuBar=new JMenuBar();
        setFichier();
        setProgramme();
        setHierarchie();
        setRecherche();
        setStyle();

        //TODO faire actionmap (touche racourcis) si j'ai pas la flemme

    }

    /**
     * Defini le style de la JMenuBar.
     */

    private void setStyle(){
        MenuBar.setMargin(new Insets(5,10,5,10));
        MenuBar.setBorder(BorderFactory.createLoweredSoftBevelBorder());
    }

    /**
     * Crée les composants du JMenu Fichier:
     * Ouvrir->Appele la classe FichierBouton("Ouvrir",Home)->Ouvre JFileChooser pour changer de xml
     * Enregistrer->Appele la classe FichierBouton("Enregistrer",Home)->Appele les classes XMlMaker et WriteCSV pour sauvegarder les données dans des nouveaux fichier
     * Quitter->Appele la classe FichierBouton("Quitter",Home)->Fermer le programme
     */

    private void setFichier(){
        JMenu Fichier=new JMenu("Fichier");

        JMenuItem Open=new JMenuItem("Ouvrir");
        Open.addActionListener(new FichierBouton(Open.getText(),home));
        Fichier.add(Open);

        JMenuItem Save=new JMenuItem("Enregistrer");
        Save.addActionListener(new FichierBouton(Save.getText(),home));
        Fichier.add(Save);

        JMenuItem Close=new JMenuItem("Quitter");
        Close.addActionListener(new FichierBouton(Close.getText(),home));
        Fichier.add(Close);

        MenuBar.add(Fichier);
    }

    /**
     * Crée les composants de JMenu Programme:
     * Tout->La Jtable affiche toutes les ue
     * ...->La selection d'un programme n'affiche que les eu de celui ci
     */

    private void setProgramme(){
        JMenu Programme=new JMenu("Programme");

        JMenuItem Tout=new JMenuItem("Tout");
        Tout.addActionListener(new ProgrammeBouton(-1,home));
        Programme.add(Tout);

        int nbrProgrammes=home.getXml().getProgramList().size();
        JMenuItem[] programmes=new JMenuItem[nbrProgrammes];
        for (int i = 0; i < nbrProgrammes; i++) {
            programmes[i]=new JMenuItem(home.getXml().getProgramList().get(i).getNom());
            programmes[i].addActionListener(new ProgrammeBouton(i,home));
            Programme.add(programmes[i]);
        }

        MenuBar.add(Programme);
    }

    /**
     * Crée le JLabel Hierarchie qui appele l'EventListener HierarchieBouton(Home),
     * celui ci appele la classe Dialog qui ouvre un JDialog contenant un JTree
     * de la hierarchie des programmes du fichier xml.
     * La selection de ceux ci permet de realiser un filtrage sur le tableau.
     */

    private void setHierarchie(){
        JLabel Hierarchie=new JLabel("Hierarchie");
        Hierarchie.addMouseListener(new HierarchieBouton(home));
        Hierarchie.setBorder(new EmptyBorder(0,0,0,10));
        Hierarchie.setHorizontalTextPosition(JLabel.CENTER);

        MenuBar.add(Hierarchie);
    }

    /**
     * Crée le JTextField qui appele L'EventListener RechercheField(JTextField,Home),
     * celui ci permet de realiser un filtrage des Nom/Prenom/Numero Etudiants sur le tableau.
     */

    private void setRecherche(){
        JTextField Recherche=new JTextField();
        Recherche.setMaximumSize(new Dimension(200,20));
        Recherche.addKeyListener(new RechercheField(Recherche,home));

        JLabel bouton=new JLabel("Rechercher:");
        bouton.setBorder(new EmptyBorder(0,0,0,0));
        bouton.setHorizontalTextPosition(JLabel.CENTER);

        MenuBar.add(bouton);
        MenuBar.add(Recherche);
    }

    /**
     * Renvoit la JMenuBar.
     *
     * @return
     */
    public JMenuBar getMenuBar() {
        return MenuBar;
    }
}
