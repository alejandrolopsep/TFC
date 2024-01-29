package proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class UsuarioServicio {

    public static void insertarUsuario(String usuario, String contrasenaPlana) {
        Connection conexion = null;
        PreparedStatement sentencia = null;

        try {
            conexion = ConexionBaseDeDatos.obtenerConexion();
            String sql = "INSERT INTO usuarios (usuario, contrasena) VALUES (?, ?)";
            sentencia = conexion.prepareStatement(sql);

            // Encriptamos la contraseña antes de almacenarla
            String contrasenaEncriptada = BCrypt.hashpw(contrasenaPlana, BCrypt.gensalt());

            sentencia.setString(1, usuario);
            sentencia.setString(2, contrasenaEncriptada);

            sentencia.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cierra los recursos de la base de datos
            try {
                if (sentencia != null) sentencia.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
