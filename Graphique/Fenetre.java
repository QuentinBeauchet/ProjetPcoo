import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import java.awt.GridBagLayout;

public class Fenetre{
  public JFrame frame;
  private Tableau tab;

  public static void main(String args[]){
    Fenetre f= new Fenetre();
  }

  public Fenetre(){
    //JFrame
    setFrame();

    //CSV
    CSV_Reader csv= new CSV_Reader("minutes_info.csv");
    String[] colones=csv.getColones();
    String[][] lignes=csv.getLignes();

    //MENU
    Menu mb= new Menu(this);
    JMenuBar Cours=mb.MenuCours(colones);
    JTextField Recherche=mb.TextField();

    //TABLEAU
    tab=new Tableau(frame,lignes,colones);

    frame.setVisible(true);
  }

 //Parametre de la JFrame
 private void setFrame(){
   JFrame frame = new JFrame("Projet PCOO");
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setSize(1500,800);
   frame.setLayout(new GridBagLayout());
   this.frame=frame;
 }

 //Methode qui permet de communiquer d'un MenuBouton a Tableau
 public void setFiltre(String tag,String s){
   tab.setFiltre(tag,s);
 }

}
