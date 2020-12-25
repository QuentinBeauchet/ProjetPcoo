package View;

import Controller.FichierBouton;
import Controller.HierarchieBouton;
import Controller.ProgrammeBouton;
import Controller.RechercheField;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.*;
import java.net.IDN;

public class Menu {
    private Home home;
    private JMenuBar MenuBar;
    private JMenu Fichier;
    private JMenu Programme;
    private JLabel Hierarchie;
    private JTextField Recherche;

    public Menu(Home h){
        home=h;
        MenuBar=new JMenuBar();
        setFichier();
        setProgramme();
        setHierarchie();
        setRecherche();
        setStyle();

        //TODO faire actionmap (touche racourcis) si j'ai pas la flemme

    }

    private void setStyle(){
        MenuBar.setMargin(new Insets(5,10,5,10));
        MenuBar.setBorder(BorderFactory.createLoweredSoftBevelBorder());
    }

    private void setFichier(){
        Fichier=new JMenu("Fichier");

        JMenuItem Open=new JMenuItem("Ouvrir");
        Open.addActionListener(new FichierBouton(Open,home));
        Fichier.add(Open);

        JMenuItem Save=new JMenuItem("Enregistrer");
        Save.addActionListener(new FichierBouton(Save,home));
        Fichier.add(Save);

        JMenuItem Close=new JMenuItem("Quitter");
        Close.addActionListener(new FichierBouton(Close,home));
        Fichier.add(Close);

        MenuBar.add(Fichier);
    }

    private void setProgramme(){
        Programme=new JMenu("Programme");

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

    private void setHierarchie(){
        Hierarchie=new JLabel("Hierarchie");
        Hierarchie.addMouseListener(new HierarchieBouton(home));
        Hierarchie.setBorder(new EmptyBorder(0,0,0,10));
        Hierarchie.setHorizontalTextPosition(JLabel.CENTER);

        MenuBar.add(Hierarchie);
    }

    private void setRecherche(){
        Recherche=new JTextField();
        Recherche.setMaximumSize(new Dimension(200,20));
        Recherche.addKeyListener(new RechercheField(Recherche,home));

        JLabel bouton=new JLabel("Rechercher:");
        bouton.setBorder(new EmptyBorder(0,0,0,0));
        bouton.setHorizontalTextPosition(JLabel.CENTER);

        MenuBar.add(bouton);
        MenuBar.add(Recherche);
    }


    public JMenuBar getMenuBar() {
        return MenuBar;
    }
}
