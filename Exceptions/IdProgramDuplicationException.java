package Exceptions;

import Model.Programme;

public class IdProgramDuplicationException extends RuntimeException {
    Programme p;

    public IdProgramDuplicationException(Programme p) {
        super("L'id de : "+p+" existe déjà");
        this.p = p;
    }
}
