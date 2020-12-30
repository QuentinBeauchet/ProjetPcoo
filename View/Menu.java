package View;

import Controller.FichierBouton;
import Controller.HierarchieBouton;
import Controller.ProgrammeBouton;
import Controller.RechercheField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;

public class Menu {
    private final Home home;
    private final JMenuBar MenuBar;

    /**
     * Classe qui contient tous les composants de la JMenuBar,
     * c'est aussi elle qui initialise les EventListener pour les boutons du menu
     * et qui defini leurs style.
     *
     * @param home Home
     */

    public Menu(Home home){
        this.home=home;
        MenuBar=new JMenuBar();
        setFichier();
        setProgramme();
        setHierarchie();
        setRecherche();
        setStyle();

        //TODO help racourcis
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
        Fichier.setMnemonic(KeyEvent.VK_F);

        JMenuItem Open=new JMenuItem("Ouvrir");
        Open.addActionListener(new FichierBouton(Open.getText(),home));
        Open.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
        Fichier.add(Open);

        JMenuItem Save=new JMenuItem("Enregistrer");
        Save.addActionListener(new FichierBouton(Save.getText(),home));
        Save.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
        Fichier.add(Save);

        JMenuItem Close=new JMenuItem("Quitter");
        Close.addActionListener(new FichierBouton(Close.getText(),home));
        Close.setAccelerator(KeyStroke.getKeyStroke('Q', CTRL_DOWN_MASK));
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
        Programme.setMnemonic(KeyEvent.VK_P);

        JMenuItem Tout=new JMenuItem("Tout");
        Tout.addActionListener(new ProgrammeBouton(-1,home));
        Tout.setAccelerator(KeyStroke.getKeyStroke('T', CTRL_DOWN_MASK));
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
        Hierarchie.setDisplayedMnemonic(KeyEvent.VK_H);
        Hierarchie.setBorder(new EmptyBorder(0,0,0,10));
        Hierarchie.setHorizontalTextPosition(JLabel.CENTER);

        JMenuItem hidden=new JMenuItem();
        hidden.addActionListener(new FichierBouton("Shortcut",home));
        hidden.setAccelerator(KeyStroke.getKeyStroke('H', InputEvent.ALT_DOWN_MASK));
        hidden.setMinimumSize(new Dimension(0,0));
        hidden.setMaximumSize(new Dimension(0,0));
        hidden.setSize(new Dimension(0,0));
        MenuBar.add(hidden);

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
     * @return JMenuBar
     */
    public JMenuBar getMenuBar() {
        return MenuBar;
    }
}
