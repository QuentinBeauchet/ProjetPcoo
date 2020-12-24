package Model;

import java.util.ArrayList;

public class BlocSimple extends Bloc {
    private UE ue;
    public BlocSimple(UE ue) {
        super(ue.getId(),ue.getNom());
        this.ue=ue;
    }
    @Override
    public int getCoef(){
        return this.ue.getCoef();
    }

    @Override
    public Note calcNote(Etudiant e) {
        return ue.calcNote(e);
    }

    @Override
    public String toString() {
        return "BlocSimple{" +
                "ue=" + ue +
                '}';
    }

    public ArrayList<UE> getUE(){
        ArrayList<UE> ue= new ArrayList<UE>();
        ue.add(this.ue);
        return ue;
    }
}