package Model;

import java.util.ArrayList;

public class TabCreation {
    private static final int NBR_COMPOSANTS_ETUDIANTS=3;
    private static final int NBR_LIGNES_CALCULS=4;
    private String[] colones;
    private String[][] lignes;
    private String[][] calculs;
    private final ArrayList<Cours> courseList;
    private final ArrayList<Etudiant> studentList;

    /**
     * Classe qui initialise les deux JTable celle des etudiants et celle des calculs.
     *
     * @param cours ArrayList<Cours>
     * @param etudiants ArrayList<Etudiant>
     */

    public TabCreation(ArrayList<Cours> cours,ArrayList<Etudiant> etudiants){
        courseList=cours;
        studentList=etudiants;
        int nbrLignes=studentList.size();
        int nbrColones=courseList.size()+NBR_COMPOSANTS_ETUDIANTS;
        setColones(nbrColones);
        setLignes(nbrColones,nbrLignes);
        setCalculs(nbrColones);
    }

    /**
     * Crée l'Header de la JTable etudiants.
     *
     * @param nbrColones int
     */

    private void setColones(int nbrColones) {
        colones=new String[nbrColones];
        colones[0]="N° Étudiant";
        colones[1]="Nom";
        colones[2]="Prénom";
        for (int i = 0; i < courseList.size(); i++) {
            //TODO afficher l'id aussi mais mieux que colones[i+NBR_COMPOSANTS_ETUDIANTS]=courseList.get(i).getId()+" - "+courseList.get(i).getNom(); et aussi dans la hierarchie
            colones[i+NBR_COMPOSANTS_ETUDIANTS]=courseList.get(i).getNom();
        }
    }

    /**
     * Crée les lignes de la JTable etudiants.
     *
     * @param nbrColones int
     * @param nbrLignes int
     */

    private void setLignes(int nbrColones,int nbrLignes) {
        lignes=new String[nbrLignes][nbrColones];
        for (int i = 0; i < studentList.size(); i++) {
            lignes[i][0]=studentList.get(i).getId();
            lignes[i][1]=studentList.get(i).getNom();
            lignes[i][2]=studentList.get(i).getPrenom();
            for (int j = 0; j < courseList.size(); j++) {
                if(!courseList.get(j).calcNote(studentList.get(i)).getNote().equals("")) {
                    lignes[i][j + NBR_COMPOSANTS_ETUDIANTS] = courseList.get(j).calcNote(studentList.get(i)).getNote();
                }
            }
        }
    }

    /**
     * Crée les lignes de la JTable calculs.
     *
     * @param nbrColones int
     */

    private void setCalculs(int nbrColones) {
        calculs=new String[NBR_LIGNES_CALCULS][nbrColones];

        calculs[0][0]="Note max";
        calculs[1][0]="Note min";
        calculs[2][0]="Note moyenne";
        calculs[3][0]="Écart-type";

        for (int i = 0; i < courseList.size(); i++) {
            ArrayList<String> toolKit = MyTools.getStats(courseList.get(i),studentList);
            calculs[0][i+NBR_COMPOSANTS_ETUDIANTS]=toolKit.get(0); //max
            calculs[1][i+NBR_COMPOSANTS_ETUDIANTS]=toolKit.get(1); //min
            calculs[2][i+NBR_COMPOSANTS_ETUDIANTS]=toolKit.get(2); //moy
            calculs[3][i+NBR_COMPOSANTS_ETUDIANTS]=toolKit.get(3); //ecart type
        }
    }

    /**
     * Renvoit l'Header de la JTable etudiants.
     *
     * @return String[]
     */

    public String[] getColones() {
        return colones;
    }

    /**
     * Renvoit les lignes de la JTable etudiants.
     *
     * @return String[][]
     */

    public String[][] getLignes() {
        return lignes;
    }

    /**
     * Renvoit les lignes de la JTable calculs.
     *
     * @return String[][]
     */

    public String[][] getCalculs() {
        return calculs;
    }

}
