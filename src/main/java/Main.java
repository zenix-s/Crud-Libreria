import entity.Libros;
import entity.Vendedores;

import javax.persistence.*;

public class Main {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
//            Add book
//            Libros libro = new Libros();
//            libro.setIsbn("123-45-67894-23-6");
//            libro.setTitulo("El Quijote");
//            libro.setAutor("Miguel de Cervantes");
//            libro.setEditorial("Planeta");
//            libro.setPrecio(1000);
//            em.persist(libro);

//            Delete book
//            Libros libro = em.find(Libros.class, 1);
//            em.remove(libro);

//            Update book
//            Libros libro = em.find(Libros.class, 2);
//            libro.setTitulo("El Quijote de la Mancha");
//            em.merge(libro);



            et.commit();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}
