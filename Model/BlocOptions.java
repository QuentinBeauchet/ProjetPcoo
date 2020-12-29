package Model;

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
        boolean isBlocABI=true;
        for (UE ue : this.listUe) {
            if (e.getNotes().get(ue) != null
                    && nMax.getFloatNote() < e.getNotes().get(ue).getFloatNote())
                nMax = e.getNotes().get(ue);
            if (!(e.getNotes().get(ue).getNote().equals("ABI"))) {
                isBlocABI = false;
            }
        }
        if(isBlocABI){
            return new Note("ABI");
        }
        else{
            return new Note(nMax.getNote());
        }
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
