import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.DefaultRowSorter;
import javax.swing.table.TableRowSorter;
import javax.swing.table.*;
import java.util.ArrayList;
import javax.swing.RowSorter.SortKey;
import java.util.Arrays;

public class Tableau{
  private boolean Vide;
  private final int NBR_LIGNES_CALCUL=4;
  private final int NBR_COLONES_PRINCIPALES=3;
  private DefaultRowSorter sorter;
  private JTable tableau;

  Tableau(JFrame frame,String[][] lignes,String[] colones){
    //Erreur de sort avec des string pour les nombres il faut des doubles
    tableau = new JTable(lignes, colones);
    tableau.setAutoResizeMode(0);

    tableau.setAutoCreateRowSorter(true);
    sorter = (DefaultRowSorter)tableau.getRowSorter();

    JScrollPane ConteneurTableau = new JScrollPane(tableau);
    frame.add(ConteneurTableau,BorderLayout.CENTER);
    tableau.getTableHeader().setForeground(new Color(153, 31, 0));

  }

  public void setFiltre(String tag,String s){
    switch(tag){
      case "COUR":
        System.out.println("CourBouton");
        ActualiseColone(tableau.getColumnModel().getColumnIndex(s));
        break;
      case "TEXTE":
        System.out.println("TexteBouton");
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + s,tableau.getColumnModel().getColumnIndex("Nom")));
        break;
      case "ON":
        System.out.println("ON");
        TableauPlein();
        break;
      case "OFF":
        System.out.println("OFF");
        TableauVide();
        break;
      default:
        System.out.println("null");
        break;
    }
  }

  private void ActualiseColone(int i){
    TableColumnModel tcm=tableau.getColumnModel();
    if((i>0) && (i<tcm.getColumnCount())){
      TableColumn c= tcm.getColumn(i);
      if(c.getWidth()==0){
        c.setWidth(tcm.getColumn(0).getWidth());
        c.setMaxWidth(tcm.getColumn(0).getMaxWidth());
        //Erreur si minWidth direct
        c.setMinWidth(tcm.getColumn(0).getWidth());
        c.setMinWidth(tcm.getColumn(0).getMinWidth());
      }
      else{
        c.setMinWidth(0);
        c.setMaxWidth(0);
        c.setWidth(0);
      }
    }
  }

  private void TableauPlein(){
    if(Vide){
      TableColumnModel tcm=tableau.getColumnModel();
      for(int i=NBR_COLONES_PRINCIPALES;i<tcm.getColumnCount();i++){
        TableColumn c= tcm.getColumn(i);
        c.setWidth(tcm.getColumn(0).getWidth());
        c.setMaxWidth(tcm.getColumn(0).getMaxWidth());
        //Erreur si minWidth direct
        c.setMinWidth(tcm.getColumn(0).getWidth());
        c.setMinWidth(tcm.getColumn(0).getMinWidth());
      }
      Vide=false;
    }
  }

  private void TableauVide(){
    if(!Vide){
      TableColumnModel tcm=tableau.getColumnModel();
      for(int i=NBR_COLONES_PRINCIPALES;i<tcm.getColumnCount();i++){
        TableColumn c= tcm.getColumn(i);
        c.setMinWidth(0);
        c.setMaxWidth(0);
        c.setWidth(0);
      }
      Vide=true;
    }
  }
}

/*    tableau.setBounds(30, 40, 200, 300);

    tableau.getTableHeader().setForeground(new Color(153, 31, 0));
    tableau.getTableHeader().setFont(j.getFont().deriveFont(Font.BOLD,12f));
*/

    /*TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableau.getModel());
    tableau.setRowSorter(sorter);
    ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
    sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
    sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
    sorter.setSortKeys(sortKeys);*/
