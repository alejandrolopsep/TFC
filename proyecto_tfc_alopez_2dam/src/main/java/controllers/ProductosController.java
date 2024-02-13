package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import entidades.Producto;
import entidades.ProductoDAO;

import java.util.List;

public class ProductosController {

    @FXML
    private Button btnRegresar;

    @FXML
    private VBox formularioProducto;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtPeso;
    @FXML
    private TextField txtCategoria;

    @FXML
    private ListView<Producto> listaProductos;

    private ProductoDAO productoDAO = new ProductoDAO();

    @FXML
    private void initialize() {
        
        // mostrarProductos();
    }

    @FXML
    private void onRegresarClicked() {
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void mostrarFormularioAñadir() {
        formularioProducto.setVisible(true);
    }

    @FXML
    private void handleAñadir() {
        try {
            String nombre = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            String categoria = txtCategoria.getText();

            Producto producto = new Producto(nombre, precio, peso, categoria);
            productoDAO.añadirProducto(producto);
            
            mostrarAlerta("Éxito", "Producto añadido correctamente.");
            formularioProducto.setVisible(false);
            limpiarFormulario();
            mostrarProductos(); // Actualiza la lista de productos
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Precio y peso deben ser números válidos.");
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo añadir el producto: " + e.getMessage());
        }
    }

    @FXML
    private void handleCancelar() {
        formularioProducto.setVisible(false);
        limpiarFormulario();
    }

    @FXML
    public void mostrarProductos() {
        List<Producto> productos = productoDAO.obtenerProductos();
        listaProductos.getItems().setAll(productos); // Muestra los productos en el ListView
    }

    @FXML
    public void eliminarProductoSeleccionado() {
        Producto productoSeleccionado = listaProductos.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            productoDAO.eliminarProducto(productoSeleccionado.getId());
            mostrarProductos(); // Actualiza la lista después de eliminar
            mostrarAlerta("Éxito", "Producto eliminado correctamente.");
        } else {
            mostrarAlerta("Selección requerida", "Por favor, selecciona un producto para eliminar.");
        }
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        txtPrecio.clear();
        txtPeso.clear();
        txtCategoria.clear();
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}

