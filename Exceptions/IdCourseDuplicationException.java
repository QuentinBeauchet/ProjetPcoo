package Exceptions;

import Model.Cours;

public class IdCourseDuplicationException extends RuntimeException {
    Cours cours;

    public IdCourseDuplicationException(Cours cours) {
        super("L'id de : "+cours+" existe déjà");
        this.cours = cours;
    }
}
