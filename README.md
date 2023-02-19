# PROYECTO HIBERNATE
---

En este proyecto se busca utilizar el framework de mapeo objeto-relación Hibernate para gestionar la persistencia de datos en nuestra aplicación. Con Hibernate, se puede mapear objetos java a trabas en una base de datos, permitiéndonos trabajar con objetos en vez de sentencias SQL.

También se utiliza Persistance una API para la persistencia de datos, permite el uso de una sintaxis unificada, además de facilitar la portabilidad y el mantenimiento del código.


## Modelo datos
---
Para el proyecto he decidido trabajar con la idea de una librería, para ello se ha utilizado 4 tablas

![[Pasted image 20230212130528.png]]

![[Pasted image 20230212152637.png]]  Unique key

![[Pasted image 20230212152722.png]]  Primary Key

![[Pasted image 20230212152814.png]] Foreign Key

## Crear Tablas
---

### Clientes
```sql
CREATE TABLE `clientes` (
	`id_cliente` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`nombre` varchar(25) NOT NULL,
	`dni` varchar(12) NOT NULL UNIQUE KEY
)
```

### Libros
```sql
CREATE TABLE `libros` (
  `codigo` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `isbn` varchar(17) DEFAULT NULL UNIQUE KEY,
  `titulo` varchar(108) DEFAULT NULL,
  `autor` varchar(49) DEFAULT NULL,
  `editorial` varchar(78) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL
)
```

### Vendedores
```sql
CREATE TABLE `vendedores` (
  `id_vendedor` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `nombre` varchar(25) NOT NULL,
  `username` varchar(25) NOT NULL UNIQUE KEY
)
```

