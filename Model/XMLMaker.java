package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class XMLMaker {
    private ArrayList<Programme> programList;
    private ArrayList<Etudiant> studentList;
    private ArrayList<Cours> courseList;

    public XMLMaker(XMLReader xml){
        programList = xml.getProgramList();
        studentList = xml.getStudentList();
        courseList = xml.getCourseList();
        try {
            writer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        timer();
    }

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



    private void timer(){
        long acc=0;
        for (int i = 0; i < 100; i++) {
            long t1 = System.currentTimeMillis();
            try {
                writer();
            } catch (IOException e) {
                e.printStackTrace();
            }
            acc=acc+System.currentTimeMillis()-t1;
        }
        System.out.println(acc/100+"."+acc%100+"ms");
    }
}
