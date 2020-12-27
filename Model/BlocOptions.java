package Model;

import java.util.ArrayList;

public class BlocOptions extends BlocMultiple {
    private int coef;

    public BlocOptions(String id, int coef, String nom) {
        super(id,nom);
        this.coef = coef;
    }

    @Override
    public void add(UE ue){
        if(ue.getCoef() == this.getCoef()){
            this.listUe.add(ue);
        }
    }

    @Override
    public String nature() {
        return "Bloc Option ";
    }

    @Override
    public int getCoef() {
        return this.coef;
    }

    @Override
    public Note calcNote(Etudiant e) {
         Note nMax = new Note("0");
        for (int i = 0; i < this.listUe.size() ; i++) {
            if(e.getNotes().get(this.listUe.get(i)) != null
                    && nMax.getFloatNote() < e.getNotes().get(this.listUe.get(i)).getFloatNote())
                nMax = e.getNotes().get(this.listUe.get(i));
        }
        return new Note(nMax.getNote());
    }

    @Override
    public void toXml(StringBuilder sb) {
        sb.append("        <option>\n")
                .append("            <identifier>").append(this.getId()).append("</identifier>\n")
                .append("            <name>").append(this.getNom()).append("</name>\n");
        for (UE ue: this.getUE()
        ) {
           sb.append("            <item>").append(ue.getId()).append("</item>\n");
        }
        sb.append("        </option>\n");
    }
}
