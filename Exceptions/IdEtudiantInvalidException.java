package Exceptions;

import Model.Etudiant;

public class IdEtudiantInvalidException extends EtudiantException {

    public IdEtudiantInvalidException(Etudiant e) {
        super(e, "L'id de l'etudiant est invalide.");
    }
}
