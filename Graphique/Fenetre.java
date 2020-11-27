import javax.swing.*;
import java.awt.*;

public class Fenetre{
  public static void main(String args[]){
   JFrame frame = new JFrame("Nom de la Fenetre");
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setSize(1500,800);

   //CSV
   CSV_Reader csv= new CSV_Reader("minutes_info.csv");
   String[] colones=csv.getColones();
   String[][] lignes=csv.getLignes();

   //MENU
   Menu mb= new Menu(frame);
   mb.MenuCours(colones);
   mb.TextField();
   mb.MenuCours(colones);

   //TABLEAU
   Tableau t= new Tableau(frame,lignes,colones);


   frame.setVisible(true);
 }
}
