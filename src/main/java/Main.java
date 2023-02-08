import views.DashBoard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        connect();
        DashBoard.main();
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
//        dashBoard.main(args);


    }
    public static void connect(){
        String url = "jdbc:mysql://localhost:3306/valar";
        String username = "root";
        String password = "rootpass123";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Conexión exitosa!");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
