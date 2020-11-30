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
        initView();
    }
    private void initView(){
        this.h.getFrameContentPane().add(new JLabel(String.valueOf(this.e.getId())));
        this.h.getFrameContentPane().add(new JLabel(this.e.getPrenom()));
        this.h.getFrameContentPane().add(new JLabel(this.e.getNom()));
    }

}
