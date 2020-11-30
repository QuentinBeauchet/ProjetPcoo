package View;

import Model.Etudiant;

import javax.swing.*;
import java.awt.*;

public class Home {
    private  JFrame frame;
    private Container frameContentPane;

    public Container getFrameContentPane() {
        return frameContentPane;
    }

    public Home() {



        this.frame = new JFrame();
        this.frame.setTitle("myHome");
        this.frame.setSize(500,120);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        this.frameContentPane = frame.getContentPane();



        this.frameContentPane.setLayout(new FlowLayout());

        this.frame.setVisible(true);

    }

    public JFrame getFrame() {
        return frame;
    }

}
