package Exceptions;

import Model.UE;

public abstract class UEException extends RuntimeException {

    UE ue;
    UEException(UE ue,String message){
        super(message);
        this.ue=ue;
    }

    public UE getUe() {
        return ue;
    }
}
