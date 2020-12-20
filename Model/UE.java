package Model;

public interface UE {
    String getId();
    int getCoef();
    String getNom();
    String toString();
    Note calcNote(Etudiant e);

    default boolean equals(UE ue){
        return ue.getId().equals(this.getId());
    }
}
