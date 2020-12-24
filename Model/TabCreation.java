package Model;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TabCreation {
    private static final int NBR_COMPOSANTS_ETUDIANTS=3;
    private static final int NBR_LIGNES_CALCULS=4;
    private String[] colones;
    private String[][] lignes;
    private String[][] scores;
    private ArrayList<Cours> courseList;
    private ArrayList<Programme> programList;
    private ArrayList<Etudiant> studentList;

    public TabCreation(ArrayList<Cours> cours, ArrayList<Programme> programmes, ArrayList<Etudiant> etudiants){
        courseList=cours;
        programList=programmes;
        studentList=etudiants;
        int nbrLignes=studentList.size();
        int nbrColones=courseList.size()+NBR_COMPOSANTS_ETUDIANTS;
        setColones(nbrColones);
        setLignes(nbrColones,nbrLignes);
        setScores(nbrColones);
    }

    private void setColones(int nbrColones) {
        colones=new String[nbrColones];
        colones[0]="N° Étudiant";
        colones[1]="Nom";
        colones[2]="Prénom";
        for (int i = 0; i < courseList.size(); i++) {
            colones[i+NBR_COMPOSANTS_ETUDIANTS]=courseList.get(i).getNom();
        }
    }

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

    private void setScores(int nbrColones) {
        scores=new String[NBR_LIGNES_CALCULS][nbrColones];

        //TODO Pas tres beau
        scores[0][0]="Note max";
        scores[0][1]="";
        scores[0][2]="";
        scores[1][0]="Note min";
        scores[1][1]="";
        scores[1][2]="";
        scores[2][0]="Note moyenne";
        scores[2][1]="";
        scores[2][2]="";
        scores[3][0]="Écart-type";
        scores[3][1]="";
        scores[3][2]="";

        //Note Max, Note Min et Moyenne
        for (int i = 0; i < courseList.size(); i++) {
            ArrayList<String> toolKit = MyTools.getStats(courseList.get(i),studentList);
            scores[0][i+NBR_COMPOSANTS_ETUDIANTS]=toolKit.get(0); //max
            scores[1][i+NBR_COMPOSANTS_ETUDIANTS]=toolKit.get(1); //min
            scores[2][i+NBR_COMPOSANTS_ETUDIANTS]=toolKit.get(2); //moy
            scores[3][i+NBR_COMPOSANTS_ETUDIANTS] = toolKit.get(3); //ecart type
        }

        //TODO substring c'est pas ouf car sa compte 4 chiffres apres la virgules sir c'est <10 et 3 chiffres si >=10
    }


    public String[] getColones() {
        return colones;
    }

    public String[][] getLignes() {
        return lignes;
    }

    public String[][] getScores() {
        return scores;
    }

    public int getNbrComposantsEtudiants() {
        return NBR_COMPOSANTS_ETUDIANTS;
    }
}