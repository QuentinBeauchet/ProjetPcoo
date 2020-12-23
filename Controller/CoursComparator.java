package Controller;

import Model.Note;

import java.util.Comparator;

import static java.lang.Math.max;

public class CoursComparator implements Comparator<String> {

    @Override
    public int compare(String arg0, String arg1) {
        Note n0=new Note(arg0);
        Note n1=new Note(arg1);
        Float f0=n0.getFloatNote();
        Float f1=n1.getFloatNote();
        return f0.compareTo(f1);
    }
}
