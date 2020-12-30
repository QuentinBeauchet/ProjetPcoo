package Model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Classe qui permet de convertir des données en CSV
 */
public class WriteCsv {
    private final XMLReader xmlReader;

    public WriteCsv(XMLReader xmlReader) {
        this.xmlReader = xmlReader;

    }

    /**
     * Ecris un CSV à partir d'un Programme
     * @param p le programme
     */
    public void printCSV(Programme p){
        String path = "data/"+p.getNom().replace(" ","_")+".csv";
        try (PrintWriter writer = new PrintWriter(path)) {

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

            StringBuilder lineMax = new StringBuilder().append("\"").append("Note max").append("\",\"\",").append("\"\",");
            StringBuilder lineMin = new StringBuilder().append("\"").append("Note min").append("\",\"\",").append("\"\",");
            StringBuilder lineMoy = new StringBuilder().append("\"").append("Moyenne").append("\",\"\",").append("\"\",");
            StringBuilder lineEcart = new StringBuilder().append("\"").append("Ecart-type").append("\",\"\",").append("\"\",");
            for (int i = 0; i < p.getBlocs().size();++i
                 ) {
                UE ue = p.getBlocs().get(i);
                ArrayList<String> myVars = MyTools.getStats(ue,xmlReader.getStudentList());
                lineMax.append("\"").append(myVars.get(0)).append("\"");
                lineMin.append("\"").append(myVars.get(1)).append("\"");
                lineMoy.append("\"").append(myVars.get(2)).append("\"");
                lineEcart.append("\"").append(myVars.get(3)).append("\"");
                if(i==p.getBlocs().size()-1){
                    lineMax.append("\n");
                    lineMin.append("\n");
                    lineMoy.append("\n");
                    lineEcart.append("\n");
                }else{
                    lineMax.append(",");
                    lineMin.append(",");
                    lineMoy.append(",");
                    lineEcart.append(",");
                }
            }
            sb.append(lineMax.toString()).append(lineMin.toString()).append(lineMoy.toString()).append(lineEcart.toString());

            writer.write(sb.toString());

            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Ecris la ligne dans le StringBuilder
     * @param e l'etudiant
     * @param sb le StringBuilder
     */
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

}
