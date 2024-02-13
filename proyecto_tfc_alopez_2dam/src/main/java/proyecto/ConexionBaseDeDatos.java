package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDeDatos {
    private static final String URL = "jdbc:mysql://127.0.0.1:33006/TFC";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "1234";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}

