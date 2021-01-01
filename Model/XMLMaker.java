package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe permettant de creer un xml
 */
public class XMLMaker {
    private final ArrayList<Programme> programList;
    private final ArrayList<Etudiant> studentList;
    private final ArrayList<Cours> courseList;

    public XMLMaker(XMLReader xml){
        programList = xml.getProgramList();
        studentList = xml.getStudentList();
        courseList = xml.getCourseList();
        try {
            writer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ecriture du fichier
     * @throws IOException echec d'ecriture
     */
    private void writer() throws IOException {
        FileWriter myWriter = new FileWriter("data/save.xml");
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\"?>\n").append("<data>\n");
        for (Cours c: courseList
             ) {
            c.toXml(sb);
        }
        for (Programme p: programList
             ) {
            p.toXml(sb);
        }
        for (Etudiant e: studentList
             ) {
            e.toXml(sb);
        }
        sb.append("</data>");
        myWriter.write(sb.toString());
        myWriter.close();
    }
}
