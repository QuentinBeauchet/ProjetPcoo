import javax.swing.*;
import java.awt.*;

public class Fenetre{
  private JFrame frame;
  private Tableau tab;

  public Fenetre(){
    //JFrame
    setFrame();

    //CSV
    CSV_Reader csv= new CSV_Reader("minutes_info.csv");
    String[] colones=csv.getColones();
    String[][] lignes=csv.getLignes();

    //MENU
    Menu mb= new Menu(this);
    JMenuBar m1=mb.MenuCours(colones);
    JTextField Recherche=mb.TextField();

    //TABLEAU
    tab= new Tableau(frame,lignes,colones);

    frame.setVisible(true);
  }
  public static void main(String args[]){
   Fenetre f= new Fenetre();
 }

 private void setFrame(){
   JFrame frame = new JFrame("Projet PCOO");
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setSize(1500,800);
   frame.setLayout(new GridBagLayout());
   this.frame=frame;
 }

 public JFrame getFrame(){
   return frame;
 }

 public void setFiltre(String tag,String s){
   tab.setFiltre(tag,s);
 }

}
