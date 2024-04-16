package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import entidades.Producto;
import entidades.ProductoDAO;
import entidades.ProovedorDAO;
import entidades.Proveedor;
import entidades.Seccion;
import entidades.SeccionDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

public class ProductoController {

    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, String> colCodigoBarras, colNombre, colMostrarEnCaja, colComoSeVende;
    @FXML
    private TableColumn<Producto, Integer> colCodigo, colStock, colStockMinimo, colPosicionPantalla;
    @FXML
    private TableColumn<Producto, Double> colPrecio, colImpuesto, colCoste, colMargenComercial;
    @FXML
    private TableColumn<Producto, Color> colColor;
    
    @FXML
    private TextField lblMargenComercial;
    @FXML
    private TextField txtCodigoBarras, txtNombre, txtPrecio, txtCoste, txtStock, txtStockMinimo;
    @FXML
    private ComboBox<Seccion> cbSecciones;
    @FXML
    private ComboBox<Proveedor> cbProveedores;
    @FXML
    private Spinner<Double> spnImpuesto, spnMargenComercial;
    @FXML
    private DatePicker dpEntrada, dpSalida;
    @FXML
    private CheckBox chkMostrarEnCaja;
    
    private ProductoDAO productoDAO = new ProductoDAO();

    @FXML
    private ColorPicker colorPicker;
    @FXML
    private ToggleGroup ventaGrupo;
    @FXML
    private RadioButton rbUnidades, rbPeso;
    @FXML
    private Spinner<Integer> spnPosicionPantalla;
    
    
    @FXML
    private ComboBox<Seccion> cbFiltroSeccion;
    @FXML
    private ComboBox<Proveedor> cbFiltroProveedor;
    
    @FXML
    private TextField txtMargenComercial;
    
    private SeccionDAO seccionDAO = new SeccionDAO();
    private ProovedorDAO proveedorDAO = new ProovedorDAO();

    private Spinner<Double> spinnerImpuesto;
    
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

    @FXML
    private void initialize() {
    	this.productoDAO = new ProductoDAO();
    	colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
    	cargarSecciones();
        cargarProveedores();
        
        txtPrecio.textProperty().addListener((observable, oldValue, newValue) -> calcularMargenComercial());
        txtCoste.textProperty().addListener((observable, oldValue, newValue) -> calcularMargenComercial());
        configurarColumnasTabla();
        cargarProductos();
        
       
    }
    private void configurarColumnasTabla() {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        
    }
    
    private String obtenerModoVenta() {
        RadioButton seleccionado = (RadioButton) ventaGrupo.getSelectedToggle();
        return seleccionado.getText();
    }
    
    private void cargarProductos() {
        List<Producto> productos = productoDAO.obtenerProductos();
        tablaProductos.setItems(FXCollections.observableArrayList(productos));
    }
    
    private void calcularMargenComercial() {
        try {
            double precio = Double.parseDouble(txtPrecio.getText());
            double coste = Double.parseDouble(txtCoste.getText());
            double margenComercial = precio - coste;
            
            lblMargenComercial.setText(String.format("%.2f", margenComercial));
        } catch (NumberFormatException e) {
            
            lblMargenComercial.setText("");
        }
    }


