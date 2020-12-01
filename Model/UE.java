package Model;

public interface UE {
    int getId();
    int getCoef();
    String getNom();
    String toString();
    Note calcNote(Etudiant e);
}
