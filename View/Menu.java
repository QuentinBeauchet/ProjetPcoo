package View;

import Controller.FichierBouton;
import Controller.HierarchieBouton;
import Controller.ProgrammeBouton;

import javax.swing.*;

public class Menu {
    private Home home;
    private JMenuBar MenuBar;
    private JMenu Fichier;
    private JMenu Programme;
    private JMenu Cours;
    private JMenuItem Hierarchie;
    private JTextField Recherche;

    public Menu(Home h){
        home=h;
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
        Open.addActionListener(new FichierBouton(Open));
        Fichier.add(Open);

        JMenuItem Save=new JMenuItem("Enregistrer");
        Save.addActionListener(new FichierBouton(Save));
        Fichier.add(Save);

        JMenuItem Close=new JMenuItem("Quitter");
        Close.addActionListener(new FichierBouton(Close));
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
        Hierarchie=new JMenuItem("Hierarchie");
        Hierarchie.addActionListener(new HierarchieBouton(home));
        Programme.add(Hierarchie,0);
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
