package Model;

public class TestModel {
    public static void main(String[] args) {

        XMLReader xmlReader = new XMLReader("data/data.xml");
        XMLMaker xmlMaker = new XMLMaker(xmlReader);

    }
}
