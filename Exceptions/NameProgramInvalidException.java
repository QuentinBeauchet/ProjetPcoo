package Exceptions;

import Model.Programme;

public class NameProgramInvalidException extends ProgramException {
    public NameProgramInvalidException(Programme p) {
        super(p,  "le nom :\""+p.getNom()+ "\" du programme : "+ p.getId() +" est invalide.");
    }
}
