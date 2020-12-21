import Controller.HomeController;
import Model.Etudiant;
import Model.XMLReader;
import View.Home;

public class App {
    public static void main(String[] args) {
        HomeController c = new HomeController(new Home(),new Etudiant("5","Forner","Yann"));
        Home h=c.getHome();
        Etudiant e=c.getEtudiant();

        XMLReader xmlReader = new XMLReader("data/data.xml");
        h.setTab(xmlReader);

    }
}
