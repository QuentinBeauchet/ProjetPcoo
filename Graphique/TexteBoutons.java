package Graphique;

import javax.swing.JTextField;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class TexteBoutons implements KeyListener{
  private JTextField TextField;
  private Menu menu;

  public TexteBoutons(Menu m){
    this.menu=m;
  }

  //Renvoit le contenu de TextField a chaque nouvelle touche pressé
  public void keyReleased(KeyEvent e){
    TextField=(JTextField)e.getSource();
    menu.setFiltre("TEXTE",TextField.getText());
  }

  public void keyTyped(KeyEvent e){}

  public void keyPressed(KeyEvent e){}

}
