package views;

import javax.swing.*;

public class DashBoard extends JFrame{
    private JPanel panel1;
    private JButton vendedoresView;
    private JButton ventasView;
    private JButton BookView;
    private JButton ClientesView;

    public static void main(){
        new DashBoard();
    }

    DashBoard(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.setSize(1152, 648);
        this.setResizable(false);
        this.setTitle("dashBoard");

        JMenuBar menuBar = new JMenuBar();
        JMenuItem menuItem = new JMenuItem("Salir");
        menuItem.setBackground(new java.awt.Color(41, 119, 117));
        menuItem.setForeground(new java.awt.Color(255, 255, 255));
        menuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuBar.add(menuItem);
        this.setJMenuBar(menuBar);
        menuItem.addActionListener(e -> {
            System.exit(0);
        });



        this.setVisible(true);

        BookView.addActionListener(e -> {
            BooksView addBook = new BooksView();
            this.dispose();
        });

        ClientesView.addActionListener(e -> {
            clientesView addCliente = new clientesView();
            this.dispose();
        });

        vendedoresView.addActionListener(e -> {
            vendorView addVendor = new vendorView();
            this.dispose();
        });

        ventasView.addActionListener(e -> {
            ventasView addVenta = new ventasView();
            this.dispose();
        });
    }

}
