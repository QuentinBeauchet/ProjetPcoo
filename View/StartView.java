package View;

import Controller.FichierBouton;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class StartView {
    private JFrame frame;
    private JPanel panel;
    private JButton fichier;
    private JPanel confirmation;
    private JLabel texte;
    private JButton oui;
    private JButton non;
    private JButton nouveau;
    private JButton quitter;
    private File file;

    /**
     * Classe qui affiche la JFrame qui est presente au lancement du programme,
     * celle ci permet que choisir un fichier xml, ou de commencer sur un nouveau tableau.
     */

    public StartView(){
        try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");}catch (Exception exception){}
        frame = new JFrame("Projet PCOO");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300,300);
        frame.setLocationRelativeTo(null);

        setLayout();
        setListener();

        frame.setVisible(true);
    }

    /**
     * Crée les differents composants de la JFrame, leurs layout et leurs style.
     */

    private void setLayout(){
        panel=new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel message=new JLabel("<html><p style=width:200px>Bienvenue sur le Projet de PCOO de </br> Quentin BEAUCHET,Yann FORNER et Gillian MASSE</p></html>");
        panel.add(message,new GridBagConstraints(0,0,5,1,1,0,GridBagConstraints.NORTHWEST,GridBagConstraints.NONE,new Insets(10,20,0,0),0,0));

        JLabel choix=new JLabel("Veuillez choisir un fichier:");
        panel.add(choix,new GridBagConstraints(0,2,3,1,1,0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,20,20,0),0,0));

        fichier=new JButton("<html><b>Fichier</b></html>");
        panel.add(fichier,new GridBagConstraints(0,3,2,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,20,0,100),0,0));

        confirmation=new JPanel();
        panel.add(confirmation,new GridBagConstraints(0,5,3,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,20,10,20),0,0));
        confirmation.setLayout(new GridBagLayout());

        texte=new JLabel();
        confirmation.add(texte,new GridBagConstraints(0,0,2,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));

        oui=new JButton("OUI");
        confirmation.add(oui,new GridBagConstraints(0,1,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,30,5,20),0,0));

        non=new JButton("NON");
        confirmation.add(non,new GridBagConstraints(1,1,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,20,5,30),0,0));

        nouveau=new JButton("Nouveau");
        panel.add(nouveau,new GridBagConstraints(0,6,3,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,5,5,200),0,0));

        quitter=new JButton("Quitter");
        panel.add(quitter,new GridBagConstraints(1,6,3,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,200,5,5),0,0));

        showConfirmation(false);

        frame.add(panel);

        frame.setResizable(false);
        frame.setUndecorated(true);
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(),BorderFactory.createLineBorder(Color.blue,2)));
    }

    /**
     * Attribution des EventListener a chacun des JBouton de la JFrame:
     * fichier->FichierBouton("Start",StartView)->Ouvre JFileChooser pour chosir un xml
     * oui->FichierBouton("Launch",StartView)->Ferme la JFrame et instancie la classe Home avec le xml selectioné
     * non->FichierBouton("Start",StartView)->Ouvre JFileChooser pour chosir un xml
     * nouveau->FichierBouton("StartNew",StartView)->Ferme la JFrame et instancie la classe Home avec un xml vide
     * quitter->FichierBouton("Start",StartView)->Ferme le programme
     */

    private void setListener(){
        fichier.addActionListener(new FichierBouton("Start",this));
        oui.addActionListener(new FichierBouton("Launch",this));
        non.addActionListener(new FichierBouton("Start",this));
        nouveau.addActionListener(new FichierBouton("StartNew",this));
        quitter.addActionListener(new FichierBouton("Quitter",this));
    }

    /**
     * Rend visible le JPanel de confirmation.
     *
     * @param bool
     */

    public void showConfirmation(boolean bool){
        for(Component c:confirmation.getComponents()){
            c.setVisible(bool);
        }
    }

    /**
     * Change le fichier du xml.
     *
     * @param file
     */

    public void setPath(File file){
        this.file=file;
    }

    /**
     * Change le message affiché dans le JPanel.
     *
     * @param s
     */

    public void setText(String s){
        texte.setText(s);
    }

    /**
     * Ferme la JFrame.
     */

    public void dispose(){
        frame.dispose();
    }

    /**
     * Renvoit le fichier du xml.
     *
     * @return
     */

    public File getFile(){
        return file;
    }

}


