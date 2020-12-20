package Model;

import java.util.HashMap;

public class Etudiant {
    private final String id;
    private final String nom;
    private final String prenom;
    private Programme p;
    HashMap<Cours, Note> notes = new HashMap<Cours,Note>();

    public Programme getP() {
        return p;
    }

    public Etudiant(String id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void inscris(Programme p){
        this.p =p;
    }

    public HashMap<Cours, Note> getNotes() {
        return notes;
    }

    public void addNote(Cours cours , Note n){
        notes.put(cours,n);
    }
    public boolean isPresent(Cours cours){
        return this.notes.get(cours) !=null;
    }

    @Override
    public String toString() {
        String program = "pas de programme";
        if(this.p != null) program = this.getP().getId();
        return this.getId()+" : "+this.getNom()+" "+this.getPrenom()+" -> "+ program;
    }
}
