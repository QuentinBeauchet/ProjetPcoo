package Controller;

import Model.Etudiant;
import View.Home;

public class HomeController {

    public HomeController(Home h, Etudiant e) {
        h.initView(e);
    }
}
