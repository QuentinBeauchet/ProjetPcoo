package Controller;

import Model.ProgramSwitch;
import View.Home;

import java.awt.event.ActionEvent;

public class ProgrammeBouton extends MenuBoutons{
    private final int index;

    public ProgrammeBouton(int index,Home home) {
        super(home);
        this.index=index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProgramSwitch programSwitch = new ProgramSwitch(super.getHome());
        programSwitch.Switch(index);
    }
}
