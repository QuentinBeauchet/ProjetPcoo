import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CourBouton extends MenuBouton{
  private final String tag;

  CourBouton(Menu m,String tag){
    super(m);
    this.tag=tag;
  }

  public void actionPerformed(ActionEvent e){
    JMenuItem cour=(JMenuItem)e.getSource();
    Couleur(cour);
    super.menu.setFiltre(tag,cour.getText());
  }

  private void Couleur(JMenuItem cour){
    JMenu Cours=(JMenu)(super.menu.getMenuBar().getComponent(0));
    switch(tag){
      case "ON":
        for(int i=2;i<Cours.getItemCount();i++){
          Cours.getMenuComponent(i).setBackground(new Color(238, 238, 238));
        }
        break;
      case "OFF":
        for(int i=2;i<Cours.getItemCount();i++){
          Cours.getMenuComponent(i).setBackground(new Color(191, 191, 191));
        }
        break;
      default:
        if(cour.getBackground().equals(new Color(238, 238, 238))){
          cour.setBackground(new Color(191, 191, 191));
        }
        else{
          cour.setBackground(new Color(238, 238, 238));
        }
        break;
    }
  }

}
