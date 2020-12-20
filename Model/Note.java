package Model;

public class Note {
    private String note;

    public Note(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }
    public float getIntNote(){
        if(this.note.equals("ABI")) return 0;
        return Integer.parseInt(this.note);
    }

    @Override
    public String toString() {
        return "Note{" +
                "note='" + note + '\'' +
                '}';
    }
}
