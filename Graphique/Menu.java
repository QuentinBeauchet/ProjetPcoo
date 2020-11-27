import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;

public class Menu{
  private JMenuBar MenuBar;
  public String text;

  public Menu(JFrame frame){
    //Barre principale
    MenuBar = new JMenuBar();

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

    frame.getContentPane().add(BorderLayout.NORTH,MenuBar);
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

  public void MenuCours(String[] colonne){
    JMenu Cours=new JMenu("COURS");
    MenuBar.add(Cours);
    JMenuItem cour;
    for(String s:colonne){
      cour=new JMenuItem(s);
      cour.addActionListener(new CoursBoutons());
      Cours.add(cour);
    }
  }

  public void TextField(){
    JMenu Recherche=new JMenu("Recherche");
    JTextField textField = new JTextField();
    textField.setColumns(20);
    textField.addKeyListener(new TexteBoutons());
    MenuBar.add(Recherche);
    Recherche.add(textField);
  }

}
