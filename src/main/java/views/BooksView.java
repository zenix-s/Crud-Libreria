package views;

import entity.Libros;

import javax.swing.*;
import javax.swing.table.*;
import java.util.List;

public class BooksView extends JFrame {

    private JPanel panel1;
    private JTabbedPane booksTabbedPane;
    private JPanel addBook_section;
    private JPanel searchBook_section;
    private JButton newBookButton;
    private JTextField newBookIsbn;
    private JTextField newBookTitulo;
    private JTextField newBookAutor;
    private JTextField newBookEditorial;
    private JTextField newBookPrecio;
    private JTextField searchBookIsbn;
    private JTextField sBookIsbn;
    private JTextField sBookTitulo;
    private JTextField sBookAutor;
    private JTextField sBookEditorial;
    private JTextField sBookPrecio;
    private JButton modificarBookButton;
    private JButton searchBookButton;
    private JButton eliminarBookButton;
    private JPanel viewBooks;
    private JTextField filterBookAutor;
    private JButton filterBookButton;
    private JTable booksTable;

    BooksView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1152, 648);
        this.setResizable(false);
        this.setTitle("Añadir Libro");
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
        modificarBookButton.setEnabled(false);
        eliminarBookButton.setEnabled(false);
        cargarLibros();
        Libros libro = new Libros();
//        Metodo para añadir un libro
        newBookButton.addActionListener(e -> {
            if (newBookIsbn.getText().isEmpty() || newBookTitulo.getText().isEmpty() || newBookAutor.getText().isEmpty() || newBookEditorial.getText().isEmpty() || newBookPrecio.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos");
            } else {
                if (libro.newBook(newBookIsbn.getText(), newBookTitulo.getText(), newBookAutor.getText(), newBookEditorial.getText(), Integer.parseInt(newBookPrecio.getText()))){
                    JOptionPane.showMessageDialog(null, "Libro añadido correctamente");
                    newBookIsbn.setText("");
                    newBookTitulo.setText("");
                    newBookAutor.setText("");
                    newBookEditorial.setText("");
                    newBookPrecio.setText("");
                    cargarLibros();
                } else {
                    JOptionPane.showMessageDialog(null, "ISBN ya existente");
                }
            }
        });
//        Metodo para buscar un libro
        searchBookButton.addActionListener(e -> {
            if (searchBookButton.getText().equals("Limpiar")){
                sBookTitulo.setText("");
                sBookAutor.setText("");
                sBookEditorial.setText("");
                sBookPrecio.setText("");
                searchBookIsbn.setEnabled(true);
                searchBookButton.setText("Buscar");
                modificarBookButton.setEnabled(false);
                eliminarBookButton.setEnabled(false);
                return;
            }
            Libros libroBuscado = libro.getBook(searchBookIsbn.getText());
            JOptionPane.showMessageDialog(null, "Buscando libro...");
            if (libroBuscado != null){
                sBookTitulo.setText(libroBuscado.getTitulo());
                sBookAutor.setText(libroBuscado.getAutor());
                sBookEditorial.setText(libroBuscado.getEditorial());
                sBookPrecio.setText(String.valueOf(libroBuscado.getPrecio()));
                searchBookIsbn.setEnabled(false);
                searchBookButton.setText("Limpiar");
                modificarBookButton.setEnabled(true);
                eliminarBookButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado el libro");
            }
        });
//        Metodo para modificar un libro
        modificarBookButton.addActionListener(e -> {
            if (libro.updateBook(searchBookIsbn.getText(), sBookTitulo.getText(), sBookAutor.getText(), sBookEditorial.getText(), Integer.parseInt(sBookPrecio.getText()))){
                JOptionPane.showMessageDialog(null, "Libro modificado correctamente");
                sBookTitulo.setText("");
                sBookAutor.setText("");
                sBookEditorial.setText("");
                sBookPrecio.setText("");
                searchBookIsbn.setText("");
                cargarLibros();
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido modificar el libro");
            }
        });
//        Metodo para eliminar un libro
        eliminarBookButton.addActionListener(e -> {
            if (libro.deleteBook(searchBookIsbn.getText())){
                JOptionPane.showMessageDialog(null, "Libro eliminado correctamente");
                sBookTitulo.setText("");
                sBookAutor.setText("");
                sBookEditorial.setText("");
                sBookPrecio.setText("");
                searchBookIsbn.setText("");
                cargarLibros();
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido eliminar el libro");
            }
        });

        filterBookButton.addActionListener(e -> {
            if(filterBookButton.getText().equals("Limpiar")){
                filterBookButton.setText("Filtrar");
                filterBookAutor.setText("");
                filterBookAutor.setEnabled(true);
                cargarLibros();
                return;
            }
            if (filterBookAutor.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, rellene el campo");
            } else {
                List<Libros> libros = libro.getBooks();
                DefaultTableModel modelo = new DefaultTableModel();
                modelo.addColumn("ISBN");
                modelo.addColumn("TITULO");
                modelo.addColumn("AUTOR");
                modelo.addColumn("EDITORIAL");
                modelo.addColumn("PRECIO");
                Object[] head = new Object[5];
                head[0] = "ISBN";
                head[1] = "TITULO";
                head[2] = "AUTOR";
                head[3] = "EDITORIAL";
                head[4] = "PRECIO";
                modelo.addRow(head);
                for (Libros l : libros){
                    if (l.getAutor().equals(filterBookAutor.getText())){
                        Object[] fila = new Object[5];
                        fila[0] = l.getIsbn();
                        fila[1] = l.getTitulo();
                        fila[2] = l.getAutor();
                        fila[3] = l.getEditorial();
                        fila[4] = l.getPrecio();
                        modelo.addRow(fila);
                    }
                }
                booksTable.setModel(modelo);
                filterBookButton.setText("Limpiar");
                filterBookAutor.setEnabled(false);
            }

        });
    }
    public void cargarLibros(){
        Libros libro = new Libros();
        List<Libros> libros = libro.getBooks();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ISBN");
        modelo.addColumn("TITULO");
        modelo.addColumn("AUTOR");
        modelo.addColumn("EDITORIAL");
        modelo.addColumn("PRECIO");
        for (Libros l : libros){
            Object[] fila = new Object[5];
            fila[0] = l.getIsbn();
            fila[1] = l.getTitulo();
            fila[2] = l.getAutor();
            fila[3] = l.getEditorial();
            fila[4] = l.getPrecio();
            modelo.addRow(fila);
        }

        booksTable.setModel(modelo);
    }

}
