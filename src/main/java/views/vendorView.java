package views;

import entity.Vendedores;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class vendorView extends JFrame{
    private JTabbedPane vendorTabbedPane;
    private JPanel addvendor_section;
    private JButton newvendorButton;
    private JTextField newvendorName;
    private JTextField newvendorUsername;
    private JPanel searchvendor_section;
    private JTextField svendorName;
    private JTextField svendorUsername;
    private JTextField searchvendorusername;
    private JButton searchvendorButton;
    private JButton modificarvendorButton;
    private JButton eliminarvendorButton;
    private JPanel viewvendor;
    private JTextField filtervendorusername;
    private JButton filtervendorButton;
    private JTable vendorTable;

    public static void main(){
        new vendorView();
    }

    vendorView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(vendorTabbedPane);
        this.setSize(1152, 648);
        this.setResizable(false);
        this.setTitle("dashBoard");

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new java.awt.Color(0, 0, 0));
        JMenuItem menuItem = new JMenuItem("Volver al menu");
        menuItem.setForeground(new java.awt.Color(255, 255, 255));
        menuItem.setBackground(new java.awt.Color(0, 0, 0));
        menuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuBar.add(menuItem);
        this.setJMenuBar(menuBar);
        menuItem.addActionListener(e -> {
            DashBoard dashBoard = new DashBoard();
            this.dispose();
        });

        this.setVisible(true);
        modificarvendorButton.setEnabled(false);
        eliminarvendorButton.setEnabled(false);
        cargarVendor();

        Vendedores vendor = new Vendedores();

        newvendorButton.addActionListener(e -> {
            if (newvendorName.getText().isEmpty() || newvendorUsername.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos");
            } else if(vendor.newVendedor(newvendorName.getText(), newvendorUsername.getText())){
                JOptionPane.showMessageDialog(null, "Vendedor añadido correctamente");
                newvendorName.setText("");
                newvendorUsername.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Vendedor ya existente");
            }
            cargarVendor();
        });

        searchvendorButton.addActionListener(e -> {
            if (searchvendorButton.getText().equals("Limpiar")){
                svendorName.setText("");
                svendorUsername.setText("");
                searchvendorusername.setText("");
                searchvendorusername.setEnabled(true);
                searchvendorButton.setText("Buscar");
                modificarvendorButton.setEnabled(false);
                eliminarvendorButton.setEnabled(false);
                return;
            }
            if (searchvendorusername.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos");
            } else {
                Vendedores vendorSeleccionado = vendor.getVendedor(searchvendorusername.getText());
                if (vendorSeleccionado != null){
                    svendorName.setText(vendorSeleccionado.getNombre());
                    svendorUsername.setText(vendorSeleccionado.getUsername());
                    searchvendorusername.setEnabled(false);
                    searchvendorButton.setText("Limpiar");
                    modificarvendorButton.setEnabled(true);
                    eliminarvendorButton.setEnabled(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Vendedor no encontrado");
                }
            }
        });

        modificarvendorButton.addActionListener(e -> {
            if (svendorName.getText().isEmpty() || svendorUsername.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos");
            } else if(vendor.updateVendedor(searchvendorusername.getText(), svendorName.getText(), svendorUsername.getText())){
                JOptionPane.showMessageDialog(null, "Vendedor modificado correctamente");
                svendorName.setText("");
                svendorUsername.setText("");
                searchvendorusername.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Vendedor no encontrado");
            }
            cargarVendor();
        });

        eliminarvendorButton.addActionListener(e -> {
            if (searchvendorusername.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos");
            } else if(vendor.deleteVendedor(searchvendorusername.getText())){
                JOptionPane.showMessageDialog(null, "Vendedor eliminado correctamente");
                svendorName.setText("");
                svendorUsername.setText("");
                searchvendorusername.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Vendedor no encontrado");
            }
            cargarVendor();
        });

        filtervendorButton.addActionListener(e -> {
            if (filtervendorButton.getText().equals("Limpiar")){
                filtervendorButton.setText("Filtrar");
                filtervendorusername.setEnabled(true);
                filtervendorusername.setText("");
                cargarVendor();
                return;
            }
            if (filtervendorusername.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos");
            } else {
                Vendedores vendorSeleccionado = vendor.getVendedor(filtervendorusername.getText());
                if (vendorSeleccionado != null){
//                    fill the table with the selected vendor
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("ID");
                    model.addColumn("Nombre");
                    model.addColumn("Username");
                    Object[] row = new Object[3];
                    row[0] = vendorSeleccionado.getIdVendedor();
                    row[1] = vendorSeleccionado.getNombre();
                    row[2] = vendorSeleccionado.getUsername();
                    model.addRow(row);
                    vendorTable.setModel(model);
                    filtervendorButton.setText("Limpiar");
                    filtervendorusername.setEnabled(false);


                } else {
                    JOptionPane.showMessageDialog(null, "Vendedor no encontrado");
                }
            }
        });
    }

    public void cargarVendor(){
        Vendedores vendor = new Vendedores();
        List<Vendedores> listaVendedores = vendor.getVendedores();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Username");
        Object[] row = new Object[3];
        for (int i = 0; i < listaVendedores.size(); i++) {
            row[0] = listaVendedores.get(i).getIdVendedor();
            row[1] = listaVendedores.get(i).getNombre();
            row[2] = listaVendedores.get(i).getUsername();
            model.addRow(row);
        }
        vendorTable.setModel(model);
    }

}
