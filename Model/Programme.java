package Model;

import java.util.ArrayList;
import java.util.Objects;

public class Programme {
    private ArrayList<Bloc> blocs = new ArrayList<>();
    private String nom;
    private String id;

    public Programme(String nom, String id) {
        this.nom = nom;
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Bloc> getBlocs() {
        return blocs;
    }

    public void add(Bloc b){
        this.blocs.add(b);
    }

    public boolean isValidated(Etudiant e){
        int somme=0;
        int total=0;
        for (Bloc b : this.blocs
             ) {

            somme += b.getCoef() * b.calcNote(e).getIntNote();
            total += b.getCoef();
        }
        return (somme/total) >= 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programme programme = (Programme) o;
        return Objects.equals(id, programme.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.id+" : "+this.nom;
    }
}