    @FXML
    private void guardarProducto() {
        Producto producto = new Producto();
        
        producto.setCodigoBarras(txtCodigoBarras.getText());
        producto.setNombre(txtNombre.getText());
        producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
        producto.setCoste(Double.parseDouble(txtCoste.getText()));
        producto.setStock(Integer.parseInt(txtStock.getText()));
        producto.setStockMinimo(Integer.parseInt(txtStockMinimo.getText()));
        producto.setImpuesto(spnImpuesto.getValue());
        producto.setMostrarEnCaja(chkMostrarEnCaja.isSelected());
        producto.setColor(toHexString(colorPicker.getValue()));
        producto.setPosicionPantalla(spnPosicionPantalla.getValue());

        Seccion seccionSeleccionada = cbSecciones.getValue();
        if (seccionSeleccionada != null) {
            producto.setSeccionId(seccionSeleccionada.getCodigo());
        }

        Proveedor proveedorSeleccionado = cbProveedores.getValue();
        if (proveedorSeleccionado != null) {
            producto.setProveedorId(proveedorSeleccionado.getCodigo());
        }

        if (dpEntrada.getValue() != null) {
            producto.setEntrada(dpEntrada.getValue().atStartOfDay());
        }
        if (dpSalida.getValue() != null) {
            producto.setSalida(dpSalida.getValue().atStartOfDay());
        }

        RadioButton seleccionado = (RadioButton) ventaGrupo.getSelectedToggle();
        producto.setVentaPor(seleccionado.getText());

        boolean resultado = productoDAO.insertarProducto(producto);
        if (resultado) {
            showAlert("Éxito", "Producto guardado correctamente", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "No se pudo guardar el producto", Alert.AlertType.ERROR);
        }
    }


        
    

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
            (int)(color.getRed() * 255),
            (int)(color.getGreen() * 255),
            (int)(color.getBlue() * 255));
    }

    
   
    
    @FXML
    public void cancelarProducto() {
        
    }
    
    private void cargarDatosProductos(Producto producto) {
    	if(producto != null) {
    		/*
    		txtCodigoBarras.setText(producto.getCodigoBarras());
    		txtNombre.setText(producto.getNombre());
    		txtPrecio.setText(producto.getPrecio());
    		spinnerImpuesto.getValueFactory().setValue(producto.getImpuesto());
    		txtCoste.setDouble(producto.getCoste());
    		
    		*/
    		
    	}
    }
    
    private void cargarProveedores() {
        List<Proveedor> proveedores = proveedorDAO.obtenerProveedores();
        
        // Configura cbFiltroProveedor para usar la lista de proveedores
        cbFiltroProveedor.setItems(FXCollections.observableArrayList(proveedores));
        // Configura cbProveedores para usar la misma lista de proveedores
        cbProveedores.setItems(FXCollections.observableArrayList(proveedores));
        
        // Configura cómo se muestra el texto para cbFiltroProveedor y cbProveedores
        StringConverter<Proveedor> proveedorConverter = new StringConverter<Proveedor>() {
            @Override
            public String toString(Proveedor proveedor) {
                return proveedor != null ? proveedor.getNombre() : "";
            }

            @Override
            public Proveedor fromString(String string) {
                return null; // No es necesario para un ComboBox
            }
        };

        cbFiltroProveedor.setConverter(proveedorConverter);
        cbProveedores.setConverter(proveedorConverter);
    }

    private void cargarSecciones() {
        List<Seccion> secciones = seccionDAO.obtenerSecciones();
        
        cbSecciones.setItems(FXCollections.observableArrayList(secciones));
        cbFiltroSeccion.setItems(FXCollections.observableArrayList(secciones)); 
        // Configura cómo se muestra el texto para cbSecciones y, opcionalmente, para cbFiltroSeccion 
        StringConverter<Seccion> seccionConverter = new StringConverter<Seccion>() {
            @Override
            public String toString(Seccion seccion) {
                return seccion != null ? seccion.getNombre() : "";
            }

            @Override
            public Seccion fromString(String string) {
                return null; 
            }
        };

        cbSecciones.setConverter(seccionConverter);
        
        if (cbFiltroSeccion != null) {
            cbFiltroSeccion.setConverter(seccionConverter);
        }
    }


    @FXML
    private void filtrarProductos() {
        Seccion seccionSeleccionada = cbFiltroSeccion.getValue();
        Proveedor proveedorSeleccionado = cbFiltroProveedor.getValue();
        
        
        List<Producto> productosFiltrados = productoDAO.obtenerProductosFiltrados(seccionSeleccionada, proveedorSeleccionado);
        tablaProductos.setItems(FXCollections.observableArrayList(productosFiltrados));
    }
    
    
    

    
}

