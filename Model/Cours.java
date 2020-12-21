package Model;

public class Cours implements UE{
    private String id;
    private int coef;
    private String nom;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getCoef() {
        return this.coef;
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    public Cours(String id, int coef, String nom) {
        this.id = id;
        this.coef = coef;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.nom+" "+this.id+" ("+this.coef+")";
    }

    @Override
    public Note calcNote(Etudiant e) {
        if(e.getNotes().get(this)==null)return new Note("");
        return e.getNotes().get(this);
    }

}
