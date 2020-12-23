package Controller;

import Model.ProgramSelection;
import Model.ProgramSwitch;
import Model.TabCreation;
import Model.XMLReader;
import View.Home;
import View.Menu;
import View.Tableau;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ProgrammeBouton implements MenuBoutons{
    private final int index;
    private final Home home;

    public ProgrammeBouton(int index,Home home) {
        super();
        this.index=index;
        this.home=home;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProgramSwitch programSwitch = new ProgramSwitch(home);
        programSwitch.Switch(index);
    }
}
