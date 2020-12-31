package Model;

/**
 * Classe modélisant la Note attribuée à un étudiant
 */
public class Note {
    private final String note;

    public Note(String note) {
        this.note = note;
    }

    /**
     * getteur de la note au format String
     * @return la note en String
     */
    public String getNote() {
        return note;
    }

    /**
     * getteur de la note au format float
     * @return float
     */
    public float getFloatNote(){
        if(this.note.equals("ABI")) return 0;
        return Float.parseFloat(this.note);
    }

    @Override
    public String toString() {
        /*
        return "Note{" +
                "note='" + note + '\'' +
                '}';
         */
        return note;
    }

    public Note getThisNote(){
        return this;
    }

}
