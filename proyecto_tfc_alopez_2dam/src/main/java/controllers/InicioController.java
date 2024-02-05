package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class InicioController {

	@FXML
	private AnchorPane contentArea;

	// Botones
	@FXML
	private Button btnCaja;
	@FXML
	private Button btnListados;
	@FXML
	private Button btnStock;
	@FXML
	private Button btnPromociones;
	@FXML
	private Button btnClientes;
	@FXML
	private Button btnProductos;
	@FXML
	private Button btnEmail;
	@FXML
	private Button btnConfiguracion;

	// Métodos para manejar eventos de clic de botones

	private void abrirNuevaVentana(String fxml, String titulo) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("/" + fxml));
		Stage stage = new Stage();
		stage.setTitle(titulo);
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	private void onCajaClicked() throws IOException {
		abrirNuevaVentana("Caja.fxml", "Caja");
	}

	@FXML
	private void onListadosClicked() throws IOException {
		abrirNuevaVentana("Listados.fxml", "Listado");
		
	}

	@FXML
	private void onStockClicked() throws IOException {
		abrirNuevaVentana("Stock.fxml", "Stock");
		
	}

	@FXML
	private void onPromocionesClicked() throws IOException {
		abrirNuevaVentana("Promociones.fxml", "Promociones");
	}

	@FXML
	private void onClientesClicked() throws IOException {
		abrirNuevaVentana("Clientes.fxml", "Clientes");
	}

	@FXML
	private void onProductosClicked() throws IOException {
		abrirNuevaVentana("Productos.fxml", "Productos");
	}

	@FXML
	private void onEmailClicked() throws IOException {
		abrirNuevaVentana("EnvioEmail.fxml", "Productos");
	}

	@FXML
	private void onConfiguracionClicked() throws IOException {
		abrirNuevaVentana("Configuracion.fxml", "Configuracion");
	}

	// Método auxiliar para cargar las vistas en el contentArea
	private void cargarVista(String fxml) {
		contentArea.getChildren().clear(); // Limpia el contenido actual
		try {
			Node nodo = FXMLLoader.load(getClass().getResource("/" + fxml));
			contentArea.getChildren().add(nodo); // Añade el nuevo contenido
			AnchorPane.setTopAnchor(nodo, 0.0);
			AnchorPane.setRightAnchor(nodo, 0.0);
			AnchorPane.setBottomAnchor(nodo, 0.0);
			AnchorPane.setLeftAnchor(nodo, 0.0);
		} catch (IOException e) {
			mostrarAlerta("Error al cargar la vista", "No se pudo cargar la vista: " + fxml);
		}
	}

	// Método para mostrar una alerta al usuario
	private void mostrarAlerta(String titulo, String contenido) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

	// Método para inicialización si es necesario
	@FXML
	public void initialize() {
		// Cualquier inicialización que necesites al cargar la pantalla
	}
}
