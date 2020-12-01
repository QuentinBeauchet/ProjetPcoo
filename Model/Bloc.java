package Model;

public abstract class Bloc implements UE {
    private int id;
    private String nom;
    public Bloc(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    @Override
    public int getId() {
        return this.id;
    }


    @Override
    public String getNom() {
        return this.nom;
    }



}
