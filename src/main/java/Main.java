import views.DashBoard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        connect();
        DashBoard.main();
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
