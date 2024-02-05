package controllers;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ListadosController {

    @FXML
    private Button btnRegresar; 
    
    @FXML
    private Button btnListados; 

    @FXML
    private void onRegresarClicked() {
        // Obtiene el Stage actual y lo cierra
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        stage.close();
    }
}
