package Model;

import View.Home;
import View.Tableau;

import java.util.ArrayList;

public class ProgramSwitch {
    private Home home;
    private Tableau tab;

    public ProgramSwitch(Home home){
        this.home=home;
    }

    public void Switch(ArrayList<Cours> cours){
        XMLReader xml=home.getXml();
        TabCreation tabCreation=new TabCreation(cours,xml.getProgramList(), xml.getStudentList());
        tab=new Tableau(tabCreation);
        Switch();

    }

    public void Switch(int index){
        if(index==-1){
            XMLReader xml=home.getXml();
            TabCreation tabCreation=new TabCreation(xml.getCourseList(),xml.getProgramList(), xml.getStudentList());
            tab=new Tableau(tabCreation);

        }
        else{
            ProgramSelection programSelection=new ProgramSelection(home.getXml());
            TabCreation[] tabCreations= programSelection.TabProgrammes();
            tab=new Tableau(tabCreations[index]);
        }
        Switch();
    }

    private void Switch(){
        home.getFrame().remove(home.getTab().getPanel());
        home.getFrame().add(tab.getPanel());
        home.setTab(tab);
        home.getFrame().validate();
    }
}
