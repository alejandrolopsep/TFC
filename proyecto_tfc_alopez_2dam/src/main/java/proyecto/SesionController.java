package proyecto;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

public class SesionController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private static final String USER = "admin";
    // La contraseña almacenada es el hash de "1234"
    private static final String HASHED_PASSWORD = BCrypt.hashpw("1234", BCrypt.gensalt(12));

    @FXML
    protected void onLoginButtonClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (USER.equals(username) && BCrypt.checkpw(password, HASHED_PASSWORD)) {
            System.out.println("Inicio de sesión exitoso");
            // Aquí podrías abrir la ventana principal de la aplicación
        } else {
            System.out.println("Credenciales incorrectas");
            // Mostrar un mensaje de error o restablecer los campos
        }
    }
}

