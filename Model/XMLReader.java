package Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {
    private Element file ;
    private ArrayList<Cours> courseList;
    private ArrayList<Programme> programList;
    private ArrayList<Etudiant> studentList;

    public Element getFile() {
        return file;
    }

    public ArrayList<Cours> getCourseList() {
        return courseList;
    }

    public ArrayList<Programme> getProgramList() {
        return programList;
    }

    public ArrayList<Etudiant> getStudentList() {
        return studentList;
    }

    public XMLReader(String fileName) {
        this.file = readFile(fileName);
        this.courseList = fillCourses();
        this.programList = fillPrograms();
        this.studentList = fillStudents();
    }




    private Element readFile (String fileName){
        Document doc = null;
        try {
            File file = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            dBuilder = dbFactory.newDocumentBuilder();
             doc = dBuilder.parse(file); // ouverture et lecture du fichier XML
            doc.getDocumentElement().normalize(); // normalise le contenu du fichier, opération très conseillée

            // c'est parti pour l'exploration de l'arbre
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return doc.getDocumentElement();
    }

    // Extrait la liste des fils de l'élément item dont le tag est name
    private static List<Element> getChildren(Element item, String name) {
        NodeList nodeList = item.getChildNodes();
        List<Element> children = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodeList.item(i); // cas particulier pour nous où tous les noeuds sont des éléments
                if (element.getTagName().equals(name)) {
                    children.add(element);
                }
            }
        }
        return children;
    }

    private ArrayList<Cours> fillCourses (){
        ArrayList<Cours> cours = new ArrayList<>();
        for (Element e: getChildren(this.file,"course")
             ) {
            cours.add(new Cours(
                    e.getElementsByTagName("identifier").item(0).getTextContent(),
                    Integer.parseInt(e.getElementsByTagName("credits").item(0).getTextContent()),
                    e.getElementsByTagName("name").item(0).getTextContent()
            ));
        }
        return cours;
    }

    private ArrayList<Programme> fillPrograms(){
        ArrayList<Programme> programs = new ArrayList<>();
        for (Element e: getChildren(this.file,"program")
             ) {
            Programme temp = new Programme(
                    e.getElementsByTagName("name").item(0).getTextContent(),
                    e.getElementsByTagName("identifier").item(0).getTextContent()
                   );
            fillOneProgram(e,temp);
            programs.add(temp);
        }
        return programs;
    }

    private ArrayList<Etudiant> fillStudents(){
        ArrayList<Etudiant> etudiants= new ArrayList<>();
        for (Element e : getChildren(this.file,"student")){
            Etudiant etu = new Etudiant(
                    e.getElementsByTagName("identifier").item(0).getTextContent(),
                    e.getElementsByTagName("name").item(0).getTextContent(),
                    e.getElementsByTagName("surname").item(0).getTextContent()
            );

            etu.inscris(findProgramById(e.getElementsByTagName("program").item(0).getTextContent()));
            fillNotesToStudent(e,etu);
            etudiants.add(etu);
        }

        return etudiants;
    }

    private void fillNotesToStudent(Element e , Etudiant t ){
        for (Element s : getChildren(e,"grade")){
            t.addNote(
                    findCourseById(s.getElementsByTagName("item").item(0).getTextContent()),
                    new Note(s.getElementsByTagName("value").item(0).getTextContent())
            );
        }
    }

    private void fillOneProgram( Element e,Programme p){
        for (Element s: getChildren(e,"item")//bloc Simple
        ) {
            p.add(new BlocSimple(findCourseById(s.getTextContent())));
        }
        for(Element s: getChildren(e,"option")//bloc option
        ){
            BlocOptions temp = new BlocOptions(
                    s.getElementsByTagName("identifier").item(0).getTextContent(),
                    findCourseById(getChildren(s,"item").get(0).getTextContent()).getCoef(),
                    s.getElementsByTagName("name").item(0).getTextContent()
            );
            fillABlocMul(s,temp);
            p.add(temp);

        }
        for(Element s: getChildren(e,"composite")//bloc composite
        ){
            BlocComposite temp = new BlocComposite(
                    s.getElementsByTagName("identifier").item(0).getTextContent(),
                    s.getElementsByTagName("name").item(0).getTextContent()
            );
            fillABlocMul(s,temp);
            p.add(temp);
        }
    }

    private void fillABlocMul(Element e, BlocMultiple b){
        for (Element s: getChildren(e,"item")
        ) {
            b.add(findCourseById(s.getTextContent()));
        }
    }

    public Cours findCourseById(String id){
        for (Cours u: this.courseList
             ) {
            if(u.getId().equals(id))return u;
        }
        return null;
    }
    public Programme findProgramById(String id){
        for (Programme p : this.programList
        ){
            if(p.getId().equals(id))return p;
        }
        return null;
    }

}
