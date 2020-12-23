package Model;

public class Note {
    private String note;

    public Note(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }
    public float getFloatNote(){
        if(this.note.equals("ABI")) return 0;
        return Float.parseFloat(this.note);
    }

    @Override
    public String toString() {
        return "Note{" +
                "note='" + note + '\'' +
                '}';
    }
}
