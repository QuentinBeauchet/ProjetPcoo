package Model;

import Exceptions.IdEtudiantInvalidException;
import Exceptions.NameEtudiantInvalidException;
import Exceptions.SurnameEtudiantInvalidException;
import View.Home;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AjoutEtudiant {
    private static final int NBR_COMPOSANTS_ETUDIANTS=5;
    private Etudiant etudiant;
    private static final Border border=(new JTextField(20)).getBorder();

    public AjoutEtudiant(Home home,Object[] args){
        JDialog dialog = (JDialog) args[0];
        if(isEtudiantValid((JTextField) args[3],(JTextField) args[1],(JTextField) args[2])){
            Programme programme=(Programme)((JList)args[4]).getModel().getElementAt(((JList)args[4]).getFirstVisibleIndex());
            etudiant.inscris(programme);
            home.getXml().getStudentList().add(etudiant);
            DefaultTableModel model = (DefaultTableModel) home.getTab().getTableau().getModel();
            model.addRow(EtudiantToRow(etudiant,model.getColumnCount()));
            dialog.dispose();
        }
    }

    private boolean isEtudiantValid(JTextField id,JTextField nom,JTextField prenom){
        boolean isValid=false;
        try {
            etudiant = new Etudiant(id.getText(), nom.getText(), prenom.getText());
            //TODO test si id existe deja
            isValid=true;
            nom.setBorder(border);
            id.setBorder(border);
            prenom.setBorder(border);
        }
        catch (IdEtudiantInvalidException exception){
            id.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        catch (NameEtudiantInvalidException exception){
            id.setBorder(border);
            nom.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        catch (SurnameEtudiantInvalidException exception){
            nom.setBorder(border);
            prenom.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        return isValid;
    }

    private Object[] EtudiantToRow(Etudiant etudiant,int nbrCol){
        Object[] row=new Object[nbrCol];
        row[0]=Integer.parseInt(etudiant.getId());
        row[1]=etudiant.getNom();
        row[2]=etudiant.getPrenom();
        row[3]=etudiant.getP();
        row[4]=etudiant.getP().getNoteProgramme(etudiant);
        for (int i = NBR_COMPOSANTS_ETUDIANTS; i < nbrCol; i++) {
            row[i]=new Note("");
        }
        return row;
    }
}
