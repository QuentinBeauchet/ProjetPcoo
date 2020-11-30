import Controller.HomeController;
import Model.Etudiant;
import View.Home;

public class App {
    public static void main(String[] args) {
        new HomeController(new Home(),new Etudiant("yann","Forner"));
    }
}
