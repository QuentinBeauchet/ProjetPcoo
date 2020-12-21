package Exceptions;

import Model.Etudiant;

public class IdEtudiantDuplicationException extends RuntimeException {
    Etudiant e;

    public IdEtudiantDuplicationException(Etudiant e) {
        super("L'id de : "+e+" existe déjà");
        this.e = e;
    }
}
