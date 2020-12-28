package Exceptions;

import Model.Programme;

public class IdProgramInvalidException extends ProgramException {
    public IdProgramInvalidException(Programme p) {
        super(p,"Id de programme invalide.");
    }
}
