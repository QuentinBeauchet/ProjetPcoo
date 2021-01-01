package Model;

/**
 * Un bloc à options est composé de plusieurs cours portant le même nombre de crédits.
 * C'est aussi le nombre de crédits du bloc.
 */
public class BlocOptions extends BlocMultiple {
    private final int coef;

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
            Cours cour=(Cours)ue;
            if(e.getNotes().get(cour) != null){
                if (nMax.getFloatNote() < e.getNotes().get(cour).getFloatNote()){
                    nMax = e.getNotes().get(cour);
                }
                if (!(e.getNotes().get(cour).toString().equals("ABI"))) {
                    isBlocABI = false;
                }
            }
        }
        if(isBlocABI){
            return new Note("ABI");
        }
        else{
            return new Note(nMax.toString());
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
