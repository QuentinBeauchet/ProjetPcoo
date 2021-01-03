package Model;

import Exceptions.*;
import View.Home;
import View.Tableau;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class AjoutCours {
    private Home home;
    private Cours cours;
    private static final Border border=(new JTextField(20)).getBorder();


    public AjoutCours(Home home, Object[] args){
        this.home=home;
        //"Ajout Cours",home,dialog,fieldNom,fieldCoeff,fieldId,buttonGroup,multipleButtonGroup,compositeNomField,compositeIDField,listeBlocs));
        JDialog dialog = (JDialog) args[0];
        JList listeProgramme=(JList)args[9];
        if(isCoursValid((JTextField)args[3],(JTextField)args[2],(JTextField)args[1],listeProgramme)){
            ButtonModel selection = ((ButtonGroup) args[4]).getSelection();
            if(selection !=null){
                if(selection.getActionCommand().equals("0")){
                    XMLReader xml= home.getXml();
                    ArrayList<Cours> coursArrayList=xml.getCourseList();
                    ArrayList<Programme> programmeArrayList=xml.getProgramList();
                    ArrayList<Etudiant> etudiantArrayList=xml.getStudentList();

                    coursArrayList.add(cours);
                    programmeArrayList.get(listeProgramme.getLastVisibleIndex()).getBlocs().add(isBlocSimpleValid());

                    TabCreation tabCreation = new TabCreation(home, coursArrayList, etudiantArrayList);
                    home.setTab(new Tableau(tabCreation));
                }
                else{
                    isBlocMultipleValid(selection.getActionCommand(),(ButtonGroup)args[5],(JTextField)args[2],(JTextField)args[6],(JTextField)args[7],(JList)args[8],(JList)args[9]);
                }
            }
        }
    }

    private boolean isCoursValid(JTextField id,JTextField coeff,JTextField nom,JList listeProgramme){
        if(home.getXml().getProgramList().size()==0){
            listeProgramme.setForeground(Color.red);
            return false;
        }
        boolean isValid=false;
        try {
            cours = new Cours(id.getText(),Integer.valueOf(coeff.getText()),nom.getText());
            //TODO changer static
            if(XMLReader.isIdCourseAlreadyExist(home.getXml().getCourseList(),cours.getId())){
                throw new IdUeDuplicationException(cours);
            }
            isValid=true;
            nom.setBorder(border);
            id.setBorder(border);
            coeff.setBorder(border);
        }
        catch (NumberFormatException exception){
            coeff.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        catch (IdUeInvalidException exception){
            coeff.setBorder(border);
            id.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        catch (NameUeInvalidException exception){
            id.setBorder(border);
            nom.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        catch (IdUeDuplicationException exception){
            nom.setBorder(border);
            id.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        return isValid;
    }

    private Bloc isBlocSimpleValid(){
        return new BlocSimple(cours);
    }

    private boolean isBlocMultipleValid(String selection,ButtonGroup nouveau, JTextField coeff, JTextField nom, JTextField id, JList blocs,JList programme) {
        boolean isValid=false;
        if(nouveau.getSelection()==null){
            return false;
        }
        //TODO tout refaire plus tard flemme c'est trop chiant
        else{
            ArrayList<Bloc> blocsArrayList=home.getXml().getProgramList().get(programme.getLastVisibleIndex()).getBlocs();
            if(selection.equals("1")){
                if(nouveau.getSelection().getActionCommand().equals("nouveau")){
                    try{
                        BlocOptions blocOptions = new BlocOptions(id.getText(), cours.getCoef(), nom.getText());
                        blocOptions.add(cours);
                        for(Bloc b:blocsArrayList){
                            if(b.getId().equals(blocOptions.getId())){
                                throw new IdUeDuplicationException(blocOptions);
                            }
                        }
                        id.setBorder(border);
                        nom.setBorder(border);
                        System.out.println(home.getXml().getProgramList().get(0).getBlocs().size());
                        blocsArrayList.add(blocOptions);
                        System.out.println(home.getXml().getProgramList().get(0).getBlocs().size());

                    }
                    catch (IdUeInvalidException exception){
                        id.setBorder(BorderFactory.createLineBorder(Color.red));
                    }
                    catch (IdUeDuplicationException exception){
                        id.setBorder(border);
                        id.setBorder(BorderFactory.createLineBorder(Color.red));
                    }
                    catch (NameUeInvalidException exception){
                        id.setBorder(border);
                        nom.setBorder(BorderFactory.createLineBorder(Color.red));
                    }
                }
                else{
                    for (int i = 0; i < blocsArrayList.size(); i++) {
                        if(blocs.getSelectedValue()!=null){

                        }
                    }
                }

            }
            else{

            }
            return isValid;
        }
    }


}
