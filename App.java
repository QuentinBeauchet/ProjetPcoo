import Controller.HomeController;
import Model.Etudiant;
import Model.ProgramSelection;
import Model.XMLReader;
import View.Home;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        XMLReader xmlReader = new XMLReader("data/data.xml");

        HomeController c = new HomeController(new Home(xmlReader),new Etudiant("5","Forner","Yann"));
        Home h=c.getHome();
        Etudiant e=c.getEtudiant();

        ProgramSelection p=new ProgramSelection(xmlReader);



        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo look : looks) {
            System.out.println(look.getClassName());
        }

        //TODO j'ai eu cette erreur quand j'ai resize "** (java:20711): CRITICAL **: 19:52:14.865: murrine_style_draw_box: assertion 'height >= -1' failed"
    }
}
