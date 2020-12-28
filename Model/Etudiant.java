package Model;

import java.util.HashMap;
import java.util.Map;

public class Etudiant {
    private final String id;
    private final String nom;
    private final String prenom;
    private Programme p;
    HashMap<Cours, Note> notes = new HashMap<Cours,Note>();

    public Programme getP() {
        return p;
    }

    public Etudiant(String id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void inscris(Programme p){
        this.p =p;
    }

    public boolean estInscris(Programme p){
        if(p.getId().equals(this.p.getId())){
            return true;
        }
        return false;
    }

    public HashMap<Cours, Note> getNotes() {
        return notes;
    }

    public void addNote(Cours cours , Note n){
        notes.put(cours,n);
    }
    public boolean isPresent(Cours cours){
        return this.notes.get(cours) !=null;
    }
    public void toXml(StringBuilder sb){
        sb.append("    <student>\n")
                .append("        <identifier>").append(this.getId()).append("</identifier>\n")
                .append("        <name>").append(this.getNom()).append("</name>\n")
                .append("        <surname>").append(this.getPrenom()).append("</surname>\n")
                .append("        <program>").append(this.getP().getNom()).append("</program>\n");
        for(Map.Entry<Cours, Note> entry : this.getNotes().entrySet()) {
            Cours key = entry.getKey();
            Note value = entry.getValue();
            sb.append("        <grade>\n")
                    .append("            <item>").append(key.getId()).append("</item>\n")
                    .append("            <value>").append(value.getNote()).append("</value>\n")
                    .append("        </grade>\n");

        }
                sb.append("    </student>\n");
    }

    @Override
    public String toString() {
        String program = "aucun";
        if(this.p != null) program = this.getP().getId();
        return this.getId()+" : "+this.getNom()+" "+this.getPrenom()+" -> Programe :"+ program;
    }
}
