package Model;

import java.util.HashMap;

public class Etudiant {
    private final int id;
    private final String nom;
    private final String prenom;
    private Programme p;
    HashMap<Cours, Note> notes = new HashMap<Cours,Note>();

    public Etudiant(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
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
}
