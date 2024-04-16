package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConfiguracionController {

    @FXML
    private TextField txtNombre, txtDireccion, txtPoblacion, txtProvincia, txtCP, txtCifNif, txtTelefono, txtEmail;
    @FXML
    private ImageView imgLogo;

    private String url = "jdbc:mysql://localhost:33006/TFC?useSSL=false&serverTimezone=UTC";
    private String usuario = "root";
    private String contrasena = "1234";

    @FXML
    public void initialize() {
        // Initialization logic here
    }

    @FXML
    private void handleLoadLogo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen del Logo");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imgLogo.setImage(image);
        }
    }

    @FXML
    private void guardarDatos() {
        String sql = "REPLACE INTO EmpresaDatos (id, nombre, direccion, poblacion, provincia, codigo_postal, cif_nif, telefono, email, logo_path) VALUES (1, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, txtNombre.getText());
            pstmt.setString(2, txtDireccion.getText());
            pstmt.setString(3, txtPoblacion.getText());
            pstmt.setString(4, txtProvincia.getText());
            pstmt.setString(5, txtCP.getText());
            pstmt.setString(6, txtCifNif.getText());
            pstmt.setString(7, txtTelefono.getText());
            pstmt.setString(8, txtEmail.getText());
            if (imgLogo.getImage() != null) {
                pstmt.setString(9, imgLogo.getImage().getUrl()); // Assuming you store the URL
            } else {
                pstmt.setNull(9, java.sql.Types.VARCHAR);
            }
            pstmt.executeUpdate();
            System.out.println("Datos guardados correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al guardar los datos: " + ex.getMessage());
        }
    }
    
    
    
    

   

  
    
}

