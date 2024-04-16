package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PromocionesController {

    @FXML
    private void verEliminarDescuento() {
        showAlert("Productos con Descuento", "Ver y eliminar promociones existentes.");
    }

    @FXML
    private void crearModificarDescuento() {
        showAlert("Productos con Descuento", "Crear o modificar promociones.");
    }

    @FXML
    private void verEliminarRegalo() {
        showAlert("Productos más Regalo", "Ver y eliminar promociones existentes.");
    }

    @FXML
    private void crearModificarRegalo() {
        showAlert("Productos más Regalo", "Crear o modificar promociones.");
    }

    @FXML
    private void verEliminarVales() {
        showAlert("Vales Descuento", "Ver y eliminar promociones existentes.");
    }

    @FXML
    private void crearModificarVales() {
        showAlert("Vales Descuento", "Crear o modificar promociones.");
    }

    @FXML
    private void configurarPrecios() {
        showAlert("Gestión de Tarifas", "Administrar varios precios.");
    }

    @FXML
    private void verEliminar2x1() {
        showAlert("Promociones 2x1", "Ver y eliminar promociones existentes.");
    }

    @FXML
    private void crearModificar2x1() {
        showAlert("Promociones 2x1", "Crear o modificar promociones.");
    }

    @FXML
    private void verEliminar3x2() {
        showAlert("Promoción 3x2", "Ver y eliminar promociones existentes.");
    }

    @FXML
    private void crearModificar3x2() {
        showAlert("Promoción 3x2", "Crear o modificar promociones.");
    }

    @FXML
    private void salir() {
        System.exit(0);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

