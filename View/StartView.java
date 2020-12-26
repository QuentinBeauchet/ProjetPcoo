package View;

import Model.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class StartView {
    private JFrame frame;

    public StartView(){
        frame = new JFrame("Projet PCOO");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        setBouttons();
        frame.setVisible(true);
    }

    private void setBouttons(){
        JLabel message=new JLabel("<html><p style=width:200px>Bienvenue sur le Projet de PCOO de </br> Quentin BEAUCHET,Yann FORNER et Gillian MASSE</p></html>");
        message.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
        GridBagConstraints c=new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.gridheight=1;
        c.gridwidth=3;
        c.anchor=GridBagConstraints.NORTH;
        c.weighty = 1;
        c.insets= new Insets(10,0,0,0);
        frame.add(message,c);

        JLabel choix=new JLabel("Veuillez choisir un fichier:");
        choix.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
        c=new GridBagConstraints();
        c.gridx=0;
        c.gridy=1;
        c.gridheight=1;
        c.gridwidth=3;
        c.anchor=GridBagConstraints.NORTH;
        c.weighty = 1;
        frame.add(choix,c);

        JButton fichier=new JButton("<html><u>Fichier</u></html>");
        fichier.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
        c=new GridBagConstraints();
        c.gridx=0;
        c.gridy=2;
        c.gridheight=1;
        c.gridwidth=1;
        c.anchor=GridBagConstraints.WEST;
        c.weighty = 1;
        frame.add(fichier,c);

        JPanel confirmation=new JPanel();
        confirmation.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
        confirmation.setLayout(new GridLayout());
        confirmation.add(new JLabel("Vous avez choisit ....xml, voulez vous confirmer ?"));
        confirmation.add(new JButton("OUI"));
        confirmation.add(new JButton("NON"));
        c=new GridBagConstraints();
        c.gridx=0;
        c.gridy=3;
        c.gridheight=1;
        c.gridwidth=3;
        c.anchor=GridBagConstraints.CENTER;
        c.weighty = 1;
        frame.add(confirmation,c);

    }
}


