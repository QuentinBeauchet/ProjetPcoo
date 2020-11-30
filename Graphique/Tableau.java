import javax.swing.DefaultRowSorter;
import javax.swing.RowFilter;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.util.Arrays;

public class Tableau{
  private int nbrColone;
  private final int NBR_LIGNES_CALCUL=4;
  private final int NBR_COLONES_PRINCIPALES=3;
  private DefaultRowSorter sorter;
  private JTable tableau;
  private JTable score;

  Tableau(JFrame frame,String[][] lignes,String[] colones){
    //Initialisation des champs
    tableau=new JTable(Arrays.copyOfRange(lignes,0,lignes.length-NBR_LIGNES_CALCUL),colones);
    nbrColone=tableau.getColumnModel().getColumnCount();

    //Initialisation du Sorter des colones
    tableau.setAutoCreateRowSorter(true);
    sorter=(DefaultRowSorter)tableau.getRowSorter();
    for(int i=NBR_COLONES_PRINCIPALES;i<tableau.getColumnModel().getColumnCount();i++){
      sorter.setComparator(i,new CoursComparator());
    }

    //Initialisation du Tableau des scores
    setScore(lignes,colones);

    //ScrollBar
    JScrollPane ConteneurScore = new JScrollPane(score);
    JScrollPane ConteneurTableau = new JScrollPane(tableau);
    setScrollBar(ConteneurTableau,ConteneurScore);

    //Initialisation du JPanel contenant ConteneurTableau et ConteneurScore
    JPanel TAB=new JPanel();
    TAB.setLayout(new BorderLayout());
    TAB.add(ConteneurTableau);
    TAB.add(ConteneurScore,BorderLayout.SOUTH);
    frame.add(TAB,GBC());

    //Texte du header de tableau en rouge
    tableau.getTableHeader().setForeground(new Color(153, 31, 0));
  }

  //Methode qui permet de communiquer d'un MenuBouton a Tableau
  public void setFiltre(String tag,String s){
    switch(tag){
      case "COUR":
        System.out.println("CourBouton");
        ActualiseColone(tableau,tableau.getColumnModel().getColumnIndex(s));
        ActualiseColone(score,score.getColumnModel().getColumnIndex(s));

        TableColumnModel tcm=tableau.getColumnModel();
        if(tcm.getColumn(tcm.getColumnIndex(s)).getWidth()==0){
          nbrColone=nbrColone-1;
        }
        else{
          nbrColone=nbrColone+1;
        }
        break;
      case "TEXTE":
        System.out.println("TexteBouton");
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + s,tableau.getColumnModel().getColumnIndex("Nom")));
        break;
      case "ON":
        System.out.println("ON");
        if(nbrColone<tableau.getColumnModel().getColumnCount()){
          TableauPlein(tableau);
          TableauPlein(score);
          nbrColone=tableau.getColumnModel().getColumnCount();
        }
        break;
      case "OFF":
        System.out.println("OFF");
        if(nbrColone!=0){
          TableauVide(tableau);
          TableauVide(score);
          nbrColone=0;
        }
        break;
      default:
        System.out.println("null");
        break;
    }
  }

  private void ActualiseColone(JTable tab,int i){
    TableColumnModel tcm=tab.getColumnModel();
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

  private void TableauPlein(JTable tab){
    TableColumnModel tcm=tab.getColumnModel();
    for(int i=NBR_COLONES_PRINCIPALES;i<tcm.getColumnCount();i++){
      TableColumn c= tcm.getColumn(i);
      c.setWidth(tcm.getColumn(0).getWidth());
      c.setMaxWidth(tcm.getColumn(0).getMaxWidth());
      //Erreur si minWidth direct
      c.setMinWidth(tcm.getColumn(0).getWidth());
      c.setMinWidth(tcm.getColumn(0).getMinWidth());
    }
  }

  private void TableauVide(JTable tab){
    TableColumnModel tcm=tab.getColumnModel();
    for(int i=NBR_COLONES_PRINCIPALES;i<tcm.getColumnCount();i++){
      TableColumn c= tcm.getColumn(i);
      c.setMinWidth(0);
      c.setMaxWidth(0);
      c.setWidth(0);
    }
  }

  //GridBagConstraints du JPanel contenant ConteneurTableau et ConteneurScore
  private GridBagConstraints GBC(){
    GridBagConstraints c=new GridBagConstraints();
    c.gridx=0;
    c.gridy=1;
    c.weighty=1;
    c.weightx=1;
    c.fill=GridBagConstraints.BOTH;
    return c;
  }

  //Intialise le Tableau score
  private void setScore(String[][] lignes,String[] colones){
    //Table des scores
    String[][] lignes_calcul=new String[NBR_LIGNES_CALCUL][];
    for(int i=lignes.length-NBR_LIGNES_CALCUL;i<lignes.length;i++){
      lignes_calcul[lignes.length-i-1]=lignes[i];
    }
    score=new JTable(lignes_calcul,colones);
    score.getTableHeader().setUI(null);
    score.setBackground(new Color(220,220,220));
    score.setPreferredScrollableViewportSize(new Dimension((int)score.getPreferredScrollableViewportSize().getWidth(),score.getRowCount()*score.getRowHeight()));
    //Empeche l'edition des scores
    score.setCellSelectionEnabled(true);
    score.setDefaultEditor(Object.class, null);
  }

  //Une seule ScrollBar horizontale pour tableau et score
  private void setScrollBar(JScrollPane ConteneurTableau,JScrollPane ConteneurScore){
    //Même scrollbar horizontale
    score.setAutoResizeMode(0);
    tableau.setAutoResizeMode(0);
    ConteneurTableau.getHorizontalScrollBar().setModel(ConteneurScore.getHorizontalScrollBar().getModel());
    ConteneurTableau.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
  }
}
