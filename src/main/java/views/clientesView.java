package views;

import entity.Clientes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class clientesView extends JFrame{


    private JTabbedPane clientesTabbedPane;
    private JPanel addClient_section;
    private JButton newClientButton;
    private JTextField newClientName;
    private JTextField sClienteName;
    private JPanel searchCliente_section;
    private JTextField searchClientId;
    private JButton searchClientButton;
    private JPanel viewClientes;
    private JTextField filterClienteId;
    private JButton filterClienteButton;
    private JTable booksTable;
    private JPanel panel1;
    private JButton modificarClienteButton;
    private JButton eliminarClienteButton;

    clientesView() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1152, 648);
        this.setResizable(false);
        this.setTitle("Añadir Cliente");
        this.setContentPane(panel1);

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
        modificarClienteButton.setEnabled(false);
        eliminarClienteButton.setEnabled(false);
        cargarClientes();
        Clientes cliente = new Clientes();

        newClientButton.addActionListener(e -> {
            if (newClientName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos");
            } else {
                if (cliente.newCliente(newClientName.getText())) {
                    JOptionPane.showMessageDialog(null, "Cliente añadido correctamente");
                    newClientName.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente ya existente");
                }
            }
            cargarClientes();
        });

        searchClientButton.addActionListener(e -> {
            if (searchClientButton.getText().equals("Limpiar")) {
                searchClientId.setText("");
                sClienteName.setText("");
                searchClientId.setEnabled(true);
                searchClientButton.setText("Buscar");
                modificarClienteButton.setEnabled(false);
                eliminarClienteButton.setEnabled(false);
                return;
            }
            if (searchClientId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos");
            } else {
                Clientes clienteSeleccionado = cliente.getCliente(Integer.parseInt(searchClientId.getText()));
                if (clienteSeleccionado != null) {
                    sClienteName.setText(clienteSeleccionado.getNombre());
                    searchClientId.setEnabled(false);
                    searchClientButton.setText("Limpiar");
                    modificarClienteButton.setEnabled(true);
                    eliminarClienteButton.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado");
                }

            }
        });

        modificarClienteButton.addActionListener(e -> {
            if (searchClientId.getText().isEmpty() || sClienteName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos");
            } else {
                if (sClienteName.getText().length() > 25){
                    JOptionPane.showMessageDialog(null, "El nombre del cliente no puede tener más de 25 caracteres");
                    return;
                }
                if (cliente.updateCliente(Integer.parseInt(searchClientId.getText()), sClienteName.getText())) {
                    JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
                    searchClientId.setText("");
                    sClienteName.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado" + searchClientId.getText());
                }
            }
            cargarClientes();
        });

        eliminarClienteButton.addActionListener(e -> {
            if (searchClientId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos");
            } else {
                if (cliente.deleteCliente(Integer.parseInt(searchClientId.getText()))) {
                    JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente");
                    searchClientId.setText("");
                    sClienteName.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado");
                }
            }
            cargarClientes();
        });

        filterClienteButton.addActionListener(e -> {
            if (filterClienteButton.getText().equals("limpiar")){
                filterClienteId.setText("");
                filterClienteButton.setText("filtrar");
                filterClienteId.setEnabled(true);
                cargarClientes();
                return;
            }
            if (filterClienteId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos");
            } else {
                Clientes clienteSeleccionado = cliente.getCliente(Integer.parseInt(filterClienteId.getText()));
                if (clienteSeleccionado != null) {
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("ID");
                    model.addColumn("Nombre");
                    model.addRow(new Object[]{clienteSeleccionado.getIdCliente(), clienteSeleccionado.getNombre()});
                    booksTable.setModel(model);
                    filterClienteButton.setText("limpiar");
                    filterClienteId.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado");
                }

            }
        });


    }

    public void cargarClientes(){
        Clientes cliente = new Clientes();
        List<Clientes> clientes = cliente.getClientes();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        for (Clientes c : clientes){
            model.addRow(new Object[]{c.getIdCliente(), c.getNombre()});
        }
        booksTable.setModel(model);
    }
}
