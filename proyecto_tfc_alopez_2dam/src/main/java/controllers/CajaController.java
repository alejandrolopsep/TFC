package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;

import entidades.Producto;
import entidades.ProductoDAO;

public class CajaController {

	@FXML
	private ListView<String> listaCategorias;
	@FXML
	private FlowPane panelProductos;
	@FXML
	private Label labelProductos;
	@FXML
	private TableView<Producto> tablaCesta;
	@FXML
	private TableColumn<Producto, String> columnaNombre;
	@FXML
	private TableColumn<Producto, Integer> columnaCantidad;
	@FXML
	private TableColumn<Producto, Double> columnaPrecio;

	private List<Producto> cesta = new ArrayList<>();

	private ProductoDAO productoDAO = new ProductoDAO();

	public void initialize() {
		columnaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		columnaCantidad.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty().asObject());
		columnaPrecio.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());

		cargarCategorias();
		listaCategorias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			cargarProductosPorCategoria(newValue); // Método para cargar productos
		});
	}

	private void cargarCategorias() {
		ProductoDAO productoDAO = new ProductoDAO();
		List<String> categorias = productoDAO.obtenerCategoriasUnicas();
		listaCategorias.getItems().addAll(categorias);
	}

	public void mostrarProductosPorCategoria() {
		String categoriaSeleccionada = listaCategorias.getSelectionModel().getSelectedItem();
		if (categoriaSeleccionada != null) {
			labelProductos.setText("Productos de: " + categoriaSeleccionada);
			cargarProductosPorCategoria(categoriaSeleccionada);
		}
	}

	private void cargarProductos(String categoria) {
		// Aquí se debe implementar la lógica para cargar los productos de la categoría
		// seleccionada
		// Esto es solo un ejemplo
		panelProductos.getChildren().clear();
		for (int i = 1; i <= 5; i++) {
			Button btnProducto = new Button(categoria + " Producto " + i);
			panelProductos.getChildren().add(btnProducto);
		}
	}

	private void cargarProductosPorCategoria(String categoria) {
		List<Producto> productos = productoDAO.obtenerProductosPorCategoria(categoria);
		panelProductos.getChildren().clear(); // Limpia los productos anteriores

		for (Producto producto : productos) {
			Button btnAñadirACesta = new Button("Añadir a la cesta");
			btnAñadirACesta.setOnAction(event -> añadirProductoACesta(producto));
			Button btnProducto = new Button(producto.getNombre());
			btnProducto.setMinWidth(150);
			// Actualiza el label al seleccionar un producto
			btnProducto.setOnAction(event -> {
				labelProductos.setText("Producto seleccionado: " + producto.getNombre());
			});

			panelProductos.getChildren().add(btnProducto); // Añade el botón al panel
		}
	}
	
	private void añadirProductoACesta(Producto producto) {
        // Añade el producto a la lista cesta
        cesta.add(producto);
        // Actualiza la tablaCesta
        tablaCesta.getItems().setAll(cesta);
    }
}
