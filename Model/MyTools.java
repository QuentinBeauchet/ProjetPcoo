package Model;

import java.util.ArrayList;

public class MyTools {

    public static ArrayList<String> getStats(UE ue,ArrayList<Etudiant> studentlist){
        float max=0;
        float min=20;
        float somme=0;

        int nbrEtudiant=0;
        for (Etudiant e: studentlist
             ) {
            if(!ue.calcNote(e).getNote().equals("")){
                nbrEtudiant=nbrEtudiant+1;
                float noteEtudiant=ue.calcNote(e).getIntNote();
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
            if(!ue.calcNote(e).getNote().equals("")) {
                float noteEtudiant = ue.calcNote(e).getIntNote();
                sommeE = sommeE + (float) Math.pow(noteEtudiant / moy, 2);
            }
        }
        ArrayList<String> myArray = new ArrayList<>();
        myArray.add(String.valueOf(max));
        myArray.add(String.valueOf(min));
        myArray.add(String.valueOf(moy).substring(0,6));
        myArray.add(String.valueOf(Math.sqrt(sommeE / (nbrEtudiant - 1))).substring(0,6));
        return  myArray;
    }
}
