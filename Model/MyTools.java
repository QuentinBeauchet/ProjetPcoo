package Model;

import java.util.ArrayList;

/**
 * Classe utilitaire permettant de regrouper des outils
 * que nous allons nous servir dans différents cas
 */
public class MyTools {
    /**
     * Calcule les statistiques min,max,moyenne et ecart-type d'une UE et d'une liste d'étudiants
     * @param ue le cours concerné
     * @param studentlist la liste d'étudiant
     * @return Retourne un ArrayList de String des différents statistiques
     */
    public static ArrayList<String> getStats(UE ue,ArrayList<Etudiant> studentlist){
        float max=0;
        float min=20;
        float somme=0;

        int nbrEtudiant=0;
        for (Etudiant e: studentlist
             ) {
            if(!(ue.calcNote(e).toString().equals("") || ue.calcNote(e).toString().equals("ABI"))){
                nbrEtudiant=nbrEtudiant+1;
                float noteEtudiant=ue.calcNote(e).getFloatNote();
                somme = somme+noteEtudiant;
                if(max<noteEtudiant){
                    max=noteEtudiant;
                }
                else if(noteEtudiant!=0 && noteEtudiant<min){
                    min=noteEtudiant;
                }
            }
        }
        float moy = somme/nbrEtudiant;
        float sommeE=0;
        for (Etudiant e: studentlist
        ) {
            if(!(ue.calcNote(e).toString().equals("") || ue.calcNote(e).toString().equals("ABI"))) {
                float noteEtudiant = ue.calcNote(e).getFloatNote();
                sommeE = sommeE + (float) Math.pow(noteEtudiant / moy, 2);
            }
        }
        ArrayList<String> myArray = new ArrayList<>();
        myArray.add(String.valueOf(max));
        myArray.add(String.valueOf(min));
        myArray.add(String.format("%.4f", moy));
        myArray.add(String.format("%.4f",Math.sqrt(sommeE / (nbrEtudiant - 1))));
        return  myArray;
    }

    /**
     * Meme fonction que getStats sauf que l'array rendu est en Double
     * @param ue le cours concerné
     * @param studentlist la liste d'étudiant
     * @return Retourne un ArrayList de Double des différents statistiques
     */
    public static ArrayList<Double> getDoubleStats (UE ue,ArrayList<Etudiant> studentlist){
        ArrayList<Double> myDoublesArr = new ArrayList<>();
        for (String s: getStats(ue,studentlist)
             ) {
            myDoublesArr.add(Double.parseDouble(s.replaceAll(",",".")));
        }
        return myDoublesArr;
    }
}
