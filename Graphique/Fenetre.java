import javax.swing.*;
import java.awt.*;

public class Fenetre{
  public static void main(String args[]){
   JFrame frame = new JFrame("Nom de la Fenetre");
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setSize(1500,800);

   JMenuBar mb = new JMenuBar();
   JMenu m1 = new JMenu("FILE");
   JMenu m2 = new JMenu("Help");
   mb.add(m1);
   mb.add(m2);
   JMenuItem m11 = new JMenuItem("Open");
   JMenuItem m22 = new JMenuItem("Save as");
   m1.add(m11);
   m1.add(m22);
   JButton button = new JButton("Press");
   frame.getContentPane().add(BorderLayout.NORTH,mb);

   CSV_Reader csv= new CSV_Reader("minutes_info.csv");
   String[] colones=csv.getColones();
   String[][] lignes=csv.getLignes();
   Tableau t= new Tableau(frame,lignes,colones);
   frame.setVisible(true);
 }
}
