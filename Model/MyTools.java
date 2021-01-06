package Model;

import java.math.BigDecimal;
import java.math.MathContext;
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
     * @return Retourne un ArrayList de Float des différents statistiques
     */
    public static ArrayList<Float> getStats(ArrayList<Etudiant> studentlist,UE... ue){
        float max=0;
        float min=20;
        float somme=0;
        int nbrEtudiant=0;
        for (Etudiant e: studentlist) {
            float noteEtudiant;
            if(ue.length!=0){
                if(!(ue[0].calcNote(e).toString().equals("") || ue[0].calcNote(e).toString().equals("ABI"))){
                    nbrEtudiant=nbrEtudiant+1;
                    noteEtudiant=ue[0].calcNote(e).getFloatNote();
                    somme = somme+noteEtudiant;
                }
                else{
                    noteEtudiant=0;
                }
            }
            else{
                nbrEtudiant=nbrEtudiant+1;
                noteEtudiant=e.getP().getNoteProgramme(e);
                somme = somme+noteEtudiant;
            }
            if(max<noteEtudiant){
                max=noteEtudiant;
            }
            if(noteEtudiant!=0 && noteEtudiant<min){
                min=noteEtudiant;
            }
        }
        float moy = somme/nbrEtudiant;
        float sommeE=0;
        for (Etudiant e: studentlist) {
            if (e.getP()==null)continue;
            float noteEtudiant;
            if(ue.length!=0){
                if(!(ue[0].calcNote(e).toString().equals("") || ue[0].calcNote(e).toString().equals("ABI"))) {
                    noteEtudiant = ue[0].calcNote(e).getFloatNote();
                    sommeE = sommeE + (float) Math.pow(noteEtudiant / moy, 2);
                }
            }
            else{
                noteEtudiant = e.getP().getNoteProgramme(e);
                sommeE = sommeE + (float) Math.pow(noteEtudiant / moy, 2);
            }
        }
        ArrayList<Float> myArray = new ArrayList<>();

        myArray.add(max);
        myArray.add(min);
        myArray.add(arondit(moy));
        myArray.add(arondit(Math.sqrt(sommeE / (nbrEtudiant - 1))));
        return myArray;
    }

    /**
     * Arrondit les double a 4 chiffres apres la virgule.
     * @param f double
     * @return float
     */
    public static float arondit(double f){
        if(Double.isNaN(f) || Double.isInfinite(f)){
            return Float.NaN;
        }
        BigDecimal bigDecimal=new BigDecimal(f);
        MathContext mathContext =new MathContext(4);
        return bigDecimal.round(mathContext).floatValue();
    }
}

