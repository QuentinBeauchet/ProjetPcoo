package View;

import Model.FichierBouton;

import javax.swing.*;
import java.awt.*;

public class Menu {
    private JMenuBar MenuBar;
    private JMenu Fichier;
    private JMenu Programme;
    private JMenu Hierarchie;
    private JMenu Cours;
    private JTextField Recherche;

    public Menu(){
        MenuBar=new JMenuBar();
        setFichier();
        setProgramme();
        setHierarchie();
        setCours();
        setRecherche();
    }

    private void setFichier(){
        Fichier=new JMenu("Fichier");

        JMenuItem Open=new JMenuItem("Ouvrir");
        Open.addActionListener(new FichierBouton(this,Open));
        Fichier.add(Open);

        JMenuItem Save=new JMenuItem("Enregistrer");
        Save.addActionListener(new FichierBouton(this,Save));
        Fichier.add(Save);

        JMenuItem Close=new JMenuItem("Quitter");
        Close.addActionListener(new FichierBouton(this,Close));
        Fichier.add(Close);

        MenuBar.add(Fichier);
    }

    private void setProgramme(){
        Programme=new JMenu("Programme");
        MenuBar.add(Programme);
        //TODO afficher selon les programmes.
        //TODO depuis tabcreation j'envoit la xml mais les array dans le constructeur comme ça je peux les modifier en amont
    }

    private void setHierarchie(){
        Hierarchie=new JMenu("Hierarchie");
        MenuBar.add(Hierarchie);
    }

    private void setCours(){
        Cours=new JMenu("Cours");
        MenuBar.add(Cours);
    }

    private void setRecherche(){
        Recherche=new JTextField(20);
        MenuBar.add(Recherche);
    }


    public JMenuBar getMenuBar() {
        return MenuBar;
    }
}
