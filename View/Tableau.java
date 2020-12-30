package View;

import Controller.HeaderListener;
import Exceptions.LookAndFeelException;
import Model.Sorter;
import Model.TabCreation;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class Tableau {
    private final int NBR_COMPOSANTS_ETUDIANTS;
    private JTable tableau;
    private final JTable calculs;
    private JScrollPane PART1;
    private JScrollPane PART2;
    private JPanel Panel;
    private Sorter sorter;

    /**
     * Classe qui affiche le tableau grace a une JTable a partir d'une TabCreation.
     *
     * @param tab TabCreation
     */

    public Tableau(TabCreation tab){
        NBR_COMPOSANTS_ETUDIANTS=tab.NBR_COMPOSANTS_ETUDIANTS;
        String[] colones = tab.getColones();
        String[][] lignes = tab.getLignes();
        String[][] scores = tab.getCalculs();
        setTabLF(lignes,colones);
        calculs=new JTable(scores,colones);
        setScrollBar();
        setLayout();
        setStyle();
        setEdition();
        setSorter();

        //TODO colones program validé + note (genre vert validé,rouge sinon)
    }

    /**
     * Look&Feel de la JTable des etudiants et instanciation de celle ci.
     *
     * @param lignes String[][]
     * @param colones String[]
     */

    private void setTabLF(String[][] lignes,String[] colones){
        LookAndFeel previousLF=UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        catch (Exception exception){
            throw new LookAndFeelException();
        }
        tableau=setStyleLignes(lignes,colones);
        try {
            UIManager.setLookAndFeel(previousLF);
        } catch (UnsupportedLookAndFeelException e) {
            throw new LookAndFeelException();
        }
    }

    /**
     * Look&Feel des JScroolPane et instanciation de ceux ci.
     */

    private void setScrollPaneLF(){
        LookAndFeel previousLF=UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        }
        catch (Exception exception){
            throw new LookAndFeelException();
        }
        PART1= new JScrollPane(tableau);
        PART2= new JScrollPane(calculs);
        try {
            UIManager.setLookAndFeel(previousLF);
        } catch (UnsupportedLookAndFeelException e) {
            throw new LookAndFeelException();
        }
    }

    /**
     * Parametrage des JScrollPane.
     */

    private void setScrollBar() {
        setScrollPaneLF();
        tableau.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        calculs.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        PART1.getHorizontalScrollBar().setModel(PART2.getHorizontalScrollBar().getModel());
        PART1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        PART2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        calculs.getTableHeader().setUI(null);
    }

    /**
     * Configure le layout.
     */

    private void setLayout(){
        Panel=new JPanel();
        Panel.setLayout(new GridBagLayout());
        calculs.setPreferredScrollableViewportSize(new Dimension((int)calculs.getPreferredScrollableViewportSize().getWidth(),calculs.getRowCount()*calculs.getRowHeight()));
        PART2.setMinimumSize(new Dimension((int)calculs.getPreferredScrollableViewportSize().getWidth(),calculs.getRowHeight()*(calculs.getRowCount()+1)));

        Panel.add(PART1,new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));
        Panel.add(PART2,new GridBagConstraints(0,1,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));
    }

    /**
     * Appele les methodes pour configurer les style de tous les composants.
     */

    private void setStyle(){
        setStyleHeader();
        setStyleCalculs();
        setStyleColonnes();
    }

    /**
     * Configure le style des Header.
     */

    private void setStyleHeader(){
        JTableHeader header=tableau.getTableHeader();
        header.setForeground(new Color(153, 31, 0));
        header.setFont(header.getFont().deriveFont(Font.BOLD));

        header.addMouseListener(new HeaderListener(tableau));
    }

    /**
     * Configure le style de la JTable des calculs.
     */

    private void setStyleCalculs(){
        calculs.setBackground(tableau.getTableHeader().getBackground());
        //TODO rendre plus jolie le tableau calcul
    }

    /**
     * Permet de mettre une couleur sur deux pour les lignes de la JTable.
     *
     * @param lignes String[][]
     * @param colones String[]
     * @return JTable
     */

    private JTable setStyleLignes(String[][] lignes,String[] colones){
        return new JTable(lignes,colones){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component returnComp = super.prepareRenderer(renderer, row, column);
                if (!returnComp.getBackground().equals(getSelectionBackground())) {
                    Color impair;
                    if(column<NBR_COMPOSANTS_ETUDIANTS){
                        impair = tableau.getTableHeader().getBackground();
                    }
                    else{
                        impair=new Color(226,239,218);
                    }
                    Color background = (row % 2 == 0 ? impair : Color.white);
                    returnComp.setBackground(background);

                }
                return returnComp;
            }
        };
    }

    /**
     * Configure le style des Colones.
     */

    private void setStyleColonnes(){
        TableColumnModel columnModel = tableau.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setResizable(false);
        }
    }

    /**
     * Empeche l'edition de tableau des calculs.
     */

    private void setEdition(){
        calculs.setCellSelectionEnabled(true);
        calculs.setDefaultEditor(Object.class, null);
    }

    /**
     * Configure le Sorter des colones.
     */

    private void setSorter(){
        sorter=new Sorter(NBR_COMPOSANTS_ETUDIANTS,tableau);
    }

    /**
     * Configure le sorter des lignes selon le filtre.
     *
     * @param filter String
     */

    public void setSorter(String filter){
        sorter.setSorter(filter);
        //TODO faire l'affichage façon moche
    }

    /**
     * Renvoit le JPanel qui contient les deux tableaux.
     *
     * @return JPanel
     */

    public JPanel getPanel() {
        return Panel;
    }
}
