package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entidades.Producto;
import entidades.ProductoDAO;
import java.util.List;
import java.util.stream.Collectors;

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
    private VBox areaEliminarProducto;

    private ObservableList<Producto> productos = FXCollections.observableArrayList();
    private ProductoDAO productoDAO = new ProductoDAO();

    @FXML
    private void initialize() {
        
        cargarProductos();
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

            productoDAO.añadirProducto(new Producto(nombre, precio, peso, categoria));
            mostrarAlerta("Éxito", "Producto añadido correctamente.");
            formularioProducto.setVisible(false);
            limpiarFormulario();
            cargarProductos(); // Recargar la lista de productos después de añadir uno nuevo
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
    private void mostrarEliminarProducto() {
        areaEliminarProducto.setVisible(true);
        actualizarListaEliminarProducto();
    }

    @FXML
    private void handleEliminarSeleccionados() {
        List<Producto> seleccionados = productos.stream().filter(Producto::isSelected).collect(Collectors.toList());
        seleccionados.forEach(producto -> productoDAO.eliminarProducto(producto.getId()));
        productos.removeAll(seleccionados);
        mostrarAlerta("Éxito", "Productos eliminados correctamente.");
        areaEliminarProducto.setVisible(false);
        cargarProductos(); // Recargar la lista de productos después de eliminar
    }

    private void actualizarListaEliminarProducto() {
        areaEliminarProducto.getChildren().clear();
        productos.forEach(producto -> {
            CheckBox cb = new CheckBox(producto.toString());
            cb.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> producto.setSelected(isNowSelected));
            areaEliminarProducto.getChildren().add(cb);
        });
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

    private void cargarProductos() {
        productos.clear();
        productos.addAll(productoDAO.obtenerProductos());
    }
}

