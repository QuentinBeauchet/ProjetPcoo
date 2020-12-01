package Model;

public class BlocSimple extends Bloc {
    private UE ue;
    public BlocSimple(int id,String nom,UE ue) {
        super(id,nom);
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
    public String toString() {
        return "BlocSimple{" +
                "ue=" + ue +
                '}';
    }
}
