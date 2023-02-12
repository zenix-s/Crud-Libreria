// @author: sergio fernandez fernandez
// @date: 2023/02/12
// @github: https://github.com/zenix-s
// @webpage: https://setfernet.com/
package entity;

import javax.persistence.*;
import java.util.List;

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

    @Basic
    @Column(name = "dni")
    private String dni;

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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public boolean clienteExiste(int idCliente){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Clientes cliente = em.find(Clientes.class, idCliente);
            if(cliente != null){
                return true;
            }
            et.commit();
        }catch(Exception e){
            et.rollback();
        }finally{
            em.close();
            emf.close();
        }
        return false;
    }

    public boolean newCliente(String nombre, String dni){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Clientes cliente = new Clientes();
            cliente.setNombre(nombre);
            cliente.setDni(dni);
            em.persist(cliente);
            et.commit();
            return true;
        }catch(Exception e){
            et.rollback();
            return false;
        }finally{
            em.close();
            emf.close();
        }
    }

    public boolean deleteCliente(int idCliente){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Clientes cliente = em.find(Clientes.class, idCliente);
            em.remove(cliente);
            et.commit();
            return true;
        }catch(Exception e){
            et.rollback();
            return false;
        }finally{
            em.close();
            emf.close();
        }
    }

    public boolean updateCliente(int idCliente, String nombre, String dni){
        if(!clienteExiste(idCliente)){
            return false;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Clientes cliente = em.find(Clientes.class, idCliente);
            cliente.setNombre(nombre);
            cliente.setDni(dni);
            em.merge(cliente);
            et.commit();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            em.close();
            emf.close();
        }
    }

    public Clientes getCliente(int idCliente){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Clientes cliente = em.find(Clientes.class, idCliente);
            et.commit();
            return cliente;
        }catch(Exception e){
            et.rollback();
            return null;
        }finally{
            em.close();
            emf.close();
        }
    }

    public List<Clientes> getClientes(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            List<Clientes> clientes = em.createQuery("SELECT c FROM Clientes c", Clientes.class).getResultList();
            et.commit();
            return clientes;
        }catch(Exception e){
            et.rollback();
            return null;
        }finally{
            em.close();
            emf.close();
        }
    }


}
