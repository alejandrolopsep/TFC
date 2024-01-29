package proyecto;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class LoginController {

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoContrasena;

    @FXML
    protected void enBotonIniciarSesionClic() {
        String usuario = campoUsuario.getText();
        String contrasena = campoContrasena.getText();

        if (validarCredenciales(usuario, contrasena)) {
            System.out.println("Inicio de sesión exitoso");
            // Aquí puedes cambiar a la vista principal de tu aplicación
        } else {
            System.out.println("Credenciales incorrectas");
            // Aquí puedes mostrar un mensaje de error
        }
    }

    private boolean validarCredenciales(String usuario, String contrasenaPlana) {
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
