import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TexteBoutons implements KeyListener{
  private JTextField TextField;
  private Menu menu;

  public TexteBoutons(Menu m){
    this.menu=m;
  }

  public void keyReleased(KeyEvent e){
    TextField=(JTextField)e.getSource();
    menu.setFiltre("TEXTE",TextField.getText());
  }

  public void keyTyped(KeyEvent e){}

  public void keyPressed(KeyEvent e){}

}
