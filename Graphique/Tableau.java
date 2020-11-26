import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;

public class Tableau{
  Tableau(JFrame frame,String[][] lignes,String[] colones){
    JTable tableau=new JTable();
    tableau = new JTable(lignes, colones);
    tableau.setAutoResizeMode(0);
/*    tableau.setBounds(30, 40, 200, 300);

    tableau.getTableHeader().setForeground(new Color(153, 31, 0));
    tableau.getTableHeader().setFont(j.getFont().deriveFont(Font.BOLD,12f));
*/
    JScrollPane ConteneurTableau = new JScrollPane(tableau);
    frame.add(ConteneurTableau,BorderLayout.CENTER);
  }
}
