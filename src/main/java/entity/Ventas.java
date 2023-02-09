package entity;

import javax.persistence.*;
import java.util.List;

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

    public boolean ventaExist(int idVenta){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Ventas venta = em.find(Ventas.class, idVenta);
            et.commit();
            if(venta != null){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            em.close();
            emf.close();
        }
    }
    public boolean newVenta(String isbn, int idCliente, int idVendedor){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Ventas venta = new Ventas();

//            Clientes cliente = new Clientes();
//            if (!cliente.clienteExiste(idCliente)){
//                System.out.println("Cliente no existe");
//                return false;
//            }
//            Libros libro = new Libros();
//            if (!libro.bookExists(isbn)){
//                System.out.printf("Libro no existe");
//                return false;
//            }
//
//            Vendedores vendedor = new Vendedores();
//            if (!vendedor.vendorExistId(idVendedor)){
//                System.out.println("Vendedor no existe");
//                return false;
//            }

            venta.setIsbn(isbn);
            venta.setIdCliente(idCliente);
            venta.setIdVendedor(idVendedor);
            Query query = em.createNativeQuery("INSERT INTO ventas (isbn, id_cliente, id_vendedor) VALUES (?, ?, ?)");
            query.setParameter(1, isbn);
            query.setParameter(2, idCliente);
            query.setParameter(3, idVendedor);
            query.executeUpdate();
            et.commit();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            em.close();
            emf.close();
        }
    }
    public boolean deleteVenta(int idVenta){
        if (!ventaExist(idVenta)){
            return false;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Ventas venta = em.find(Ventas.class, idVenta);
            if(venta != null){
                em.remove(venta);
                et.commit();
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            em.close();
            emf.close();
        }
    }
    public boolean updateVenta(int idVenta, String isbn, int idCliente, int idVendedor){
        if (!ventaExist(idVenta)){
            return false;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Ventas venta = em.find(Ventas.class, idVenta);
            if(venta != null){
                Libros libro = new Libros();
                if (!libro.bookExists(isbn)){
                    return false;
                }
                Clientes cliente = new Clientes();
                if (!cliente.clienteExiste(idCliente)){
                    return false;
                }
                Vendedores vendedor = new Vendedores();
                if (!vendedor.vendorExistId(idVendedor)){
                    return false;
                }
                venta.setIsbn(isbn);
                venta.setIdCliente(idCliente);
                venta.setIdVendedor(idVendedor);
                em.merge(venta);
                et.commit();
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            em.close();
            emf.close();
        }
    }
    public List<Ventas> getVentas(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Query query = em.createQuery("SELECT v FROM Ventas v");
            List<Ventas> ventas = query.getResultList();
            et.commit();
            return ventas;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            em.close();
            emf.close();
        }
    }
    public List<Ventas> getVentasByVendor(int idVendedor){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Query query = em.createQuery("SELECT v FROM Ventas v WHERE v.idVendedor = :idVendedor");
            query.setParameter("idVendedor", idVendedor);
            List<Ventas> ventas = query.getResultList();
            et.commit();
            return ventas;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            em.close();
            emf.close();
        }
    }
    public List<Ventas> getVentasByClient(int idCliente){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Query query = em.createQuery("SELECT v FROM Ventas v WHERE v.idCliente = :idCliente");
            query.setParameter("idCliente", idCliente);
            List<Ventas> ventas = query.getResultList();
            et.commit();
            return ventas;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            em.close();
            emf.close();
        }
    }
    public List<Ventas> getVentasByBook(String isbn){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Query query = em.createQuery("SELECT v FROM Ventas v WHERE v.isbn = :isbn");
            query.setParameter("isbn", isbn);
            List<Ventas> ventas = query.getResultList();
            et.commit();
            return ventas;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            em.close();
            emf.close();
        }
    }
    public Ventas getVenta(int idVenta){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Ventas venta = em.find(Ventas.class, idVenta);
            et.commit();
            return venta;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            em.close();
            emf.close();
        }
    }
}
