package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import entidades.Empresa;
import entidades.Producto;
import entidades.ProductoDAO;
import entidades.ProovedorDAO;
import entidades.Proveedor;
import entidades.Seccion;
import entidades.SeccionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CajaController {

	@FXML
	private Label nombreEmpleadoLabel, labelHoraActual, labelNombreCliente, labelProductosDe;
	@FXML
	private DatePicker datePickerFecha;
	@FXML
	private VBox vboxContenedor;
	@FXML
	private ToggleButton toggleSecciones, toggleProveedores;
	@FXML
	private Button btnAnterior, btnSiguiente;
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private HBox hboxContenedor;

	private static String nombreEmpleado;
	private SeccionDAO seccionDAO = new SeccionDAO();
	private ProovedorDAO proveedorDAO = new ProovedorDAO();
	private ProductoDAO productoDAO = new ProductoDAO();

	private int paginaActual = 0;
	private static final int MAX_ITEMS = 5;
	private boolean mostrandoSecciones = true;

	@FXML
	private ListView<String> listaOpciones;
	@FXML
	private FlowPane paneContenedor;
	@FXML
	private VBox vboxSecciones;

	@FXML
	private VBox vboxProveedores;

	private List<Button> botonesSecciones;

	private List<Button> botonesProveedores;

	private List<Button> todosLosBotonesSecciones = new ArrayList<>();
	private List<Button> todosLosBotonesProveedores = new ArrayList<>();
	private static final int BOTONES_POR_PAGINA = 5;
	private static final int PRODUCTOS_POR_FILA = 5;

	private Seccion seccionSeleccionada;
	private Proveedor proveedorSeleccionado;
	@FXML
	private FlowPane paneProductos;
	@FXML
	private TableColumn<Producto, String> columnaNombreProducto;

	@FXML
	private HBox hboxProductos;

	private ObservableList<Producto> productos = FXCollections.observableArrayList();

	@FXML
	private VBox vboxContenedorProductos;

	private static final int PRODUCTOS_POR_PAGINA = 20;

	private List<Producto> todosLosProductos = new ArrayList<>();

	@FXML
	private TableView<Producto> tablaDetallesVenta;

	@FXML
	private TableColumn<Producto, String> columnaNombre;
	@FXML
	private TableColumn<Producto, Double> columnaPrecio;
	@FXML
	private TableColumn<Producto, Double> columnaImpuesto;
	@FXML
	private TableColumn<Producto, Integer> columnaCantidad;
	@FXML
	private TableColumn<Producto, Double> columnaTotal;
	@FXML
	private TableColumn<Producto, String> columnaCodigoBarras;

	private ObservableList<Producto> datosTabla = FXCollections.observableArrayList();

	@FXML
	private TextField totalTextField;
	
	@FXML
	private TextField codigoBarrasTextField;
	
	private String url = "jdbc:mysql://localhost:33006/TFC?useSSL=false&serverTimezone=UTC";
    private String user = "root";
    private String password = "1234";

    
    private Connection conectar() {
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	
	public void initialize() {

		columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columnaImpuesto.setCellValueFactory(new PropertyValueFactory<>("impuesto"));
		columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
		columnaTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		columnaCodigoBarras.setCellValueFactory(new PropertyValueFactory<>("codigoBarras"));
		tablaDetallesVenta.setItems(datosTabla);

		datePickerFecha.setValue(LocalDate.now());
		actualizarHora();

		if (nombreEmpleadoLabel != null && nombreEmpleado != null) {
			nombreEmpleadoLabel.setText(nombreEmpleado);
		}

		ToggleGroup toggleGroup = new ToggleGroup();
		toggleSecciones.setToggleGroup(toggleGroup);
		toggleProveedores.setToggleGroup(toggleGroup);

		cargarBotonesSecciones();
		cargarBotonesProveedores();

		mostrarSecciones();
	}
	
	@FXML
	private void handleQuitarProducto() {
	    Producto productoSeleccionado = tablaDetallesVenta.getSelectionModel().getSelectedItem();
	    if (productoSeleccionado != null) {
	        tablaDetallesVenta.getItems().remove(productoSeleccionado);
	    } else {
	        // Opcional: Mostrar un mensaje indicando que el usuario debe seleccionar un producto.
	        System.out.println("Por favor, selecciona un producto para quitar.");
	    }
	}
	
	
	private void cargarBotonesSecciones() {
		todosLosBotonesSecciones.clear();
		List<Seccion> secciones = seccionDAO.obtenerSecciones();
		for (Seccion seccion : secciones) {
			Button btn = new Button(seccion.getNombre());
			btn.setStyle("-fx-background-color: " + seccion.getColor() + "; -fx-text-fill: white;");
			btn.setOnAction(event -> {
				labelProductosDe.setText("Productos de: Sección " + seccion.getNombre()); // Actualiza el label
				List<Producto> productos = productoDAO.obtenerProductosPorSeccion(seccion);
				mostrarProductosEnPane(productos); // Suponiendo que tienes este método para mostrar
																		// productos
			});
			todosLosBotonesSecciones.add(btn);
		}
	}

	private void cargarBotonesProveedores() {
		todosLosBotonesProveedores.clear();
		List<Proveedor> proveedores = proveedorDAO.obtenerProveedores();
		for (Proveedor proveedor : proveedores) {
			Button btn = new Button(proveedor.getNombre());
			btn.setStyle("-fx-background-color: " + proveedor.getColor() + "; -fx-text-fill: white;");
			btn.setOnAction(event -> {
				labelProductosDe.setText("Productos de: Proveedor " + proveedor.getNombre()); // Actualiza el label
				List<Producto> productos = productoDAO.obtenerProductosPorProveedor(proveedor);
				mostrarProductosEnPane(productos); // Muestra los productos filtrados
			});
			todosLosBotonesProveedores.add(btn);
		}
	}
	
	
	private void mostrarProductosEnPane(List<Producto> productos) {
	    hboxProductos.getChildren().clear();

	    for (Producto producto : productos) {
	        Button btnProducto = new Button(producto.getNombre());
	        btnProducto.setStyle("-fx-background-color: " + producto.getColor() + "; -fx-text-fill: black;");

	        btnProducto.setOnAction(event -> {
	            agregarProductoATabla(new Producto(
	                producto.getNombre(),
	                producto.getPrecio(),
	                producto.getImpuesto(),
	                1, // Suponiendo que la cantidad inicial es 1
	                producto.getPrecio() * (1 + producto.getImpuesto()), // Total inicial
	                producto.getCodigoBarras()
	            ));
	        });

	        hboxProductos.getChildren().add(btnProducto);
	    }
	}

	public void agregarProductoATabla(Producto productoNuevo) {
	    boolean productoExistente = false;
	    
	    for (Producto producto : datosTabla) {
	        if (producto.getCodigoBarras().equals(productoNuevo.getCodigoBarras())) {
	            productoExistente = true;
	            // Incrementa la cantidad del producto existente
	            int nuevaCantidad = producto.getCantidad() + 1;
	            producto.setCantidad(nuevaCantidad);
	            // Calcula el total con impuesto incluido
	            double precioConImpuesto = producto.getPrecio() * (1 + producto.getImpuesto() / 100);
	            producto.setTotal(precioConImpuesto * nuevaCantidad);
	            break;
	        }
	    }

	    if (!productoExistente) {
	        // Para un producto nuevo, calcula el total inicial considerando el impuesto
	        double precioConImpuesto = productoNuevo.getPrecio() * (1 + productoNuevo.getImpuesto() / 100);
	        productoNuevo.setTotal(precioConImpuesto); // La cantidad inicial es 1, por lo que el total es simplemente el precio con impuesto
	        datosTabla.add(productoNuevo);
	    }
	    
	    tablaDetallesVenta.refresh(); // Actualiza la tabla para reflejar los cambios
	    actualizarTotal(); // Llama al método para actualizar el total general
	}

	// Método para recalcular el total cada vez que se añade o modifica un producto
	private void actualizarTotal() {
	    double total = 0;
	    for (Producto producto : datosTabla) {
	        total += producto.getTotal(); // Asume que getTotal() devuelve el total por producto
	    }
	    totalTextField.setText(String.format("%.2f", total)); // Formatea el total a dos decimales
	}
	
	
	
	@FXML
	private void handleAnadirProductoPorCodigo() {
	    String codigoBarras = codigoBarrasTextField.getText().trim();
	    if (!codigoBarras.isEmpty()) {
	        // Busca el producto por el código de barras
	        Producto producto = buscarProductoPorCodigoBarras(codigoBarras);
	        if (producto != null) {
	            // Añade el producto a la tabla
	            agregarProductoATabla(producto);
	            codigoBarrasTextField.clear(); // Limpia el campo de texto
	        } else {
	            // Mostrar algún mensaje de que el producto no se encontró
	            System.out.println("Producto no encontrado con el código de barras: " + codigoBarras);
	        }
	    }
	}
	

	public Producto buscarProductoPorCodigoBarras(String codigoBarras) {
	    Producto productoEncontrado = null;
	    String sql = "SELECT * FROM productos WHERE codigoBarras = ?";
	    try (Connection conn = this.conectar();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setString(1, codigoBarras);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            productoEncontrado = new Producto();
	            productoEncontrado.setCodigo(rs.getInt("codigo"));
	            productoEncontrado.setCodigoBarras(rs.getString("codigoBarras"));
	            productoEncontrado.setNombre(rs.getString("nombre"));
	            productoEncontrado.setPrecio(rs.getDouble("precio"));
	            productoEncontrado.setImpuesto(rs.getDouble("impuesto"));
	            productoEncontrado.setCoste(rs.getDouble("coste"));
	            productoEncontrado.setColor(rs.getString("color"));
	            productoEncontrado.setCantidad(1); // Establece la cantidad inicial como 1
	            // Calcula el total inicial teniendo en cuenta el impuesto
	            productoEncontrado.setTotal(productoEncontrado.getPrecio() * (1 + productoEncontrado.getImpuesto() / 100));
	            System.out.println("Producto encontrado: " + productoEncontrado.getNombre());
	        } else {
	            System.out.println("Producto no encontrado con el código de barras: " + codigoBarras);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return productoEncontrado;
	}
	
	private List<Producto> crearBotonesProducto(List<Producto> productos) {
		hboxProductos.getChildren().clear();

		for (Producto producto : productos) {
			Button btnProducto = new Button(producto.getNombre());

			btnProducto.setOnAction(event -> seleccionarProducto(producto));
			hboxProductos.getChildren().add(btnProducto);
		}
		return productos;
	}
	
	


	public void seleccionarProducto(Producto producto) {
		boolean encontrado = false;
		for (Producto prod : datosTabla) {
			if (prod.getCodigoBarras().equals(producto.getCodigoBarras())) {
				int nuevaCantidad = prod.getCantidad() + 1;
				prod.setCantidad(nuevaCantidad);
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {

			Producto nuevoProd = new Producto(producto.getNombre(), producto.getPrecio(), producto.getImpuesto(), 1,
					producto.getCodigoBarras());
			datosTabla.add(nuevoProd);
		}
		tablaDetallesVenta.refresh();
	}

	public void seleccionarProducto(String nombre, double precio, double impuesto, String codigoBarras) {
		boolean productoEncontrado = false;
		
		for (Producto producto : datosTabla) {
			if (producto.getCodigoBarras().equals(codigoBarras)) {
				producto.setCantidad(producto.getCantidad() + 1);
				productoEncontrado = true;
				break;
			}
		}
		if (!productoEncontrado) {
			datosTabla.add(new Producto(nombre, precio, impuesto, 1, codigoBarras));
		}
		tablaDetallesVenta.refresh();
	}

	private void mostrarProductos() {
		List<Producto> productos = obtenerProductosPagina(paginaActual); // Implementa este método
		vboxContenedorProductos.getChildren().clear(); // Limpia el contenedor antes de añadir nuevos productos

		VBox filaActual = new VBox(10);
		int contador = 0;
		for (Producto producto : productos) {
			if (contador % PRODUCTOS_POR_FILA == 0) {
				filaActual = new VBox(5);
				vboxContenedorProductos.getChildren().add(filaActual);
			}
			Button btnProducto = new Button(producto.getNombre());
			btnProducto.setStyle("-fx-background-color: " + producto.getColor() + "; -fx-text-fill: white;");

			filaActual.getChildren().add(btnProducto);
			contador++;
		}
	}

	private List<Producto> obtenerProductosPagina(int pagina) {

		int start = pagina * PRODUCTOS_POR_PAGINA;
		int end = Math.min(start + PRODUCTOS_POR_PAGINA, todosLosProductos.size());
		return todosLosProductos.subList(start, end);
	}

	

	private void actualizarPaneProductos(List<Producto> productos) {
		paneProductos.getChildren().clear();

		for (Producto producto : productos) {
			Button botonProducto = new Button(producto.getNombre());
			botonProducto.setStyle("-fx-background-color: " + producto.getColor() + "; -fx-text-fill: white;");
			paneProductos.getChildren().add(botonProducto);
		}
	}

	private void mostrarDetallesProducto(Producto producto) {
		System.out.println("Producto seleccionado: " + producto.getNombre());
	}

	private List<Producto> obtenerProductos() {
		List<Producto> productos = new ArrayList<>();

		String url = "jdbc:mysql://localhost:33006/TFC?useSSL=false&serverTimezone=UTC";
		String usuario = "root";
		String contrasena = "1234";

		String consulta = "SELECT nombre, color FROM productos"; // Asegúrate de que los campos coincidan con tu esquema

		try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
				PreparedStatement sentencia = conexion.prepareStatement(consulta);
				ResultSet resultado = sentencia.executeQuery()) {

			while (resultado.next()) {
				String nombre = resultado.getString("nombre");
				String color = resultado.getString("color");
				productos.add(new Producto(nombre, color));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productos;
	}

	private VBox crearVistaProducto(Producto producto) {
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(5));
		box.setSpacing(5);

		Label nombreProducto = new Label(producto.getNombre());
		nombreProducto.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

		box.setStyle("-fx-background-color: " + producto.getColor() + "; -fx-border-color: black;");
		box.getChildren().add(nombreProducto);

		box.setOnMouseClicked(event -> {

		});

		return box;
	}

	private void actualizarSeleccionSeccion(Seccion seccion) {
		seccionSeleccionada = seccion;
		proveedorSeleccionado = null; // Resetea la selección del proveedor

	}

	private void actualizarSeleccionProveedor(Proveedor proveedor) {
		proveedorSeleccionado = proveedor;
		seccionSeleccionada = null;

	}

	public Seccion obtenerSeccionSeleccionada() {
		return seccionSeleccionada;
	}

	public Proveedor obtenerProveedorSeleccionado() {
		return proveedorSeleccionado;
	}

	private void mostrarSeccionesInicialmente() {
		mostrandoSecciones = true;
		paginaActual = 0;
		actualizarVistaBotones();
	}

	@FXML
	private void mostrarSecciones() {
		mostrandoSecciones = true;
		paginaActual = 0;
		actualizarVistaBotones();
		cargarProductosPorTipo(true);
	}

	@FXML
	private void mostrarProveedores() {
		mostrandoSecciones = false;
		paginaActual = 0;
		actualizarVistaBotones();
		cargarProductosPorTipo(false);
	}

	private void cargarProductosPorTipo(boolean esSeccion) {

		if (esSeccion) {

			Seccion seccionSeleccionada = obtenerSeccionSeleccionada();
			if (seccionSeleccionada != null) {
				List<Producto> productos = productoDAO.obtenerProductosPorSeccion(seccionSeleccionada);

			}
		} else {

			Proveedor proveedorSeleccionado = obtenerProveedorSeleccionado();
			if (proveedorSeleccionado != null) {
				List<Producto> productos = productoDAO.obtenerProductosPorProveedor(proveedorSeleccionado);

			}
		}
	}

	@FXML
	private void mostrarAnteriores() {
		if (paginaActual > 0) {
			paginaActual--;
			actualizarVistaBotones();
		}
	}

	@FXML
	private void mostrarSiguientes() {

		int totalItems = mostrandoSecciones ? seccionDAO.obtenerSecciones().size()
				: proveedorDAO.obtenerProveedores().size();
		if ((paginaActual + 1) * MAX_ITEMS < totalItems) {
			paginaActual++;
			actualizarVistaBotones();
		}
	}

	private void cargarProductosPorSeccion(Seccion seccion) {

		// Obtiene los productos de la sección específica
		List<Producto> productos = productoDAO.obtenerProductosPorSeccion(seccion);

	}

	private void actualizarVistaBotones() {
		hboxContenedor.getChildren().clear();
		List<Button> botones = mostrandoSecciones ? todosLosBotonesSecciones : todosLosBotonesProveedores;
		int inicio = paginaActual * BOTONES_POR_PAGINA;
		int fin = Math.min(inicio + BOTONES_POR_PAGINA, botones.size());
		for (int i = inicio; i < fin; i++) {
			hboxContenedor.getChildren().add(botones.get(i));
		}
		btnAnterior.setDisable(paginaActual == 0);
		btnSiguiente.setDisable(fin == botones.size());
	}

	

	private void actualizarVistaBotones(int pagina, boolean esSeccion) {
		hboxContenedor.getChildren().clear();
		int inicio = pagina * BOTONES_POR_PAGINA;
		int fin = Math.min(inicio + BOTONES_POR_PAGINA,
				(esSeccion ? todosLosBotonesSecciones.size() : todosLosBotonesProveedores.size()));
		List<Button> botones = esSeccion ? todosLosBotonesSecciones.subList(inicio, fin)
				: todosLosBotonesProveedores.subList(inicio, fin);
		hboxContenedor.getChildren().addAll(botones);
	}

	private void cargarBotones() {
		botonesSecciones = new ArrayList<>();
		botonesProveedores = new ArrayList<>();

		List<Seccion> secciones = seccionDAO.obtenerSecciones();
		for (Seccion seccion : secciones) {
			Button btnSeccion = new Button(seccion.getNombre());
			btnSeccion.setStyle("-fx-background-color: " + seccion.getColor() + "; -fx-text-fill: white;");
			botonesSecciones.add(btnSeccion);
		}

		List<Proveedor> proveedores = proveedorDAO.obtenerProveedores();
		for (Proveedor proveedor : proveedores) {
			Button btnProveedor = new Button(proveedor.getNombre());
			btnProveedor.setStyle("-fx-background-color: " + proveedor.getColor() + "; -fx-text-fill: white;");
			botonesProveedores.add(btnProveedor);
		}
	}

	// Método para mostrar los botones de la página actual
	private void mostrarPaginaActual() {
		hboxContenedor.getChildren().clear(); // Limpia los botones anteriores
		List<Button> botonesActuales = mostrandoSecciones ? botonesSecciones : botonesProveedores;

		int start = paginaActual * MAX_ITEMS;
		int end = Math.min(start + MAX_ITEMS, botonesActuales.size());

		for (int i = start; i < end; i++) {
			hboxContenedor.getChildren().add(botonesActuales.get(i));
		}

		// Actualizar el estado de los botones de navegación
		btnAnterior.setDisable(paginaActual == 0);
		btnSiguiente.setDisable(end == botonesActuales.size());
	}

	private void actualizarHora() {
		Timeline reloj = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			labelHoraActual.setText(LocalDateTime.now().format(formatter));
		}), new KeyFrame(Duration.seconds(1)));
		reloj.setCycleCount(Timeline.INDEFINITE);
		reloj.play();
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		nombreEmpleadoLabel.setText(nombreEmpleado);
	}

	@FXML
	private void abrirCaja() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/Caja.fxml"));
			Parent root = loader.load();

			CajaController controller = loader.getController();
			Stage stage = new Stage();
			stage.setTitle("Caja");
			stage.setScene(new Scene(root));
			stage.setMaximized(true);

			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleCambiarEmpleado() {
	    try {
	        // Cerrar la ventana actual
	        Stage stageActual = (Stage) tablaDetallesVenta.getScene().getWindow();
	        stageActual.close();
	        
	        // Cargar y mostrar la nueva ventana de SeleccionEmpleados
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/SeleccionEmpleados.fxml")); // Asegúrate de que la ruta sea correcta
	        Parent root = loader.load();
	        
	        Stage nuevoStage = new Stage();
	        nuevoStage.setTitle("Seleccionar Empleado");
	        nuevoStage.setScene(new Scene(root));
	        nuevoStage.setMaximized(true);
	        nuevoStage.show();
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@FXML
	private void handleNuevaVenta() {
	    // Limpiar los datos de la tabla
	    datosTabla.clear();
	    
	   
	}

	@FXML
	private void handleVentasActivas() {
		System.out.println("Mostrando ventas activas.");

	}

	@FXML
	private void handleVentasCodigoBarras() {
		System.out.println("Modo de ventas por código de barras activado.");

	}

	private Empresa obtenerDatosEmpresa() {
        String sql = "SELECT * FROM EmpresaDatos WHERE id = 1";  // Asumiendo que siempre habrá un solo registro.
        Empresa empresa = null;

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                empresa = new Empresa(
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("poblacion"),
                    rs.getString("provincia"),
                    rs.getString("codigo_postal"),
                    rs.getString("cif_nif"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("logo_path")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener los datos de la empresa: " + e.getMessage());
        }

        return empresa;
    }
	
	
	@FXML
	private void handleBuscarCliente() {

		System.out.println("Buscar cliente...");
	}
	
	@FXML
	private void handleImprimirTicket() {
	    Empresa empresa = obtenerDatosEmpresa();
	    
	    if (empresa == null) {
	        System.out.println("Datos de empresa no disponibles.");
	        return;
	    }

	    StringBuilder ticket = new StringBuilder();
	    ticket.append("---------- TICKET DE VENTA ----------\n");
	    ticket.append("Fecha: ").append(datePickerFecha.getValue().toString()).append(" Hora: ").append(labelHoraActual.getText()).append("\n");
	    ticket.append("Empleado: ").append(nombreEmpleadoLabel.getText()).append("\n\n");
	    
	    ticket.append(empresa.getNombre()).append("\n");
	    ticket.append(empresa.getDireccionCompleta()).append("\n");
	    ticket.append(empresa.getTelefono()).append(" - ").append(empresa.getEmail()).append("\n");
	    ticket.append("-------------------------------------\n");
	    ticket.append(String.format("%-15s %-10s %-10s\n", "Producto", "Cantidad", "Total"));
	    
	    for (Producto producto : datosTabla) {
	        ticket.append(String.format("%-15s %-10d %-10.2f\n", producto.getNombre(), producto.getCantidad(), producto.getTotal()));
	    }

	    ticket.append("-------------------------------------\n");
	    ticket.append("Total a pagar: ").append(totalTextField.getText()).append(" euros\n");
	    ticket.append("-------------------------------------\n");
	    ticket.append("Gracias por su compra!\n");

	    System.out.println(ticket.toString());
	    // Aquí añadirías la lógica para imprimir el ticket físicamente si es necesario
	}


}
