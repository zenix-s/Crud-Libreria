package views;

import javax.swing.*;

public class DashBoard extends JFrame{
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton addBook_view;
    private JButton button4;

    public static void main(){
        new DashBoard();
    }

    DashBoard(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.setSize(1152, 648);
        this.setResizable(false);
        this.setTitle("dashBoard");
        this.setVisible(true);

        addBook_view.addActionListener(e -> {
            BooksView addBook = new BooksView();
            this.dispose();
        });
    }

}
