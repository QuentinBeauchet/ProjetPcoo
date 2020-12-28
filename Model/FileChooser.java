package Model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {
    private JFileChooser chooser;
    private int option;

    /**
     * Classe qui instancie le JFileChooser pour choisir un nouveau fichier xml.
     */

    public FileChooser(){
        setLF();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers .xml", "xml");
        chooser.setFileFilter(filter);
        option = chooser.showOpenDialog(null);
    }

    /**
     * Look&Feel du JFileChooser.
     */

    private void setLF(){
        LookAndFeel previousLF=UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            chooser = new JFileChooser();
        }
        catch (Exception exception){}
        try {
            UIManager.setLookAndFeel(previousLF);
        }
        catch (Exception exception){}
    }

    /**
     * Renvoit le JFileChooser.
     *
     * @return
     */

    public JFileChooser getChooser(){
        return chooser;
    }

    /**
     * Renvoit le status du JFileChooser.
     *
     * @return
     */

    public int getOption(){
        return option;
    }
}
