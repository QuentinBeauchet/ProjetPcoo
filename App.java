import Controller.HomeController;
import Model.Etudiant;
import Model.XMLReader;
import View.Home;

public class App {
    public static void main(String[] args) {
        XMLReader xmlReader = new XMLReader("data/data.xml");

        HomeController c = new HomeController(new Home(xmlReader),new Etudiant("5","Forner","Yann"));
        Home h=c.getHome();
        Etudiant e=c.getEtudiant();

    }
}
