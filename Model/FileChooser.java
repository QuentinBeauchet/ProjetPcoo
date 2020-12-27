package Model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.file.Path;

public class FileChooser {
    private JFileChooser chooser;
    private int option;

    public FileChooser(){
        setLF();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers .xml", "xml");
        chooser.setFileFilter(filter);
        option = chooser.showOpenDialog(null);
    }

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

    public JFileChooser getChooser(){
        return chooser;
    }

    public int getOption(){
        return option;
    }
}
