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
    tableau = new JTable(lignes, colones);

    tableau.setAutoCreateRowSorter(true);
    sorter = (DefaultRowSorter)tableau.getRowSorter();
    for(int i=NBR_COLONES_PRINCIPALES;i<tableau.getColumnModel().getColumnCount();i++){
      sorter.setComparator(i,new CoursComparator());
    }

    //Table des scores
    String[][] lignes_calcul=new String[NBR_LIGNES_CALCUL][];
    for(int i=lignes.length-NBR_LIGNES_CALCUL;i<lignes.length;i++){
      lignes_calcul[lignes.length-i-1]=lignes[i];
    }
    JTable score=new JTable(lignes_calcul,colones);
    score.getTableHeader().setUI(null);
    score.setBackground(new Color(220,220,220));
    score.setShowVerticalLines(false);
    score.setDefaultEditor(Object.class, null);
    score.setPreferredScrollableViewportSize(new Dimension((int)score.getPreferredScrollableViewportSize().getWidth(),score.getRowCount()*score.getRowHeight()));

    //ScrollBar
    JScrollPane ConteneurScore = new JScrollPane(score);
    JScrollPane ConteneurTableau = new JScrollPane(tableau);

    //Même scrollbar horizontale
    tableau.setAutoResizeMode(0);
    score.setAutoResizeMode(0);
    ConteneurTableau.getHorizontalScrollBar().setModel(ConteneurScore.getHorizontalScrollBar().getModel());
    ConteneurTableau.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


    JPanel TAB=new JPanel();
    TAB.setLayout(new BorderLayout());
    TAB.add(ConteneurTableau);
    TAB.add(ConteneurScore,BorderLayout.SOUTH);

    GridBagConstraints c=new GridBagConstraints();
    c.gridx=0;
    c.gridy=1;
    c.weighty=1;
    c.weightx=1;
    c.fill=GridBagConstraints.BOTH;
    frame.add(TAB,c);
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
