package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class LoginController {
/*
    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoContrasena;

    @FXML
    protected void enBotonIniciarSesionClic() {
        String usuario = campoUsuario.getText();
        String contrasena = campoContrasena.getText();

        if (UsuarioServicio.validarUsuario(usuario, contrasena)) {
            System.out.println("Inicio de sesión exitoso");
            
            // Cambiamos la ruta a la correcta según la jerarquía del proyecto
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Inicio.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) campoUsuario.getScene().getWindow(); // Obtiene el stage actual
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("No se pudo cargar la vista de inicio.");
            }
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
    } */
}
