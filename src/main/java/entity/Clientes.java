package entity;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Clientes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cliente")
    private int idCliente;
    @Basic
    @Column(name = "nombre")
    private String nombre;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clientes clientes = (Clientes) o;

        if (idCliente != clientes.idCliente) return false;
        if (nombre != null ? !nombre.equals(clientes.nombre) : clientes.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCliente;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }
}
