package Model;

/**
 * Un bloc composite est composé de plusieurs cours.
 * Le nombre de crédits du bloc est la somme des crédits des UE qui le composent.
 */
public class BlocComposite extends BlocMultiple {

    public BlocComposite(String id, String nom) {
        super(id, nom);
    }

    @Override
    public String nature() {
        return "Bloc composite ";
    }

    @Override
    public Note calcNote(Etudiant e) {
        int somme=0;
        int total=0;
        for (UE ue: this.listUe
        ) {
            somme += ue.getCoef()*ue.calcNote(e).getFloatNote();
            total += ue.getCoef();
        }
        return new Note(String.valueOf(somme/total));
    }
    @Override
    public void toXml(StringBuilder sb) {
        sb.append("        <composite>\n")
                .append("            <identifier>").append(this.getId()).append("</identifier>\n")
                .append("            <name>").append(this.getNom()).append("</name>\n");
        for (UE ue: this.getUE()
             ) {
            sb.append("            <item>").append(ue.getId()).append("</item>\n");
        }
        sb.append("        </composite>\n");
    }

    @Override
    public int getCoef() {
        int coef=0;
        for (UE ue: this.listUe
             ) {
            coef+= ue.getCoef();
        }
        return coef;
    }
}
