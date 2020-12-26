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
        myWriter.write("<?xml version=\"1.0\"?>\n");
        myWriter.write("<data>\n");

        writeCours(myWriter);
        writeProgrammes(myWriter);
        writeEtudiants(myWriter);

        myWriter.write("</data>");
        myWriter.close();
    }

    private void writeCours(FileWriter writer) throws IOException {
        for(Cours c:courseList){
            writer.write("    <course>\n");
            writer.write("        <identifier>");
            writer.write(c.getId());
            writer.write("</identifier>\n");
            writer.write("        <name>");
            writer.write(c.getNom());
            writer.write("</name>\n");
            writer.write("        <credits>");
            writer.write(String.valueOf(c.getCoef()));
            writer.write("</credits>\n");
            writer.write("    </course>\n\n");
        }
    }

    private void writeEtudiants(FileWriter writer) throws IOException {
        for(Etudiant e:studentList){
            writer.write("    <student>\n");
            writer.write("        <identifier>");
            writer.write(e.getId());
            writer.write("</identifier>\n");
            writer.write("        <name>");
            writer.write(e.getNom());
            writer.write("</name>\n");
            writer.write("        <surname>");
            writer.write(e.getPrenom());
            writer.write("</surname>\n");
            writer.write("        <program>");
            writer.write(e.getP().getId());
            writer.write("</program>\n");
            e.getNotes().forEach((key, value) -> {
                try{
                    writer.write("        <grade>\n");
                    writer.write("            <item>");
                    writer.write(key.getId());
                    writer.write("</item>\n");
                    writer.write("            <value>");
                    writer.write(value.getNote());
                    writer.write("</value>\n");
                    writer.write("        </grade>\n");
                } catch (IOException ioException) {}
            });
            writer.write("    </student>\n");
        }
    }

    private void writeProgrammes(FileWriter writer) throws IOException {
        for(Programme p:programList){
            writer.write("    <program>\n");
            writer.write("        <identifier>");
            writer.write(p.getId());
            writer.write("</identifier>\n");
            writer.write("        <name>");
            writer.write(p.getNom());
            writer.write("</name>\n");
            for(Bloc b:p.getBlocs()){
                if(b instanceof BlocSimple){
                    writer.write("        <item>");
                    writer.write(b.getUE().get(0).getId());
                    writer.write("</item>\n");
                }
                else{
                    if(b instanceof BlocComposite){
                        writer.write("        <composite>\n");
                    }
                    else if(b instanceof BlocOptions){
                        writer.write("        <option>\n");
                    }
                    writer.write("            <identifier>");
                    writer.write(b.getId());
                    writer.write("</identifier>\n");
                    writer.write("            <name>");
                    writer.write(b.getNom());
                    writer.write("</name>\n");
                    for(UE u:b.getUE()){
                        writer.write("            <item>");
                        writer.write(u.getId());
                        writer.write("</item>\n");
                    }
                    if(b instanceof BlocComposite){
                        writer.write("        </composite>\n");
                    }
                    else if(b instanceof BlocOptions){
                        writer.write("        </option>\n");
                    }
                }
            }
            writer.write("    </program>\n\n");
        }
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
