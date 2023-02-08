package views;

import javax.swing.*;

public class BooksView extends JFrame {

    private JPanel panel1;
    private JTabbedPane booksTabbedPane;
    private JPanel addBook_section;
    private JPanel searchBook_section;
    private JButton crearButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    BooksView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1152, 648);
        this.setResizable(false);
        this.setTitle("Añadir Libro");
        this.setContentPane(panel1);
        this.setVisible(true);
    }
}
