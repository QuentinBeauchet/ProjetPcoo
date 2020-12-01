import Controller.HomeController;
import Model.Etudiant;
import View.Home;

public class App {
    public static void main(String[] args) {
        HomeController c = new HomeController(new Home(),new Etudiant(5,"Forner","Yann"));
        c.initController();
    }
}
