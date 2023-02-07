package entity;

import javax.persistence.*;

@Entity
@Table(name = "ventas")
public class Ventas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_venta")
    private int idVenta;
    @Basic
    @Column(name = "isbn")
    private String isbn;
    @Basic
    @Column(name = "id_cliente")
    private int idCliente;
    @Basic
    @Column(name = "id_vendedor")
    private int idVendedor;

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ventas ventas = (Ventas) o;

        if (idVenta != ventas.idVenta) return false;
        if (idCliente != ventas.idCliente) return false;
        if (idVendedor != ventas.idVendedor) return false;
        if (isbn != null ? !isbn.equals(ventas.isbn) : ventas.isbn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVenta;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + idCliente;
        result = 31 * result + idVendedor;
        return result;
    }
}
