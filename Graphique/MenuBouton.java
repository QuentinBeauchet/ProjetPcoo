package Graphique;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

abstract class MenuBouton implements ActionListener{
  public Menu menu;

  public MenuBouton(Menu m){
    this.menu=m;
  }

  abstract public void actionPerformed(ActionEvent e);

}
