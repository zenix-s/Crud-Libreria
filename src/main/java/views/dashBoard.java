package views;

import javax.swing.*;

public class dashBoard {
    private JPanel panel1;


    public static void main(String[] args) {
        JFrame frame = new JFrame("dashBoard");
        frame.setContentPane(new dashBoard().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
