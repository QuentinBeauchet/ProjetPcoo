package Model;

import Exceptions.*;

import java.util.ArrayList;
import java.util.Objects;

public class Programme {
    private ArrayList<Bloc> blocs = new ArrayList<>();
    private String nom;
    private String id;

    public Programme(String nom, String id) {
        this.nom = nom;
        this.id = id;
        if(id.equals(""))throw new IdProgramInvalidException(this);
        if(nom.equals(""))throw new NameProgramInvalidException(this);
    }

    public String getNom() {
        return nom;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Bloc> getBlocs() {
        return blocs;
    }

    public void add(Bloc b){
        this.blocs.add(b);
    }

    public boolean isValidated(Etudiant e){
        int somme=0;
        int total=0;
        for (Bloc b : this.blocs
             ) {

            somme += b.getCoef() * b.calcNote(e).getFloatNote();
            total += b.getCoef();
        }
        return (somme/total) >= 10;
    }

    public void toXml(StringBuilder sb){
        sb.append("    <program>\n")
                .append("        <identifier>").append(this.getId()).append("</identifier>\n")
                .append("        <name>").append(this.getNom()).append("</name>\n");
        for (Bloc b: this.getBlocs()
             ) {
            b.toXml(sb);
        }
        sb.append("    </program>\n");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programme programme = (Programme) o;
        return Objects.equals(id, programme.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.id+" : "+this.nom;
    }

}
