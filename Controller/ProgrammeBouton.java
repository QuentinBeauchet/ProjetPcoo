package Controller;

import Model.ProgramSwitch;
import View.Home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgrammeBouton implements ActionListener {
    private final int index;
    private final Home home;

    /**
     * Classe de l'EventListener des boutons du JMenu Programme.
     * @param index int
     * @param home Home
     */
    public ProgrammeBouton(int index,Home home) {
        this.home=home;
        this.index=index;
    }

    /**
     * Change les cours du tableau selon le programme selectioné.
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ProgramSwitch programSwitch = new ProgramSwitch(home);
        programSwitch.Switch(index);
    }
}
