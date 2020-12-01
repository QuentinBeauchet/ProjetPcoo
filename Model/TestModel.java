package Model;

public class TestModel {
    public static void main(String[] args) {
        Etudiant e = new Etudiant(1546,"MASSE","Gillian");

        Cours c1 = new Cours(45,3,"geo");
        Cours c2= new Cours(516,5,"math");

        BlocComposite b = new BlocComposite(452,"science");
        Programme p = new Programme("Mathsup",8456);

        b.add(c1);
        b.add(c2);

        p.add(b);

        e.addNote(c1,new Note("15"));
        e.addNote(c2,new Note("10"));

        System.out.println(p.isValidated(e));

    }
}
