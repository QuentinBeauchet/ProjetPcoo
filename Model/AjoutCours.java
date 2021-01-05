package Model;

import Exceptions.*;
import View.Home;
import View.Tableau;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class AjoutCours {
    private Home home;
    private Cours cours;

    public AjoutCours(Home home, Object[] args) {
        this.home = home;
        JDialog dialog = (JDialog) args[0];
        JList listeProgramme = (JList) args[9];
        if (isCoursValid((JTextField) args[3], (JTextField) args[2], (JTextField) args[1], listeProgramme)) {
            ButtonModel selection = ((ButtonGroup) args[4]).getSelection();
            if (selection != null) {
                setBloc(selection.getActionCommand(),(ButtonGroup)args[5],(JTextField)args[2],(JTextField)args[7],(JTextField)args[6],(JList)args[8],(JList)args[9],(JTextField)args[3]);
                dialog.dispose();
                ProgramSwitch programSwitch = new ProgramSwitch(home);
                programSwitch.Switch(-1);
            }
        }
    }

    private boolean isCoursValid(JTextField id, JTextField coeff, JTextField nom, JList listeProgramme) {
        if (home.getXml().getProgramList().size() == 0) {
            listeProgramme.setForeground(Color.red);
            return false;
        }
        boolean isValid = false;
        try {
            cours = new Cours(id.getText(), Integer.valueOf(coeff.getText()), nom.getText());
            if (XMLReader.isIdCourseAlreadyExist(home.getXml().getCourseList(), cours.getId())) {
                throw new IdUeDuplicationException(cours);
            }
            isValid = true;
            nom.setBorder(PopUp.normalBorder);
            id.setBorder(PopUp.normalBorder);
            coeff.setBorder(PopUp.normalBorder);
        } catch (NumberFormatException exception) {
            coeff.setBorder(PopUp.erreurBorder);
        } catch (IdUeInvalidException exception) {
            coeff.setBorder(PopUp.normalBorder);
            id.setBorder(PopUp.erreurBorder);
        } catch (NameUeInvalidException exception) {
            id.setBorder(PopUp.normalBorder);
            nom.setBorder(PopUp.erreurBorder);
        } catch (IdUeDuplicationException exception) {
            nom.setBorder(PopUp.normalBorder);
            id.setBorder(PopUp.erreurBorder);
        }
        for(Etudiant e:home.getXml().getStudentList()){
            e.getNotes().put(cours,new Note(""));
        }
        return isValid;
    }

    private void setBloc(String selection, ButtonGroup nouveau, JTextField coeff, JTextField id, JTextField nom, JList blocs, JList programmes, JTextField idcours) {
        XMLReader xml = home.getXml();
        ArrayList<Cours> coursArrayList = xml.getCourseList();
        ArrayList<Programme> programmeArrayList = xml.getProgramList();

        Programme currentProgramme = programmeArrayList.get(programmes.getLastVisibleIndex());
        if (selection.equals("0")) {
            BlocSimple blocSimple = new BlocSimple(cours);
            currentProgramme.add(blocSimple);
        }
        else{
            if (nouveau.getSelection() != null) {
                if (selection.equals("1")) {
                    if(nouveau.getSelection().getActionCommand().equals("nouveau")){
                        try{
                            BlocOptions blocOptions=new BlocOptions(id.getText(), Integer.valueOf(coeff.getText()), nom.getText(), currentProgramme);
                            blocOptions.add(cours);
                            coeff.setBorder(PopUp.normalBorder);
                            id.setBorder(PopUp.normalBorder);
                        }
                        catch (NumberFormatException exception){
                            coeff.setBorder(PopUp.erreurBorder);
                        }
                        catch (IdUeDuplicationException exception){
                            coeff.setBorder(PopUp.normalBorder);
                            id.setBorder(PopUp.erreurBorder);
                        }
                    }
                    else{
                        BlocOptions blocOptions = (BlocOptions) blocs.getModel().getElementAt(blocs.getLastVisibleIndex());
                        try{
                            blocOptions.add(cours);
                            id.setBorder(PopUp.normalBorder);
                            coeff.setBorder(PopUp.normalBorder);
                        }
                        catch (IdUeDuplicationException exception){
                            if(exception.getUe().getId().equals(cours.getId())){
                                idcours.setBorder(PopUp.erreurBorder);
                            }
                            else{
                                idcours.setBorder(PopUp.normalBorder);
                                id.setBorder(PopUp.erreurBorder);
                            }
                        }
                        catch (CoefUeInvalidException exception){
                            id.setBorder(PopUp.normalBorder);
                            idcours.setBorder(PopUp.normalBorder);
                            coeff.setBorder(PopUp.erreurBorder);
                        }
                    }
                } else {
                    if(nouveau.getSelection().getActionCommand().equals("nouveau")){
                        try{
                            BlocComposite blocComposite = new BlocComposite(id.getText(), nom.getText(), currentProgramme);
                            blocComposite.add(cours);
                            coeff.setBorder(PopUp.normalBorder);
                            id.setBorder(PopUp.normalBorder);
                        }
                        catch (NumberFormatException exception){
                            coeff.setBorder(PopUp.erreurBorder);
                        }
                        catch (IdUeDuplicationException exception){
                            coeff.setBorder(PopUp.normalBorder);
                            id.setBorder(PopUp.erreurBorder);
                        }
                    }
                    else{
                        BlocComposite blocComposite = (BlocComposite) blocs.getModel().getElementAt(blocs.getLastVisibleIndex());
                        try{
                            blocComposite.add(cours);
                            id.setBorder(PopUp.normalBorder);
                            coeff.setBorder(PopUp.normalBorder);
                        }
                        catch (IdUeDuplicationException exception){
                            if(exception.getUe().getId().equals(cours.getId())){
                                idcours.setBorder(PopUp.erreurBorder);
                            }
                            else{
                                idcours.setBorder(PopUp.normalBorder);
                                id.setBorder(PopUp.erreurBorder);
                            }
                        }
                    }
                }
            }
        }
        coursArrayList.add(cours);
        System.out.println("Actualisation...");
        TabCreation tabCreation=new TabCreation(home,coursArrayList, xml.getStudentList());
        Tableau tab=new Tableau(tabCreation);
        home.setTab(tab);
    }
}
