import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

abstract class MenuBouton implements ActionListener{
  public Menu menu;

  public MenuBouton(Menu m){
    this.menu=m;
  }

  abstract public void actionPerformed(ActionEvent e);

}
