package Model;

import Controller.CoursComparator;
import View.Tableau;

import javax.swing.*;

public class Sorter {
    private int NBR_COMPOSANTS_ETUDIANTS;
    private JTable tableau;

    public Sorter(int nbrComposantesEtudiants, JTable tab){
        NBR_COMPOSANTS_ETUDIANTS=nbrComposantesEtudiants;
        tableau=tab;
        setSorter();
    }

    private void setSorter(){
        tableau.setAutoCreateRowSorter(true);
        DefaultRowSorter sorter=(DefaultRowSorter)tableau.getRowSorter();
        for(int i=NBR_COMPOSANTS_ETUDIANTS;i<tableau.getColumnModel().getColumnCount();i++){
            sorter.setComparator(i,new CoursComparator());
        }
    }

}
