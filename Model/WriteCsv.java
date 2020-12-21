package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteCsv {
    private XMLReader xmlReader;

    public WriteCsv(XMLReader xmlReader) {
        this.xmlReader = xmlReader;

    }
    public void printCSV(Programme p){
        String path = "data/"+p.getNom().replace(" ","_")+".csv";
        try (PrintWriter writer = new PrintWriter(new File(path))) {

            StringBuilder sb = new StringBuilder();
            sb.append("\"Numero Etudiant\",\"Nom\",\"Prenom\",");           //entete
            sb.append("\"").append(p.getBlocs().get(0).getNom()).append("\"");
            for (int i = 1; i < p.getBlocs().size() ; i++) {                //enteteCours
                sb.append(",").append("\"").append(p.getBlocs().get(i).getNom()).append("\"");
            }
            sb.append("\n");

            for (Etudiant e: xmlReader.getStudentList()                     //Etudiants
                 ) {
                if(p.equals(e.getP())){
                    etudiantCol(e,sb);
                }
            }
            sb.append("\n");

            sb.append("\"").append("Note max").append("\",\"\",").append("\"\",");
            sb.append("\"").append(findNoteMax(p.getBlocs().get(0),p)).append("\"");        //Notes Max
            for (int i = 1; i < p.getBlocs().size() ; i++) {
                sb.append(",").append("\"").append(findNoteMax(p.getBlocs().get(i),p)).append("\"");
            }
            sb.append("\n");

            sb.append("\"").append("Note min").append("\",\"\",").append("\"\",");
            sb.append("\"").append(findNoteMin(p.getBlocs().get(0),p)).append("\"");        //Notes min
            for (int i = 1; i < p.getBlocs().size() ; i++) {
                sb.append(",").append("\"").append(findNoteMin(p.getBlocs().get(i),p)).append("\"");
            }
            sb.append("\n");


            writer.write(sb.toString());

            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private void etudiantCol (Etudiant e, StringBuilder sb){
        sb.append("\"").append(e.getId()).append("\",")
                .append("\"").append(e.getNom()).append("\",")
                .append("\"").append(e.getPrenom()).append("\",");
        sb.append("\"").append(e.getP().getBlocs().get(0).calcNote(e).getNote()).append("\"");
        for (int i = 1; i < e.getP().getBlocs().size() ; i++ ) {
            sb.append(",\"").append(e.getP().getBlocs().get(i).calcNote(e).getNote()).append("\"");
        }
        sb.append("\n");
    }
    private float findNoteMax(UE ue, Programme p){
        float max = 0;
        for (Etudiant e: xmlReader.getStudentList()
             ) {
            if(p.equals(e.getP())){
                float current = ue.calcNote(e).getIntNote();
                if(max < current)max = current;
            }
        }
        return max;
    }

    private float findNoteMin(UE ue, Programme p){
        float min = 20;
        for (Etudiant e: xmlReader.getStudentList()
        ) {
            if(p.equals(e.getP())){
                float current = ue.calcNote(e).getIntNote();
                if(min > current)min = current;
            }
        }
        return min;
    }

}
