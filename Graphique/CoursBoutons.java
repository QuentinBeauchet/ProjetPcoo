import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CoursBoutons implements ActionListener{
  private JMenuItem cour;

  public CoursBoutons(){}

  public void actionPerformed(ActionEvent e){
    cour=(JMenuItem)e.getSource();
    System.out.println("COUR: "+cour.getText());
  }
}
