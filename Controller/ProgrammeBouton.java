package Controller;

import Model.ProgramSwitch;
import View.Home;

import java.awt.event.ActionEvent;

public class ProgrammeBouton extends MenuBoutons{
    private final int index;

    /**
     * Classe de l'EventListener des boutons du JMenu Programme.
     *
     * @param index int
     * @param home Home
     */

    public ProgrammeBouton(int index,Home home) {
        super(home);
        this.index=index;
    }

    /**
     * Change les cours du tableau selon le programme selectioné.
     *
     * @param e ActionEvent
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        ProgramSwitch programSwitch = new ProgramSwitch(super.getHome());
        programSwitch.Switch(index);
    }
}
