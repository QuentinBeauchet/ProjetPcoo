package Model;

import java.util.ArrayList;

/**
 * Un bloc simple est juste un cours.
 */
public class BlocSimple extends Bloc {
    private UE ue;
    public BlocSimple(UE ue) {
        super(ue.getId(),ue.getNom());
        this.ue=ue;
    }
    @Override
    public int getCoef(){
        return this.ue.getCoef();
    }

    @Override
    public Note calcNote(Etudiant e) {
        return ue.calcNote(e);
    }

    @Override
    public void toXml(StringBuilder sb) {
        sb.append("        <item>").append(this.getId()).append("</item>\n");
    }

    @Override
    public String toString() {
        return "BlocSimple{" +
                "ue=" + ue +
                '}';
    }
    @Override
    public ArrayList<UE> getUE(){
        ArrayList<UE> ue= new ArrayList<>();
        ue.add(this.ue);
        return ue;
    }
}
