package proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    public static boolean validarUsuario(String usuario, String contrasenaPlana) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet resultados = null;

        try {
            conexion = ConexionBaseDeDatos.obtenerConexion();
            String sql = "SELECT contrasena FROM usuarios WHERE usuario = ?";
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, usuario);

            resultados = sentencia.executeQuery();

            if (resultados.next()) {
                String contrasenaEncriptada = resultados.getString("contrasena");
                return BCrypt.checkpw(contrasenaPlana, contrasenaEncriptada);
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Cierra los recursos de la base de datos
            try {
                if (resultados != null) resultados.close();
                if (sentencia != null) sentencia.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

