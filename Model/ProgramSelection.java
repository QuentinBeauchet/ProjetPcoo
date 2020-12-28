package Model;

import java.util.ArrayList;

public class ProgramSelection {
    private ArrayList<Programme> programList;
    private ArrayList<Etudiant> studentList;

    /**
     * Classe qui crée un TabCreation[] de tout les Programmes du XMLreader
     * pour facilement passer de l'affichage d'un programme a un autre.
     *
     * @param xml
     */

    public ProgramSelection(XMLReader xml){
        programList=xml.getProgramList();
        studentList=xml.getStudentList();
    }

    /**
     * Renvoit l'ArrayList des tous les etudiants inscrit dans le programme
     * a l'index i dans l'ArrayList<Programme> de l'XMLReader.
     *
     * @param index
     * @return
     */

    private ArrayList<Etudiant> ListeEtudiantsIndex(int index){
        ArrayList<Etudiant> etudiants=new ArrayList<>();
        for(Etudiant e:studentList){
            if(e.estInscris(programList.get(index))){
                etudiants.add(e);
            }
        }
        return etudiants;
    }

    /**
     * Renvoit l'ArrayList de tous les cours presents dans le programme
     * a l'index i dans l'ArrayList<Programme> de l'XMLReader.
     *
     * @param index
     * @return
     */

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

    /**
     * Renvoit le TabCreation pour les etudiants et les cours du programme
     * a l'index i dans l'ArrayList<Programme> de l'XMLReader.
     *
     * @param index
     * @return
     */

    private TabCreation TabProgrammeIndex(int index){
        TabCreation tabCreation=new TabCreation(ListeCoursIndex(index),ListeEtudiantsIndex(index));
        return tabCreation;
    }

    /**
     * Renvoit le tableau TabCreation[] qui contient les TabCreation pour chaque
     * programme dans l'ArrayList<Programme> de l'XMLReader.
     *
     * @return
     */

    public TabCreation[] TabProgrammes(){
        TabCreation[] tab=new TabCreation[programList.size()];
        for (int i = 0; i < programList.size(); i++) {
            tab[i]=TabProgrammeIndex(i);
        }
        return tab;
    }
}
