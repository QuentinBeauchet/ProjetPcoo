package Exceptions;

public class LookAndFeelException extends NullPointerException {

    //TODO pas sur que ce soit la bonne exception a extends

    /**
     * Lancé quand il y a un probleme de Look&Feel.
     */

    public LookAndFeelException(){
        System.out.println("Le Programme a rencontré un problème lors du réglage de son Look&Feel.");
    }

}
