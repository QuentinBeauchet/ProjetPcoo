import Controller.HomeController;
import Model.Etudiant;
import Model.XMLReader;
import View.Home;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

public class App {
    public static void main(String[] args) {

        HomeController c = new HomeController();

        //TODO j'ai eu cette erreur quand j'ai resize "** (java:20711): CRITICAL **: 19:52:14.865: murrine_style_draw_box: assertion 'height >= -1' failed"
    }
}
