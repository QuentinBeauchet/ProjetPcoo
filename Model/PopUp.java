package Model;

import Controller.Boutons;
import Exceptions.LookAndFeelException;

import javax.swing.*;
import java.awt.*;

public class PopUp {
    private final JDialog dialog;

    /**
     * Classe du JDialog qui permet de confirmer quand on quitte le programme.
     */

    public PopUp(String action){
        dialog=new JDialog(Window.getWindows()[0]);
        setPanel(action);
        dialog.setVisible(true);
    }

    private void setPanel(String action){
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setResizable(false);
        switch (action){
            case "Quitter":
                dialog.setSize(new Dimension(200,200));
                dialog.setUndecorated(true);
                dialog.setBackground(new Color(0,0,0,0));
                setPanelQuitter();
                break;
            default:
                dialog.setSize(new Dimension(500,200));
                dialog.setUndecorated(false);
                dialog.setBackground(new Color(0,0,0,255));
                dialog.setTitle("Ajouter un Etudiant");
                setPanelEtudiant();
        }
        dialog.setLocationRelativeTo(null);
    }

    private void setPanelEtudiant(){
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel nom=new JLabel("NOM:");
        panel.add(nom,new GridBagConstraints(0,0,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,20,0,0),0,0));

        JTextField fieldNom=new JTextField(20);
        panel.add(fieldNom,new GridBagConstraints(1,0,1,1,1,0,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(20,-120,0,20),0,0));

        JLabel prenom=new JLabel("PRENOM:");
        panel.add(prenom,new GridBagConstraints(0,1,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,20,0,0),0,0));

        JTextField fieldPrenom=new JTextField(20);
        panel.add(fieldPrenom,new GridBagConstraints(1,1,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,-90,0,20),0,0));

        JLabel id=new JLabel("NUMERO ETUDIANT:");
        panel.add(id,new GridBagConstraints(0,2,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,20,0,0),0,0));

        JTextField fieldId=new JTextField(20);
        panel.add(fieldId,new GridBagConstraints(1,2,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,-20,0,20),0,0));

        JLabel programme=new JLabel("CHOIX PROGRAMME:");
        panel.add(programme,new GridBagConstraints(0,3,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,20,0,0),0,0));

        JList choixProgramme=new JList(new Object[]{"Aucun","L3 info","L3 math","L1 biologie"});
        System.out.println();
        choixProgramme.getLastVisibleIndex();
        panel.add(new JScrollPane(choixProgramme),new GridBagConstraints(1,3,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,0,0,20),0,0));

        JButton confirmation=new JButton("Confirmer");
        //confirmation.addActionListener(new Fic);
        panel.add(confirmation,new GridBagConstraints(1,4,2,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(10,100,30,100),0,0));

        dialog.add(panel);
    }


    /**
     * Configure le JPanel contenu dans le JDialog pour l'action Quitter.
     */

    private void setPanelQuitter(){
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createLineBorder(Color.lightGray,5)));

        JPanel text=new JPanel();
        text.setLayout(new BorderLayout());
        text.setBackground(new Color(230,230,230));
        text.setBorder(BorderFactory.createEtchedBorder());

        JLabel label=new JLabel("<html><div style='text-align: center;'><b>Les modifications apportés ne seront pas sauvegardées, voulez vous continuer ?<b></div></html>");
        text.add(label,BorderLayout.CENTER);
        label.setForeground(Color.darkGray);

        panel.add(text,new GridBagConstraints(0,0,2,1,0,1,GridBagConstraints.NORTH,GridBagConstraints.BOTH,new Insets(10,10,0,10),0,0));

        JButton oui=setBoutonLF("OUI");
        panel.add(oui,new GridBagConstraints(0,1,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10),0,0));
        setStyleBouton(oui);
        oui.addActionListener(new Boutons("STOP",this));

        JButton non=setBoutonLF("NON");
        panel.add(non,new GridBagConstraints(1,1,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10),0,0));
        setStyleBouton(non);
        non.addActionListener(new Boutons("Annuler",dialog));

        dialog.add(panel);
    }

    /**
     * Configure le style des boutons du JPanel.
     *
     * @param bouton JButton
     */

    private void setStyleBouton(JButton bouton){
        bouton.setBackground(new Color(59, 89, 182));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
        bouton.setFont(new Font("Tahoma", Font.BOLD, 12));
    }

    /**
     * Configure le Look&Feel des boutons du JPanel.
     *
     * @param s String
     * @return JButton
     */

    private JButton setBoutonLF(String s){
        JButton bouton;
        LookAndFeel previousLF = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        catch (Exception exception){/*Ne rien faire*/}
        bouton=new JButton(s);
        try {
            UIManager.setLookAndFeel(previousLF);
        } catch (UnsupportedLookAndFeelException e) {
            throw new LookAndFeelException();
        }
        return bouton;
    }
}
