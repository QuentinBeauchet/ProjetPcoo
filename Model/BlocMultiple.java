package Model;

import java.util.ArrayList;

public abstract class BlocMultiple  extends Bloc {
    protected ArrayList<UE> listUe = new ArrayList<>();

    public BlocMultiple(String id, String nom) {
        super(id, nom);
    }

    public void add(UE ue){
        this.listUe.add(ue);
    }

    public abstract String nature ();
    @Override
    public String toString() {
        return nature()+" : "+this.getId()+" "+ getNom() +"{" +
                "listUe=" + listUe +
                '}';
    }
}
