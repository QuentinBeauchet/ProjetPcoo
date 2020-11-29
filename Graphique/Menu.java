import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;

public class Menu{
  private final int DEBUT_COURS=3;
  private Fenetre Fenetre;
  private JMenuBar MenuBar;

  public Menu(Fenetre F){
    Fenetre=F;
    MenuBar = new JMenuBar();

    GridBagConstraints c=new GridBagConstraints();
    c.gridx=0;
    c.gridy=0;
    c.weighty=0;
    c.weightx=1;
    c.fill = GridBagConstraints.HORIZONTAL;
    Fenetre.getFrame().getContentPane().add(MenuBar,c);
  }

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

  public JTextField TextField(){
    JMenu Recherche=new JMenu("Recherche Nom");
    JTextField textField = new JTextField();
    textField.setColumns(20);
    textField.addKeyListener(new TexteBoutons(this));
    MenuBar.add(Recherche);
    Recherche.add(textField);
    return textField;
  }

  public void setFiltre(String tag,String s){
    Fenetre.setFiltre(tag,s);
  }

  public JMenuBar getMenuBar(){
    return MenuBar;
  }
}

/*  private void ajoutSousMenu(JMenu parent,JMenu fils,String... item){
    if(item.length>0){
      for(String i:item){
        fils.add(new JMenuItem(i));
      }
      parent.add(fils);
    }
    else{
      parent.add(new JMenuItem(fils.getText()));
    }
  }
*/


    /*JMenu m1 = new JMenu("OPTION 1");
    MenuBar.add(m1);
    JMenu m11 = new JMenu("sm 1");
    m1.add(m11);
    JMenuItem m111 = new JMenuItem("A");
    m11.add(m111);
    JMenuItem m112 = new JMenuItem("B");
    m11.add(m112);
    JMenuItem m113 = new JMenuItem("C");
    m11.add(m113);
    JMenu m12 = new JMenu("sm 2");
    m1.add(m12);
    JMenu m2 = new JMenu("OPTION 2");
    MenuBar.add(m2);
    JMenu m3 = new JMenu("OPTION 3");
    MenuBar.add(m3);

    m111.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("PAF !!!");
      }
    });*/


    //System.out.println(((JMenuItem)(m3.getMenuComponents()[0])).getText());
    //System.out.println((((JMenu)(m3.getMenuComponents()[0])).getMenuComponents())[0]);
