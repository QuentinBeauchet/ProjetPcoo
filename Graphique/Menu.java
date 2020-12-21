package Graphique;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Menu{
  private final int DEBUT_COURS=3;
  private Fenetre Fenetre;
  public JMenuBar MenuBar;

  public Menu(Fenetre F){
    Fenetre=F;
    MenuBar=new JMenuBar();
    Fenetre.frame.getContentPane().add(MenuBar,GBC());
  }

  //Methode creation Selection Fichier
  public JMenuBar Fichier(){
    JMenu fichier=new JMenu("Fichier");
    MenuBar.add(fichier);

    JMenuItem ouvrir=new JMenuItem("Ouvrir");
    ouvrir.addActionListener(new FichierBouton(this,"Ouvrir"));
    fichier.add(ouvrir);

    JMenuItem enregistrer=new JMenuItem("Enregistrer");
    enregistrer.addActionListener(new FichierBouton(this,"Enregistrer"));
    fichier.add(enregistrer);
    return MenuBar;
  }

  //Methode creation MenuDeroulant Cours
  public JMenuBar MenuCours(String[] colonne){
    JMenu Cours=new JMenu("COURS");
    MenuBar.add(Cours);

    JMenuItem cour=new JMenuItem("ON");
    cour.addActionListener(new CourBouton(this,"ON"));
    Cours.add(cour);
    cour=new JMenuItem("OFF");
    cour.addActionListener(new CourBouton(this,"OFF"));
    Cours.add(cour);

    for(int i=DEBUT_COURS;i<colonne.length-1;i++){
      cour=new JMenuItem(colonne[i]);
      cour.addActionListener(new CourBouton(this,"COUR"));
      Cours.add(cour);
    }
    return MenuBar;
  }

  //Methode creation TextField Recherche Nom
  public JTextField TextField(){
    JPanel Recherche=new JPanel();
    JTextField textField = new JTextField();
    textField.setColumns(20);
    textField.setMaximumSize(new Dimension(100,20));
    textField.addKeyListener(new TexteBoutons(this));

    textField.setFont(new Font("Dialog", Font.BOLD, 12));
    textField.addFocusListener(new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {
        if (textField.getText().equals("RECHERCHE")) {
            textField.setText("");
        }
    }
    @Override
    public void focusLost(FocusEvent e) {
        if (textField.getText().isEmpty()) {
            textField.setText("RECHERCHE");
        }
    }
  });

    MenuBar.add(textField);
    return textField;
  }

  //Methode qui permet de communiquer d'un MenuBouton a Tableau
  public void setFiltre(String tag,String s){
    Fenetre.setFiltre(tag,s);
  }

  public void setFile(File f){
    Fenetre.setFile(f);
  }

  //GridBagConstraints du Menu
  private GridBagConstraints GBC(){
    GridBagConstraints c=new GridBagConstraints();
    c.gridx=0;
    c.gridy=0;
    c.weighty=0;
    c.weightx=1;
    c.fill = GridBagConstraints.HORIZONTAL;
    return c;
  }

  public JMenuBar getMenuBar(){
    return MenuBar;
  }
}
