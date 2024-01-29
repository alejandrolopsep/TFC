package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDeDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/TFC";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}

