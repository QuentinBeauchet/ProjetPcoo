package Exceptions;

import Model.Etudiant;

public class IdEtudiantDuplicationException extends EtudiantException {

    public IdEtudiantDuplicationException(Etudiant e) {
        super(e,"L'id de : "+e+" existe déjà");
    }
}