### Ventas
```sql
CREATE TABLE `ventas` (
  `id_venta` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `isbn` varchar(17) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_vendedor` int(11) NOT NULL
)
ALTER TABLE `ventas`
  ADD KEY `isbn` (`isbn`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_vendedor` (`id_vendedor`);
```

### RELACIONES
```SQL
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ventas_ibfk_3` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedores` (`id_vendedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ventas_ibfk_4` FOREIGN KEY (`isbn`) REFERENCES `libros` (`isbn`) ON DELETE NO ACTION ON UPDATE NO ACTION;
```

## Conectar Base de Datos
---
Puesto que la aplicación se esta desarrollando con IntelliJ lo primero sea crear un nuevo proyecto
![[Pasted image 20230212161143.png]]
Nuestro Proyecto los generaremos con `Jakarta EE` Indicamos el nombre del proyecto en mi caso `ProyApp` y seleccionamos el template `Library`, el resto lo dejamos con las opciones default.

![[Pasted image 20230212161407.png]]
A continuación seleccionamos las opciones `Persistance (JPA)` y `Hibernate` y creamos el proyecto

Una vez creado el proyecto tenemos que añadir algunas cosas a nuestro `pom.xml`.
Lo primero es asegurar que la dependencia para `Hibernate` está en nuestro proyecto
```xml
<dependency>  
    <groupId>org.hibernate.orm</groupId>  
    <artifactId>hibernate-core</artifactId>  
    <version>6.0.2.Final</version>  
</dependency>
```
y puesto que vamos a conectar una base de datos mysql también tenemos que conectar la dependencia de conexión.
```xml
<dependency>  
    <groupId>mysql</groupId>  
    <artifactId>mysql-connector-java</artifactId>  
    <version>8.0.32</version>  
</dependency>
```

Con el comando `Crtl + shift + O` podemos importar las dependencias.

![[Pasted image 20230212161925.png]]

A continuación nos pedirá el enlace a nuestra base de datos, tenemos que tener en cuenta 3 valores:
1. Localización de la base de datos - localhost
2. Puerto - 3306
3. Nombre de la base de datos - valar
`jdbc:mysql://localhost:3306/valar`

![[Pasted image 20230212162239.png]]
Una vez conectada veremos algo como esto
![[Pasted image 20230212162308.png]]

Aquí se nos piden algunos datos como el usuario y la contraseña de nuestra base de datos, rellenamos esos campos y pulsamos ok, también tenemos la opción de testear la conexión para ver si todo está correcto, si lo hemos hecho todo bien veremos algo como esto:
![[Pasted image 20230212162606.png]]
Ahora vamos a generar la persistencia de nuestra base de datos.

![[9 1.png]]


una vez abierto hacemos click derecho sobre default y Pulsamos en la opción `By Database Schema`
![[adjuntos/10.png]]
Indicamos las tablas a importar
![[adjuntos/11.png]]

Una vez finalizado podemos ver en nuestra carpeta entity a las clases generadas por el mapeo del Hibernate
![[adjuntos/12.png]]

y en el default las tablas
![[adjuntos/13.png]]

## Cambios
---
Para hacer funcionar correctamente todo esto hay que realizar algunos cambios

### persistance.xml
tendremos que cambiar el persistance xml y dejarlo algo como esto, los propery username y password deben ser los de la base de datos y la url la introducida anteriormente
```xml
<property name="hibernate.connection.url" value="jdbc:mysql://localhost:3306/valar"/>  
<property name="hibernate.connection.driver_class" value="com.mysql.cj.jdbc.Driver"/>  
<property name="hibernate.connection.username" value="root"/>  
<property name="hibernate.connection.password" value="rootpass123"/>  
<property name="hibernate.dialect" value="org.hibernate.dialect.MySQL5Dialect"/>  
<property name="hibernate.show_sql" value="true"/>
```

### Clases entity
En cada clase entity generada debemos alterar una serie de partes, nuestro documento ha de pasar de lucir asi:
```java
package entity;  
import jakarta.persistence.*;  
@Entity  
public class Clientes
```

Tendrá que lucir así, debemos modificar la librería desde donde importamos el persistance y agregar el @Table con el nombre de la tabla

```java
package entity;  
  
import javax.persistence.*;  
  
@Entity  
@Table(name = "clientes")  
public class Clientes
```

## Consultas
---
Una vez realizados los pasos anteriores podemos empezar a realizar algunas consultas

### Select
Podemos hacer un select buscando por el valor de la primary key.
En esta función le pasamos el id de un cliente y comprobará si existe en ese caso devolverá True en caso contrario False.
El cliente recogido se almacena en un objeto
```java
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
```

También podemos hacer una función que devuelva al cliente
```java
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
```

EntityManager también nos permite realizar querys más complejas, lo que nos devolverá un List de objetos de nuestra clase
```java
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
```

### Insert / añadir
Para añadir un nuevo registro podemos usar el siguiente código.
Se crea un objeto de la clase Clientes y a este se le añaden los valores correspondientes, posteriormente esto nos permite con el persist añadirlo.
```java
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
```

### Update
Podemos actualizar un registro con el merge, para ello necesitamos un objeto de la clase y el comando merge
```java
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
```

### Delete
Eliminar un valor es igualmente similar solo necesitamos un objeto de la clase objetivo donde esten almacenados los datos que deseemos borrar, lo cual podemos hacer de la siguiente manera.
```java
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
```

## Interfaz
Para empezar a crear una interfaz 
![[Pasted image 20230212180930.png]]

Utilizamos este código para activar nuestra interfaz.
```java
clientesView() {  
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    this.setSize(1152, 648);  
    this.setResizable(false);  
    this.setTitle("Añadir Cliente");  
    this.setContentPane(panel1);  
    this.setVisible(true);
}
```

Una vez creado nuestra interfaz podemos aplicar eventos a los botones, donde utilizaremos las funciones creadas anteriormente

```java
Clientes cliente = new Clientes();
newClientButton.addActionListener(e -> {  
    if (newClientName.getText().isEmpty() || newClientedni.getText().isEmpty()) {  
        JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos");  
    } else {  
        if (cliente.newCliente(newClientName.getText(), newClientedni.getText())) {  
            JOptionPane.showMessageDialog(null, "Cliente añadido correctamente");  
            newClientName.setText("");  
        } else {  
            JOptionPane.showMessageDialog(null, "Cliente ya existente");  
        }  
    }  
});
```

Añadir elementos a una tabla, utilizamos la lista generada en la función previa
```java
public void cargarClientes(){  
    Clientes cliente = new Clientes();  
    List<Clientes> clientes = cliente.getClientes();  
    DefaultTableModel model = new DefaultTableModel();  
    model.addColumn("ID");  
    model.addColumn("Nombre");  
    model.addColumn("DNI");  
    for (Clientes c : clientes){  
        model.addRow(new Object[]{c.getIdCliente(), c.getNombre(), c.getDni()});  
    }  
    booksTable.setModel(model);  
}
```

## Imagenes de la interfaz
### Menu
![[Pasted image 20230212181510.png]]
### Libros
##### Añadir Libro
![[Pasted image 20230212181521.png]]

#### Buscar Libro
![[Pasted image 20230212181556.png]]

#### Visulizar tabla
![[Pasted image 20230212181615.png]]
