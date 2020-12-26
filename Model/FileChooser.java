package Model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {
    private JFileChooser chooser;

    public FileChooser(){
        chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers .xml", "xml");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
        if(chooser.getSelectedFile()!=null){
            System.out.println(chooser.getSelectedFile());
            //TODO passer le chemin a xmlreader
        }
        else{
            System.out.println("Fichier invalide");
        }
    }
}
