package Model;

import View.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class MenuBoutons implements ActionListener {
    private static Menu menu;

    public MenuBoutons(Menu menu){
        this.menu=menu;
    }

    public Menu getMenu(){
        return menu;
    }

    abstract public void actionPerformed(ActionEvent e);
}
