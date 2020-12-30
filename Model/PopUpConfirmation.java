package Model;

import Controller.Boutons;
import Exceptions.LookAndFeelException;

import javax.swing.*;
import java.awt.*;

public class PopUpConfirmation {
    private final JDialog dialog;

    public PopUpConfirmation(){
        dialog=new JDialog();
        setStyle();
        setPanel();
    }

    private void setStyle(){
        dialog.setSize(new Dimension(200,200));
        dialog.setLocationRelativeTo(null);
        dialog.setModal(false);
        dialog.setUndecorated(true);
        dialog.setVisible(true);
        dialog.setBackground(new Color(0,0,0,0));
    }

    private void setPanel(){
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

    private void setStyleBouton(JButton bouton){
        bouton.setBackground(new Color(59, 89, 182));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
        bouton.setFont(new Font("Tahoma", Font.BOLD, 12));
    }

    private JButton setBoutonLF(String s){
        JButton bouton;
        LookAndFeel previousLF = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        catch (Exception exception){
            throw new LookAndFeelException();
        }
        bouton=new JButton(s);
        try {
            UIManager.setLookAndFeel(previousLF);
        } catch (UnsupportedLookAndFeelException e) {
            throw new LookAndFeelException();
        }
        return bouton;
    }
}
