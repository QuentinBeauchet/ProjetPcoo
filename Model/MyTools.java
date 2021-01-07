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

    /**
     * Teste si un id existe déjà parmi les ues existants
     * @param ues la liste des ue
     * @param id l'id à tester
     * @return boolean vrai si trouvé
     */
    public static boolean isIdUEAlreadyExist(ArrayList<UE> ues, String id){
        for (UE ue: ues){
            if(ue.getId().equals(id))return true;
        }
        return false;
    }
    /**
     * Teste si un id existe déjà parmi les blocs existants
     * @param blocs la liste des blocs
     * @param id l'id à tester
     * @return boolean vrai si trouvé
     */
    public static boolean isIdBlocAlreadyExist(ArrayList<Bloc> blocs, String id){
        for (Bloc b : blocs){
            if(b.getId().equals(id))return true;
        }
        return false;
    }

    /**
     * Teste si un id existe déjà parmi les cours existants
     * @param cours la liste des cours
     * @param id l'id à tester
     * @return boolean vrai si trouvé
     */
    public static boolean isIdCourseAlreadyExist(ArrayList<Cours> cours, String id){
        for (Cours c: cours
        ) {
            if(c.getId().equals(id))return true;
        }
        return false;
    }

    /**
     * Teste si un id existe déjà parmi les programmes existants
     * @param programs la liste des programmes
     * @param id l'id à tester
     * @return boolean vrai si trouvé
     */
    public static boolean isIdProgramAlreadyExist(ArrayList<Programme> programs,String id){
        for (Programme p: programs
        ) {
            if(p.getId().equals(id))return true;
        }
        return false;
    }

    /**
     * Teste si un id existe déjà parmi les etudiants existants
     * @param students la liste des etudiants
     * @param id l'id à tester
     * @return boolean vrai si trouvé
     */
    public static boolean isIdEtudiantAlreadyExist(ArrayList<Etudiant> students ,String id){
        for (Etudiant e: students
        ) {
            if(id.equals(e.getId()))return true;
        }
        return false;
    }
}

