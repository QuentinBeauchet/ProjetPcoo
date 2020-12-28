package Exceptions;

import Model.UE;

public class CoefUeInvalidException extends UEException {
    public CoefUeInvalidException(UE ue) {
        super(ue, "le coef : "+ue.getCoef()+"  de l'UE : "+ ue.getId() +" est invalide.");
    }
}
