package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class ProgramSelection {
    private ArrayList<Cours> courseList;
    private ArrayList<Programme> programList;
    private ArrayList<Etudiant> studentList;

    public ProgramSelection(XMLReader xml){
        courseList=xml.getCourseList();
        programList=xml.getProgramList();
        studentList=xml.getStudentList();
    }

    private ArrayList<Etudiant> ListeEtudiantsIndex(int index){
        ArrayList<Etudiant> etudiants=new ArrayList<>();
        for(Etudiant e:studentList){
            if(e.estInscris(programList.get(index))){
                etudiants.add(e);
            }
        }
        return etudiants;
    }

    private ArrayList<Cours> ListeCoursIndex(int index){
        ArrayList<UE> ue=new ArrayList<>();
        ArrayList<Bloc> blocs=programList.get(index).getBlocs();
        for(Bloc b:blocs){
            ue.addAll(b.getUE());
        }
        ArrayList<Cours> cours=new ArrayList<>();
        for(UE u:ue){
            cours.add((Cours)u);
        }
        return cours;
    }

    private TabCreation TabProgrammeIndex(int index){
        ArrayList<Etudiant> etudiants=ListeEtudiantsIndex(index);
        ArrayList<Cours> cours=ListeCoursIndex(index);
        TabCreation tabCreation=new TabCreation(ListeCoursIndex(index),programList,ListeEtudiantsIndex(index));
        return tabCreation;
    }

    public TabCreation[] TabProgrammes(){
        TabCreation[] tab=new TabCreation[programList.size()];
        for (int i = 0; i < programList.size(); i++) {
            tab[i]=TabProgrammeIndex(i);
        }
        return tab;
    }
}
