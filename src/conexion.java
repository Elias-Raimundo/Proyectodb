import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {

    public static Connection conectar() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/Basededatos";
        String usuario = "postgres";
        String contraseña = "Raimundo123456";
        return DriverManager.getConnection(url, usuario, contraseña);
    }
}

