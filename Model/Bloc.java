package Model;

import Exceptions.*;

import java.util.ArrayList;

/**
 * Classe abstraite qui nous permet de définir un bloc avec ces fonctions de base
 */
public abstract class Bloc implements UE {
    private String id;
    private String nom;
    public Bloc(String id, String nom) {
        this.id = id;
        this.nom = nom;
        if(id.equals(""))throw new IdUeInvalidException(this);
        if(nom.equals(""))throw new NameUeInvalidException(this);
    }

    /**
     * Getter d'un id
     * @return id d'un bloc
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Getter d'un nom
     * @return nom d'un bloc
     */
    @Override
    public String getNom() {
        return this.nom;
    }

    /**
     * getter abstrait récupérant la liste des UE
     * @return la liste d'ue
     */
    public abstract ArrayList<UE> getUE();


}
