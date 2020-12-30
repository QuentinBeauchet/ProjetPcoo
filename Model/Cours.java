package Model;

import Exceptions.*;

/**
 * Un cours est l'unité d'enseignement de base d'une université.
 */
public class Cours implements UE{
    private String id;
    private int coef;
    private String nom;

    public Cours(String id, int coef, String nom) {
        this.id = id;
        this.coef = coef;
        this.nom = nom;
        if(id.equals(""))throw new IdUeInvalidException(this);
        if(nom.equals(""))throw new NameUeInvalidException(this);
        if(coef< 0)throw new CoefUeInvalidException(this);
    }
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getCoef() {
        return this.coef;
    }

    @Override
    public String getNom() {
        return this.nom;
    }



    @Override
    public String toString() {
        return this.nom+" "+this.id+" ("+this.coef+")";
    }

    @Override
    public Note calcNote(Etudiant e) {
        if(e.getNotes().get(this)==null)return new Note("");
        return e.getNotes().get(this);
    }

    @Override
    public void toXml(StringBuilder sb) {
        sb.append("    <course>\n")
                .append("        <identifier>").append(this.getId()).append("</identifier>\n")
                .append("        <name>").append(this.getNom()).append("</name>\n")
                .append("        <credits>").append(this.getCoef()).append("</credits>\n")
                .append("    </course>\n");
    }

}
