package Exceptions;

import Model.UE;

public class IdUeDuplicationException extends UEException {

    public IdUeDuplicationException(UE ue) {
        super(ue,"L'id de : " +ue + " existe déjà.");
    }
}
