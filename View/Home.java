package View;

import Model.Etudiant;

import javax.swing.*;
import java.awt.*;

public class Home {
    private  JFrame frame;
    private Container frameContentPane;
    private JButton hello;

    public Home() {

        this.frame = new JFrame();
        this.frame.setTitle("myHome");
        this.frame.setSize(500,120);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.hello = new JButton("Hello");

        this.frameContentPane = frame.getContentPane();


        this.frameContentPane.setLayout(new FlowLayout());
        this.frameContentPane.add(this.hello);

        this.frame.setVisible(true);

    }

    public JFrame getFrame() {
        return frame;
    }
    public Container getFrameContentPane() {
        return frameContentPane;
    }

    public JButton getHello() {
        return hello;
    }


}
