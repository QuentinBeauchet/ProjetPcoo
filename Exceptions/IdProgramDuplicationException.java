package Exceptions;

import Model.Programme;

public class IdProgramDuplicationException extends ProgramException {
    public IdProgramDuplicationException(Programme p) {
        super(p,"L'id de : "+p+" existe déjà");
    }
}
