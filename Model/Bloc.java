package Model;

public abstract class Bloc implements UE {
    private String id;
    private String nom;
    public Bloc(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    @Override
    public String getId() {
        return this.id;
    }


    @Override
    public String getNom() {
        return this.nom;
    }



}
