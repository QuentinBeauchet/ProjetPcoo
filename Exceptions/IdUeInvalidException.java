package Exceptions;

import Model.UE;

public class IdUeInvalidException extends UEException {
    public IdUeInvalidException(UE ue) {
        super(ue,"Id ue incorrect.");
    }
}
