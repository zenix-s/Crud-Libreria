package entity;

import javax.persistence.*;

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
}
