package Graphique;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class CourBouton extends MenuBouton{
  private final String tag;

  CourBouton(Menu m,String tag){
    super(m);
    this.tag=tag;
  }

  //Renvoit le nom du cours qui a été cliqué dans le menu
  public void actionPerformed(ActionEvent e){
    JMenuItem cour=(JMenuItem)e.getSource();
    Couleur(cour);
    super.menu.setFiltre(tag,cour.getText());
  }

  //Grise la case du cours s'il n'est pas affiché et inversement
  private void Couleur(JMenuItem cour){
    JMenu Cours=(JMenu)(super.menu.MenuBar.getComponent(0));
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
