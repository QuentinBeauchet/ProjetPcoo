package Model;

public class Etudiant {
    private static int idCurrent=0;
    private final int id;
    private final String nom;
    private final String prenom;

    public Etudiant( String nom, String prenom) {
        this.id = Etudiant.idCurrent;
        Etudiant.idCurrent++;
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
}
