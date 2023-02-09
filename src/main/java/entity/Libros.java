package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libros {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigo")
    private int codigo;
    @Basic
    @Column(name = "isbn")
    private String isbn;
    @Basic
    @Column(name = "titulo")
    private String titulo;
    @Basic
    @Column(name = "autor")
    private String autor;
    @Basic
    @Column(name = "editorial")
    private String editorial;
    @Basic
    @Column(name = "precio")
    private Integer precio;


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Libros libros = (Libros) o;

        if (codigo != libros.codigo) return false;
        if (isbn != null ? !isbn.equals(libros.isbn) : libros.isbn != null) return false;
        if (titulo != null ? !titulo.equals(libros.titulo) : libros.titulo != null) return false;
        if (autor != null ? !autor.equals(libros.autor) : libros.autor != null) return false;
        if (editorial != null ? !editorial.equals(libros.editorial) : libros.editorial != null) return false;
        if (precio != null ? !precio.equals(libros.precio) : libros.precio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (autor != null ? autor.hashCode() : 0);
        result = 31 * result + (editorial != null ? editorial.hashCode() : 0);
        result = 31 * result + (precio != null ? precio.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Libros{" +
                "codigo=" + codigo +
                ", isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editorial='" + editorial + '\'' +
                ", precio=" + precio +
                "}\n";
    }
    public boolean bookExists(String isbn){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Libros libro = em.createQuery("SELECT l FROM Libros l WHERE l.isbn = :isbn", Libros.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult();
            if(libro != null){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            em.close();
            emf.close();
        }
    }
    public boolean newBook(String isbn, String titulo, String autor, String editorial, int precio){
        if(bookExists(isbn)){
            return false;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Libros libro = new Libros();
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setPrecio(precio);
            em.persist(libro);
            et.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            em.close();
            emf.close();
        }
    }
    public boolean deleteBook(String isbn){
        if(!bookExists(isbn)){
            return false;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Libros libro = (Libros) em.createQuery("SELECT l FROM Libros l WHERE l.isbn = :isbn").setParameter("isbn", isbn).getSingleResult();
            em.remove(libro);
            et.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            em.close();
            emf.close();
        }
    }
    public boolean updateBook(String isbn, String titulo, String autor, String editorial, int precio){
        if(!bookExists(isbn)){
            return false;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Libros libro = (Libros) em.createQuery("SELECT l FROM Libros l WHERE l.isbn = :isbn").setParameter("isbn", isbn).getSingleResult();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setPrecio(precio);
            em.merge(libro);
            et.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            em.close();
            emf.close();
        }
    }
    public List<Libros> getBooks(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            List<Libros> libros = em.createQuery("SELECT l FROM Libros l").getResultList();
            return libros;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            em.close();
            emf.close();
        }
    }
    public List<Libros> getBooksByAutor(String autor){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            List<Libros> libros = em.createQuery("SELECT l FROM Libros l WHERE l.autor = :autor").setParameter("autor", autor).getResultList();
            return libros;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            em.close();
            emf.close();
        }
    }
    public Libros getBook(String isbn){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try{
            et.begin();
            Libros libro = (Libros) em.createQuery("SELECT l FROM Libros l WHERE l.isbn = :isbn").setParameter("isbn", isbn).getSingleResult();
            return libro;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            em.close();
            emf.close();
        }
    }
}
