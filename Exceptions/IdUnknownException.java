package Exceptions;

public class IdUnknownException extends RuntimeException {
    public IdUnknownException(String id){
        super("l'id : "+id + " n'existe pas.");
    }
}
