package Exceptions;

import Model.Etudiant;

public abstract class EtudiantException extends RuntimeException {
    Etudiant e;

    EtudiantException(Etudiant e,String message){
        super(message);
        this.e=e;
    }

    public Etudiant getE() {
        return e;
    }
}
