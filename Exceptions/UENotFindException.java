package Exceptions;

import Model.UE;

public class UENotFindException extends UEException{

    public UENotFindException(UE ue) {
        super(ue, "L'ue "+ue.getNom()+ " n'a pas été trouvée");
    }
}
