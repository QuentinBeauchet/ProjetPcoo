package Exceptions;

import Model.Etudiant;

public class SurnameEtudiantInvalidException extends EtudiantException {
    public SurnameEtudiantInvalidException(Etudiant e) {
        super(e,  "le prenom :\""+e.getPrenom()+ "\" de l'etudiant : "+ e.getId() +" est invalide.");
    }
}
