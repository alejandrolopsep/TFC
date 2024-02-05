package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyecto.UsuarioServicio;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SesionController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    
    @FXML
    protected void onLoginButtonClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (UsuarioServicio.validarUsuario(username, password)) {
            System.out.println("Inicio de sesión exitoso");
            
            // Cambiamos la ruta a la correcta según la jerarquía del proyecto
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Inicio.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) usernameField.getScene().getWindow(); // Obtiene el stage actual
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
}


