package Model;

import java.util.ArrayList;

public class BlocComposite extends BlocMultiple {
    public BlocComposite(String id, String nom) {
        super(id, nom);
    }

    @Override
    public String nature() {
        return "Bloc composite ";
    }

    @Override
    public Note calcNote(Etudiant e) {
        int somme=0;
        int total=0;
        for (UE ue: this.listUe
        ) {
            System.out.println(ue);
            somme += ue.getCoef()*ue.calcNote(e).getIntNote();
            total += ue.getCoef();
        }
        System.out.println((somme/total));
        return new Note(String.valueOf(somme/total));
    }

    @Override
    public int getCoef() {
        int coef=0;
        for (UE ue: this.listUe
             ) {
            coef+= ue.getCoef();
        }
        return coef;
    }
}
