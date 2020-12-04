import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FichierBouton extends MenuBouton{
  private final String action;

  public FichierBouton(Menu m,String action){
    super(m);
    this.action=action;
  }

  public void actionPerformed(ActionEvent e){
    if(action.equals("Ouvrir")){
      JFileChooser chooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers .xml", "xml");
      chooser.setFileFilter(filter);
      int returnVal = chooser.showOpenDialog(menu.getMenuBar());
      if(returnVal == JFileChooser.APPROVE_OPTION) {
        menu.setFile(chooser.	getSelectedFile());
      }
    }
    else{
      
    }
  }
}
