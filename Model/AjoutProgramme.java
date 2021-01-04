package Model;

import Exceptions.IdProgramInvalidException;
import Exceptions.NameProgramInvalidException;
import Exceptions.ProgramException;
import View.Home;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AjoutProgramme {
    private Home home;
    private static final Border border=(new JTextField(20)).getBorder();

    public AjoutProgramme(Home home, Object[] args){
        this.home=home;
        JDialog dialog=(JDialog)args[0];
        JTextField nom=((JTextField)args[1]);
        JTextField id=((JTextField)args[2]);
        try{
            Programme programme=new Programme(nom.getText(),id.getText());
            programme.add(new BlocSimple(new Cours("123",6,"algebre")));
            //TODO check if duplicate
            home.getXml().getProgramList().add(programme);
            nom.setBorder(border);
            id.setBorder(border);
            dialog.dispose();
        }
        catch (IdProgramInvalidException exception){
            nom.setBorder(border);
            id.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        catch (NameProgramInvalidException exception){
            id.setBorder(border);
            nom.setBorder(BorderFactory.createLineBorder(Color.red));
        }
    }
}
