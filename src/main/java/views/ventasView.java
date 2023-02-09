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
    private JTextField sventaIdCliente;
    private JPanel addventa_section;
    private JPanel searchventa_section;
    private JTextField sventaIsbn;
    private JTextField sventaIdVendedor;
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
    private JTextField filterventaIdvendedor;
    private JTabbedPane tabbedPane1;
    private JTable ventasVendedor;

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
                if (venta.newVenta(newventaIsbn.getSelectedItem().toString(), Integer.parseInt(newventaCliente.getSelectedItem().toString()), Integer.parseInt(newventaVendedor.getSelectedItem().toString()))){
                    JOptionPane.showMessageDialog(null, "Venta agregada correctamente");
                    cargarVentas();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al agregar la venta");
                }
            }
        });

        searchventaButton.addActionListener(e -> {
            if (searchventaButton.getText().equals("Limpiar")){
                searchventaId.setText("");
                sventaIdCliente.setText("");
                sventaIdVendedor.setText("");
                sventaIsbn.setText("");
                searchventaId.setEnabled(true);
                searchventaButton.setText("Buscar");
                modificarventaButton.setEnabled(false);
                eliminarventaButton.setEnabled(false);
                return;
            }
            if (searchventaId.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Debe ingresar un ID de venta");
            }else{
                Ventas venta1 = venta.getVenta(Integer.parseInt(searchventaId.getText()));
                if (venta1 != null){
                    sventaIdCliente.setText(String.valueOf(venta1.getIdCliente()));
                    sventaIdVendedor.setText(String.valueOf(venta1.getIdVendedor()));
                    sventaIsbn.setText(venta1.getIsbn());
                    modificarventaButton.setEnabled(true);
                    eliminarventaButton.setEnabled(true);
                    searchventaId.setEnabled(false);
                    searchventaButton.setText("Limpiar");
                }else{
                    JOptionPane.showMessageDialog(null, "No se encontro la venta");
                }
            }
        });

        modificarventaButton.addActionListener(e -> {
            if (sventaIsbn.getText().isEmpty() || sventaIdVendedor.getText().isEmpty() || sventaIdCliente.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Debe ingresar todos los datos");
            }else{
                if (venta.updateVenta(Integer.parseInt(searchventaId.getText()), sventaIsbn.getText(), Integer.parseInt(sventaIdVendedor.getText()), Integer.parseInt(sventaIdCliente.getText()))){
                    JOptionPane.showMessageDialog(null, "Venta modificada correctamente");
                    cargarVentas();
                    searchventaButton.doClick();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al modificar la venta");
                }
            }
        });

        eliminarventaButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar la venta?", "Eliminar venta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                if (venta.deleteVenta(Integer.parseInt(searchventaId.getText()))){
                    JOptionPane.showMessageDialog(null, "Venta eliminada correctamente");
                    cargarVentas();
                    searchventaButton.doClick();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar la venta");
                }
            }
        });

        filterventaButton.addActionListener(e -> {
            if (filterventaButton.getText().equals("Limpiar")){
                filterventaIdvendedor.setText("");
                filterventaIdvendedor.setEnabled(true);
                filterventaButton.setText("Filtrar");
                cargarVentas();
                return;
            }
            if (filterventaIdvendedor.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Debe ingresar un ID de vendedor");
            }else{
                Ventas venta1 = new Ventas();
                List<Ventas> ventas = venta1.getVentasByVendor(Integer.parseInt(filterventaIdvendedor.getText()));
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("ID_Venta");
                model.addColumn("ID_Cliente");
                model.addColumn("ID_Vendedor");
                model.addColumn("ISBN_Libro");
                for (Ventas v : ventas){
                    model.addRow(new Object[]{v.getIdVenta(), v.getIdCliente(), v.getIdVendedor(), v.getIsbn()});
                }
                ventaTable.setModel(model);
                filterventaIdvendedor.setEnabled(false);
                filterventaButton.setText("Limpiar");
            }
        });

    }

    private void cargarVentas(){
        Ventas venta = new Ventas();
//        vestas all
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

//      Vestas / vendedor
        Vendedores vendedor = new Vendedores();
        List<Vendedores> vendedores = vendedor.getVendedores();
        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("ID_Vendedor");
        model1.addColumn("Total de ventas");
        int total = 0;
        for (Vendedores v : vendedores){
            List<Ventas> ventas1 = venta.getVentasByVendor(v.getIdVendedor());
            total = ventas1.size();
            model1.addRow(new Object[]{v.getIdVendedor(), total});
        }
        ventasVendedor.setModel(model1);
    }

}
