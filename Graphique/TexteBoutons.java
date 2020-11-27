import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TexteBoutons implements KeyListener{
  private JTextField text;

  public TexteBoutons(){}

  public void keyReleased(KeyEvent e){}

  public void keyTyped(KeyEvent e){}

  public void keyPressed(KeyEvent e){
    text=(JTextField)e.getSource();
    System.out.println(text.getText());
  }
}
