package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.StyleManager;

public class MantenimientoController {

    private void openWindow(String resource, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.setMaximized(true);
            StyleManager.applyStyles(stage);  // Aplica estilos al stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void gestionarSecciones() {
        openWindow("/Secciones.fxml", "Secciones");
    }

    @FXML
    private void onProveedoresClicked() {
        openWindow("/Proveedores.fxml", "Proveedores");
    }

    @FXML
    private void onEmpleadosClicked() {
        openWindow("/Empleados.fxml", "Empleados");
    }

    @FXML
    private void onProductosClicked() {
        openWindow("/Productos.fxml", "Productos");
    }

    @FXML
    private void onClientesClicked() {
        openWindow("/Clientes.fxml", "Clientes");
    }

    @FXML
    private void onPromocionesClicked() {
        openWindow("/Promociones.fxml", "Promociones");
    }

    @FXML
    private void onConfiguracionClicked() {
        openWindow("/Configuracion.fxml", "Configuración");
    }

    @FXML
    private void mostrarFormasDePago() {
        openWindow("/FormasDePago.fxml", "Formas de Pago");
    }

    @FXML
    private void repararBaseDeDatos() {
        System.out.println("Reparando base de datos...");
    }

    @FXML
    private void limpiarCache() {
        System.out.println("Limpiando caché...");
    }

    @FXML
    private void actualizarSistema() {
        System.out.println("Actualizando sistema...");
    }
}

