package Model;

import View.Home;
import View.Menu;
import View.Tableau;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ProgrammeBouton extends MenuBoutons{
    private final int index;
    private final Home home;

    public ProgrammeBouton(Menu menu, int index,Home home) {
        super(menu);
        this.index=index;
        this.home=home;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Tableau tab;
        home.getFrame().remove(home.getTabInit().getPanel());
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
        home.getFrame().add(tab.getPanel());
        home.setTab(tab);
        SwingUtilities.updateComponentTreeUI(home.getFrame());
    }
}
