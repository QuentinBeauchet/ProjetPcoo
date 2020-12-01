package Model;

import java.util.ArrayList;

public abstract class BlocMultiple  extends Bloc {
    protected ArrayList<UE> listUe = new ArrayList<>();

    public BlocMultiple(int id, String nom) {
        super(id, nom);
    }

    public void add(UE ue){
        this.listUe.add(ue);
    }

    @Override
    public String toString() {
        return "BlocMultiple{" +
                "listUe=" + listUe +
                '}';
    }
}
