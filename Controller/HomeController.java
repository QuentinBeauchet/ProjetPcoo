package Controller;

import Model.Etudiant;
import View.Home;

import javax.swing.*;
import java.awt.*;

public class HomeController {

    private Home h;
    private Etudiant e;

    public HomeController(Home h, Etudiant e) {
        this.h = h;
        this.e = e;
        //initView();
    }

    public Home getHome() {
        return h;
    }

    public Etudiant getEtudiant() {
        return e;
    }

}
