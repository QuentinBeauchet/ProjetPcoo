package Model;

public class TestModel {
    public static void main(String[] args) {

        XMLReader xmlReader = new XMLReader("data/data.xml");
        WriteCsv writeCsv = new WriteCsv(xmlReader);
        writeCsv.printCSV(xmlReader.getProgramList().get(0));
    }
}
