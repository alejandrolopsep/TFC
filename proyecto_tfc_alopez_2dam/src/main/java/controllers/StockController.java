package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StockController {
	
	@FXML
    private Button btnRegresar;
	
	
    @FXML
    private void handleEntradaStock() {
        mostrarMensaje("Entrada de Stock", "Funcionalidad no implementada.");
    }

    @FXML
    private void handleRegularizacionStock() {
        mostrarMensaje("Regularización de Stock", "Funcionalidad no implementada.");
    }

    @FXML
    private void handleDevolucionProveedor() {
        mostrarMensaje("Devolución a Proveedor", "Funcionalidad no implementada.");
    }

    @FXML
    private void handlePedidosProveedor() {
        mostrarMensaje("Pedidos a Proveedor", "Funcionalidad no implementada.");
    }

    @FXML
    private void handleListadoProductosBajoMinimo() {
        mostrarMensaje("Listado de Productos Bajo Mínimo", "Funcionalidad no implementada.");
    }

    @FXML
    private void handleMovimiento() {
        mostrarMensaje("Movimiento", "Funcionalidad no implementada.");
    }

   

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

 

    @FXML
    private void onRegresarClicked() {
        
		// Obtiene el Stage actual y lo cierra
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        stage.close();
    }
}

