package Controller;

import View.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RechercheField implements KeyListener {
    private JTextField texte;
    private Home home;

    public RechercheField(JTextField texte,Home home){
        this.texte=texte;
        this.home=home;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        home.getTab().setSorter(texte.getText());
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }
}
