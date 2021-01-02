package Model;

import Controller.NoteComparator;

import javax.swing.*;
import java.util.Comparator;

public class Sorter {
    private final int NBR_COMPOSANTS_ETUDIANTS;
    private final JTable tableau;
    private DefaultRowSorter<?,?> sorter;

    /**
     * Classe qui crée les sorters du tableau.
     *
     * @param nbrComposantesEtudiants int
     * @param tab JTable
     */

    public Sorter(int nbrComposantesEtudiants, JTable tab){
        NBR_COMPOSANTS_ETUDIANTS=nbrComposantesEtudiants;
        tableau=tab;
        setSorter();
    }

    /**
     * Creation du sorter sur les colones du tableau ASC/DESC
     */

    private void setSorter(){
        tableau.setAutoCreateRowSorter(true);
        sorter=(DefaultRowSorter<?,?>)tableau.getRowSorter();
        for(int i=NBR_COMPOSANTS_ETUDIANTS;i<tableau.getColumnModel().getColumnCount();i++){
            sorter.setComparator(i,new NoteComparator());
        }
    }

    /**
     * Creation du sorter sur les lignes du tableau selon un filtre.
     *
     * @param filter String
     */

    public void setSorter(String filter){
        int[] colones=new int[NBR_COMPOSANTS_ETUDIANTS];
        for (int i = 0; i < colones.length; i++) {
            colones[i]=i;
        }
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filter,colones));
    }

}
