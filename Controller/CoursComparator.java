package Controller;

import Model.Note;

import java.util.Comparator;

public class CoursComparator implements Comparator<String> {

    /**
     * Classe qui permet de comparer deux notes du tableau.
     *
     * @param arg0 String
     * @param arg1 String
     * @return int
     */

    @Override
    public int compare(String arg0, String arg1) {
        Note n0=new Note(arg0);
        Note n1=new Note(arg1);
        Float f0=n0.getFloatNote();
        Float f1=n1.getFloatNote();
        return f0.compareTo(f1);
    }
}
