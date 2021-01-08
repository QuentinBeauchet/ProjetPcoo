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
        for(Programme p: xmlReader.getProgramList()){
            printCSV(p);
        }
        System.out.println("CSV enregistrés.");
    }

    /**
     * Ecris un CSV à partir d'un Programme
     * @param p le programme
     */
    public void printCSV(Programme p){
        String path = "data/"+p.getNom().replace(" ","_")+".csv";
        try (PrintWriter writer = new PrintWriter(path)) {
            StringBuilder sb = new StringBuilder();
            sb.append("\"Numero Etudiant\",\"Nom\",\"Prenom\",\"").append(p.getNom()).append("\"");           //entete
            for ( Bloc b : p.getBlocs()
                 ) {
                b.toCSVTtitle(sb);
            }
            sb.append("\n");
            for (Etudiant e: xmlReader.getStudentList()                     //Etudiants
                 ) {
                if(p.equals(e.getP())){
                    etudiantCol(e,sb,p);
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
                ArrayList<Float> myVars = MyTools.getStats(xmlReader.getStudentList(),ue);
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


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Ecris la ligne dans le StringBuilder
     * @param e l'etudiant
     * @param sb le StringBuilder
     * @param p le programme de l'élève
     */
    private void etudiantCol (Etudiant e, StringBuilder sb,Programme p){
        sb.append("\"").append(e.getId()).append("\",")
                .append("\"").append(e.getNom()).append("\",")
                .append("\"").append(e.getPrenom()).append("\",")
                .append("\"").append(p.getNoteProgramme(e)).append("\"");
        for (Bloc b: p.getBlocs()
             ) {
            b.toCsvMoy(sb,e);
        }
        sb.append("\n");
    }


}
