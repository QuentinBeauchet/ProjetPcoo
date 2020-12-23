package Model;

import Controller.Arbre;
import Controller.Dialog;
import View.Home;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class HierarchieCreation {
    private Home home;
    private JTree arbre;
    private ArrayList<UE> ue;

    public HierarchieCreation(Home home){
        this.home=home;
        arbre=ArbreProgrammes();
        setDialogBox();
    }

    private JTree ArbreProgrammes(){
        ArrayList<Programme> programList = home.getXml().getProgramList();
        DefaultMutableTreeNode arbre=new DefaultMutableTreeNode("Programmes");
        for(Programme p:programList){
            HierarchieSelection hierarchie = new HierarchieSelection(p);
            arbre.add(hierarchie.getArbre());
        }
        return new JTree(arbre);
    }

    private void setDialogBox(){
        JDialog dialog = new JDialog(home.getFrame(), "Hierarchie");
        JScrollPane scroll=new JScrollPane(arbre);

        arbre.addTreeExpansionListener(new Arbre(dialog,arbre));
        dialog.addWindowListener(new Dialog(this,arbre));

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        dialog.add(scroll);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    public void setFiltreUE(ArrayList<String> filtreUE){
        //TODO je crée un programe depuis une liste d'ue (peu etre amelioré)

        ProgramSwitch programSwitch = new ProgramSwitch(home);
        if(filtreUE.size()>0 && filtreUE.get(0).equals("Programmes")){
            programSwitch.Switch(-1);
        }
        else{
            ArrayList<Programme> programList=home.getXml().getProgramList();
            ArrayList<Bloc> blocsList=new ArrayList<>();

            //TODO si je passe pas par le hashset string il y a des doublons pour les ue qui sont dans plusieurs programmes
            HashSet<String> blocs=new HashSet<>();
            for(Programme p:programList){
                for(Bloc b:p.getBlocs()){
                    blocs.add(b.getNom());
                }
            }

            for(Programme p:programList){
                for(Bloc b:p.getBlocs()){
                    if(blocs.contains(b.getNom())){
                        blocsList.add(b);
                        blocs.remove(b.getNom());
                    }
                }
            }

            ArrayList<UE> ue=new ArrayList<>();
            for(Bloc b:blocsList){
                if(filtreUE.contains(b.getNom())){
                    ue.addAll(b.getUE());
                }
            }

            //TODO C'EST MOCHE (c'est pour afficher les programmes + des blocs)
            ArrayList<Bloc> blocsProg =new ArrayList<>();
            for(Programme p:programList){
                if(filtreUE.contains(p.getNom())){
                    blocsProg.addAll(p.getBlocs());
                }
            }
            for(Bloc m:blocsProg){
                ue.addAll(m.getUE());
            }



            ArrayList<Cours> cours=new ArrayList<>();
            for(UE u:ue){
                cours.add((Cours)u);
            }
            programSwitch.Switch(cours);
        }
    }
}
