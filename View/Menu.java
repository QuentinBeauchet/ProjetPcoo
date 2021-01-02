package View;

import Controller.Boutons;
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
        setModif();




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
     * Ouvrir->Appele la classe Boutons("Ouvrir",Home)->Ouvre JFileChooser pour changer de xml
     * Enregistrer->Appele la classe Boutons("Enregistrer",Home)->Appelle les classes XMlMaker et WriteCSV pour sauvegarder les données dans des nouveaux fichier
     * Quitter->Appele la classe Boutons("Quitter",Home)->Fermer le programme
     */

    private void setFichier(){
        JMenu Fichier=new JMenu("Fichier");
        Fichier.setMnemonic(KeyEvent.VK_F);

        JMenuItem Open=new JMenuItem("Ouvrir");
        Open.addActionListener(new Boutons(Open.getText(),home));
        Open.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
        Fichier.add(Open);

        JMenuItem Save=new JMenuItem("Enregistrer");
        Save.addActionListener(new Boutons(Save.getText(),home));
        Save.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
        Fichier.add(Save);

        JMenuItem Close=new JMenuItem("Quitter");
        Close.addActionListener(new Boutons(Close.getText(),home));
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
        hidden.addActionListener(new Boutons("Shortcut",home));
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


    private void setModif(){
/*        JMenu modif=new JMenu("Modification");
        JMenuItem ajoutEtu =new JMenuItem("Ajouter Etudiant");
        JMenuItem ajoutCours=new JMenuItem("Ajouter Cours");
        JMenuItem ajoutProg=new JMenuItem("Ajouter Programme");
        //modif.addActionListener(new Boutons("modif",0));
        JMenu b=new JMenu("bouttons");

        modif.add(ajoutEtu);
        modif.add(ajoutCours);
        modif.add(ajoutProg);
        b.add(modif);

        MenuBar.add(b);
*/

        JMenu modif=new JMenu("Modification");
        modif.setMnemonic(KeyEvent.VK_M);

        JMenuItem ajoutEtu=new JMenuItem("Ajouter un etudiant");
        ajoutEtu.addActionListener(new Boutons("Ajouter Etudiant",home));
        ajoutEtu.setMnemonic(KeyEvent.VK_N);

        /*
        TextField textFieldID = new TextField("ID");
        textFieldID.addActionListener( new Boutons("ID",textFieldID));

        TextField textFieldNom = new TextField("Nom");
        TextField textFieldPrenom = new TextField("Prénom");

        JButton bAjoutEtu= new JButton ("Ajouter etudiant");
        bAjoutEtu.addActionListener(new Boutons(bAjoutEtu.getText(),home));

        ajoutEtu.add(textFieldID);
        ajoutEtu.add(textFieldNom);
        ajoutEtu.add(textFieldPrenom);
        ajoutEtu.add(bAjoutEtu);
  */
        modif.add(ajoutEtu);


        JMenuItem ajoutCours=new JMenuItem("Ajouter un cours");
        ajoutCours.addActionListener(new Boutons(ajoutCours.getText(),home));
        ajoutCours.setAccelerator(KeyStroke.getKeyStroke('C', CTRL_DOWN_MASK));
        modif.add(ajoutCours);

        JMenuItem ajoutProg=new JMenuItem("Ajouter un programme");
        ajoutProg.addActionListener(new Boutons(ajoutProg.getText(),home));
        ajoutProg.setAccelerator(KeyStroke.getKeyStroke('P', CTRL_DOWN_MASK));
        modif.add(ajoutProg);

        MenuBar.add(modif);

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
