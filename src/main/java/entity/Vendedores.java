// @author: sergio fernandez fernandez
// @date: 2023/02/12
// @github: https://github.com/zenix-s
// @webpage: https://setfernet.com/
package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vendedores")
public class Vendedores {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_vendedor")
    private int idVendedor;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "username")
    private String username;

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vendedores that = (Vendedores) o;

        if (idVendedor != that.idVendedor) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVendedor;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    public boolean vendedorExiste(String username){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Vendedores vendedor = em.createQuery("SELECT v FROM Vendedores v WHERE v.username = :username", Vendedores.class)
                    .setParameter("username", username)
                    .getSingleResult();
            if (vendedor != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
            emf.close();
        }
    }
    public boolean vendorExistId(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Vendedores vendedor = em.find(Vendedores.class, id);
            if (vendedor != null) {
                return true;
            } else {
                System.out.println("No existe el vendedor");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
            emf.close();
        }
    }
    public boolean newVendedor(String nombre, String username){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Vendedores vendedor = new Vendedores();
            vendedor.setNombre(nombre);
            vendedor.setUsername(username);
            em.persist(vendedor);
            et.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
            emf.close();
        }
    }

    public boolean deleteVendedor(String username){
        if (!vendedorExiste(username)){
            return false;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Vendedores vendedor = em.createQuery("SELECT v FROM Vendedores v WHERE v.username = :username", Vendedores.class)
                    .setParameter("username", username)
                    .getSingleResult();
            em.remove(vendedor);
            et.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
            emf.close();
        }
    }

    public boolean updateVendedor(String prevUsername,String name, String username){
        if (!vendedorExiste(prevUsername)){
            return false;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Vendedores vendedor = em.createQuery("SELECT v FROM Vendedores v WHERE v.username = :username", Vendedores.class)
                    .setParameter("username", prevUsername)
                    .getSingleResult();
            vendedor.setNombre(name);
            vendedor.setUsername(username);
            em.merge(vendedor);
            et.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
            emf.close();
        }
    }

    public Vendedores getVendedor(String username){
        if (!vendedorExiste(username)){
            return null;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Vendedores vendedor = em.createQuery("SELECT v FROM Vendedores v WHERE v.username = :username", Vendedores.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return vendedor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            emf.close();
        }
    }

    public List<Vendedores> getVendedores(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            List<Vendedores> vendedores = em.createQuery("SELECT v FROM Vendedores v", Vendedores.class)
                    .getResultList();
            return vendedores;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            emf.close();
        }
    }

}
