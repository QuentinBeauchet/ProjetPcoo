package Model;

public class Cours implements UE{
    private int id;
    private int coef;
    private String nom;

    @Override
    public int getId() {
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

    public Cours(int id, int coef, String nom) {
        this.id = id;
        this.coef = coef;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.nom+" "+this.id;
    }

    @Override
    public Note calcNote(Etudiant e) {
        if(e.getNotes().get(this)==null)return new Note("ABI");
        return e.getNotes().get(this);
    }
}
