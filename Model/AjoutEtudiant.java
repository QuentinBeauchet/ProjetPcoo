package Model;

import Controller.Dialog;
import Exceptions.IdEtudiantInvalidException;
import Exceptions.NameEtudiantInvalidException;
import Exceptions.SurnameEtudiantInvalidException;
import View.Home;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AjoutEtudiant {
    private final int NBR_COMPOSANTS_ETUDIANTS=5;
    private final Home home;
    private Etudiant etudiant;

    public AjoutEtudiant(Home home,Object[] args){
        this.home =home;
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
            isValid=true;
        }
        catch (IdEtudiantInvalidException exception){
            nom.setBorder(prenom.getBorder());
            prenom.setBorder(nom.getBorder());
            id.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        catch (NameEtudiantInvalidException exception){
            id.setBorder(prenom.getBorder());
            prenom.setBorder(id.getBorder());
            nom.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        catch (SurnameEtudiantInvalidException exception){
            nom.setBorder(id.getBorder());
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
        //TODO donner ses notes au lieu de NaN et faire l'affichage des resulstas dynamiquement
        row[4]=etudiant.getP().getNoteProgramme(etudiant);
        for (int i = NBR_COMPOSANTS_ETUDIANTS; i < nbrCol; i++) {
            row[i]=new Note("");
        }
        return row;
    }
}
