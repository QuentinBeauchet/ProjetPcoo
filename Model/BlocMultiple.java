package Model;

import Exceptions.IdUeDuplicationException;
import Exceptions.ProgramException;

import java.util.ArrayList;

/**
 * Classe abstraite représentant un bloc multiple
 */
public abstract class BlocMultiple  extends Bloc {
    protected ArrayList<UE> listUe = new ArrayList<>();


    public BlocMultiple(String id, String nom) {
        super(id, nom);
    }

    /**
     * créer un bloc et l'ajoute à la liste des blocs d'un programme
     * @param id  du bloc
     * @param nom du bloc
     * @param p programme
     */
    public BlocMultiple(String id, String nom, Programme p){
        super(id, nom);
        if(MyTools.isIdBlocAlreadyExist(p.getBlocs(),id))throw new IdUeDuplicationException(this);
        p.add(this);
    }

    /**
     * Ajoute un élément à la liste des UEs
     * @param ue à ajouter
     */
    public void add(UE ue){
        if(MyTools.isIdUEAlreadyExist(getUE(),ue.getId()))throw new IdUeDuplicationException(ue);
        this.listUe.add(ue);
    }

    /**
     * Retourne une string comportant la nature du bloc multiple (composite ou option)
     * @return String
     */
    public abstract String nature ();

    @Override
    public String toString() {
        return getId()+" - "+getNom();
    }

    @Override
    public ArrayList<UE> getUE(){
        return listUe;
    }
}
