import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class Menu{
  private final int DEBUT_COURS=3;
  private Fenetre Fenetre;
  public JMenuBar MenuBar;

  public Menu(Fenetre F){
    Fenetre=F;
    MenuBar=new JMenuBar();
    Fenetre.frame.getContentPane().add(MenuBar,GBC());
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
    JMenu Recherche=new JMenu("Recherche Nom");
    JTextField textField = new JTextField();
    textField.setColumns(20);
    textField.addKeyListener(new TexteBoutons(this));
    Recherche.add(textField);
    MenuBar.add(Recherche);
    return textField;
  }

  //Methode qui permet de communiquer d'un MenuBouton a Tableau
  public void setFiltre(String tag,String s){
    Fenetre.setFiltre(tag,s);
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
}
