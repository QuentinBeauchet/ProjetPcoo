package Exceptions;

import Model.Etudiant;

public class NameEtudiantInvalidException extends EtudiantException {
    public NameEtudiantInvalidException(Etudiant e) {
        super(e, "le nom :\""+e.getNom()+ "\" de l'etudiant : "+ e.getId() +" est invalide." );
    }
}
