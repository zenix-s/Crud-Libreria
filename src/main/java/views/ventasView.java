package views;

import entity.Clientes;
import entity.Libros;
import entity.Vendedores;
import entity.Ventas;

import javax.swing.*;
import javax.swing.table.*;
import java.util.List;

public class ventasView extends JFrame{

    private JTabbedPane ventaTabbedPane;
    private JButton newventaButton;
    private JComboBox newventaIsbn;
    private JComboBox newventaVendedor;
    private JComboBox newventaCliente;
    private JTextField textField1;
    private JPanel addventa_section;
    private JPanel searchventa_section;
    private JTextField sventaName;
    private JTextField sventaUsername;
    private JTextField searchventaId;
    private JButton searchventaButton;
    private JPanel viewventa;
    private JButton modificarventaButton;
    private JButton eliminarventaButton;
    private JTextField filterventaIdCliente;
    private JButton filterventaButton;
    private JTable ventaTable;

    public void main(){
        new ventasView();
    }

    private JPanel panel1;

    ventasView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
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
        modificarventaButton.setEnabled(false);
        eliminarventaButton.setEnabled(false);
        cargarVentas();
        newventaIsbn.addItem("Seleccione un libro");
        newventaVendedor.addItem("Seleccione un vendedor");
        newventaCliente.addItem("Seleccione un cliente");
        Libros libro = new Libros();
        List<Libros> libros = libro.getBooks();
        for (Libros l : libros){
            newventaIsbn.addItem(l.getIsbn());
        }
        Vendedores vendedor = new Vendedores();
        List<Vendedores> vendedores = vendedor.getVendedores();
        for (Vendedores v : vendedores){
            newventaVendedor.addItem(v.getIdVendedor());
        }
        Clientes cliente = new Clientes();
        List<Clientes> clientes = cliente.getClientes();
        for (Clientes c : clientes){
            newventaCliente.addItem(c.getIdCliente());
        }

        Ventas venta = new Ventas();
        newventaButton.addActionListener(e -> {
            if (newventaIsbn.getSelectedIndex() == 0 || newventaVendedor.getSelectedIndex() == 0 || newventaCliente.getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un libro, un vendedor y un cliente");
            }else{
                if (venta.newVenta(newventaIsbn.getSelectedItem().toString(), Integer.parseInt(newventaVendedor.getSelectedItem().toString()), Integer.parseInt(newventaCliente.getSelectedItem().toString()))){
                    JOptionPane.showMessageDialog(null, "Venta agregada correctamente");
                    cargarVentas();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al agregar la venta");
                }
            }
        });


    }

    private void cargarVentas(){
        Ventas venta = new Ventas();
        List<Ventas> ventas = venta.getVentas();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID_Venta");
        model.addColumn("ID_Cliente");
        model.addColumn("ID_Vendedor");
        model.addColumn("ISBN_Libro");
        for (Ventas v : ventas){
            model.addRow(new Object[]{v.getIdVenta(), v.getIdCliente(), v.getIdVendedor(), v.getIsbn()});
        }
        ventaTable.setModel(model);
    }

}
