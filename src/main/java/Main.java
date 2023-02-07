import entity.Libros;
import entity.Vendedores;
import views.dashBoard;

import javax.persistence.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction et = em.getTransaction();
//        try {
//            et.begin();
////            Add book
////            Libros libro = new Libros();
////            libro.setIsbn("123-45-67894-23-6");
////            libro.setTitulo("El Quijote");
////            libro.setAutor("Miguel de Cervantes");
////            libro.setEditorial("Planeta");
////            libro.setPrecio(1000);
////            em.persist(libro);
//
////            Delete book
////            Libros libro = em.find(Libros.class, 1);
////            em.remove(libro);
//
////            Update book
////            Libros libro = em.find(Libros.class, 2);
////            libro.setTitulo("El Quijote de la Mancha");
////            em.merge(libro);
//
////            Visualizar libro
////            Libros libro = em.find(Libros.class, 2);
////            System.out.println(libro.toString());
//
////            En un array meter todas las entradas de Libros
//            HashMap<Integer, Libros> libros = new HashMap<>();
//            Query query = em.createQuery("SELECT l FROM Libros l where l.precio > 2500");
//            for (Object o : query.getResultList()) {
//                Libros libro = (Libros) o;
//                libros.put(libro.getCodigo(), libro);
//            }
//            System.out.println(libros.toString());
//
//            et.commit();
//        } catch (Exception e) {
//            System.out.println("error: " + e.getMessage());
//        } finally {
//            em.close();
//            emf.close();
//        }

//        execute dashBoard view form from views package
        dashBoard.main(args);

    }
}
