package controllers;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import entidades.ProovedorDAO;
import entidades.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class ProveedoresController {

	@FXML
	private TextField txtNombre, txtEmail, txtNif, txtTelefono, txtUrl, txtDireccion, txtPersonaContacto, txtFormaPago, txtBanco, txtNumeroCuenta, txtTransporte;
	@FXML
	private ColorPicker colorPicker;
	@FXML
	private Spinner<Integer> spinnerPosicion;

	@FXML
	private Spinner<Double> spinnerImpuesto, spinnerRecargo, spinnerDescuento;

	@FXML
	private DatePicker dpDiaPago, dpVencimiento;

	@FXML
	private ComboBox<String> cbPortes;
	@FXML
	private TextArea txtObservaciones;

	private ProovedorDAO proveedorDAO = new ProovedorDAO();

	@FXML
	private TableView<Proveedor> tableProveedores;
	@FXML
	private TableColumn<Proveedor, Integer> columnCodigo;
	@FXML
	private TableColumn<Proveedor, String> columnNombre;


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
        // Inicialización del controlador
        cbPortes.getItems().addAll("Pagados", "Debidos");
        
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        tableProveedores.setItems(obtenerProveedoresDetallados());

        
        
        tableProveedores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cargarDatosProveedor(newSelection);
            }
        });

        // Configuración de Spinner para Impuesto
        SpinnerValueFactory<Double> valueFactoryImpuesto = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, 0, 0.01);
        spinnerImpuesto.setValueFactory(valueFactoryImpuesto);

        // Configuración de Spinner para Recargo
        SpinnerValueFactory<Double> valueFactoryRecargo = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, 0, 0.01);
        spinnerRecargo.setValueFactory(valueFactoryRecargo);

        // Configuración de Spinner para Descuento
        SpinnerValueFactory<Double> valueFactoryDescuento = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, 0, 0.01);
        spinnerDescuento.setValueFactory(valueFactoryDescuento);


        
    }
        
        
        
    private void cargarDatosProveedor(Proveedor proveedor) {
        if (proveedor != null) {
            txtNombre.setText(proveedor.getNombre());
            txtEmail.setText(proveedor.getEmail());
            txtNif.setText(proveedor.getNif());
            txtTelefono.setText(proveedor.getTelefono());
            txtUrl.setText(proveedor.getUrl());
            txtDireccion.setText(proveedor.getDireccion());
            txtPersonaContacto.setText(proveedor.getPersonaContacto());
            txtFormaPago.setText(proveedor.getFormaPago());

            // Ajustar los valores numéricos en los Spinner
            spinnerImpuesto.getValueFactory().setValue(proveedor.getImpuesto());
            spinnerRecargo.getValueFactory().setValue(proveedor.getRecargoEquiv());
            spinnerDescuento.getValueFactory().setValue(proveedor.getDescuento());

            // Ajustar las fechas en los DatePicker
            dpDiaPago.setValue(proveedor.getDiaPago()); 
            dpVencimiento.setValue(proveedor.getVencimiento()); 

            txtBanco.setText(proveedor.getBanco());
            txtNumeroCuenta.setText(proveedor.getNumeroCuenta());
            cbPortes.setValue(proveedor.getPortes());
            txtTransporte.setText(proveedor.getTransporte());

            // Para el ColorPicker
            if (proveedor.getColor() != null && !proveedor.getColor().isEmpty()) {
                colorPicker.setValue(Color.web(proveedor.getColor()));
            } else {
                colorPicker.setValue(Color.WHITE); // Valor por defecto si no hay color
            }

            // Ajustar el valor del Spinner de la posición en pantalla
            spinnerPosicion.getValueFactory().setValue(proveedor.getPosicionPantalla());

            txtObservaciones.setText(proveedor.getObservaciones());
        } else {
            limpiarCampos(); // Llama al método limpiarCampos si el proveedor es nulo
        }
    }


    
    
    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtEmail.clear();
        txtNif.clear();
        txtTelefono.clear();
        txtUrl.clear();
        txtDireccion.clear();
        txtPersonaContacto.clear();
        txtFormaPago.clear();
        txtBanco.clear();
        txtNumeroCuenta.clear();
        txtTransporte.clear();
        txtObservaciones.clear();
        colorPicker.setValue(Color.WHITE); 
        spinnerPosicion.getValueFactory().setValue(1);

      
        spinnerImpuesto.getValueFactory().setValue(0.0);
        spinnerRecargo.getValueFactory().setValue(0.0);
        spinnerDescuento.getValueFactory().setValue(0.0);



        
        cbPortes.getSelectionModel().clearSelection();
        dpDiaPago.setValue(null);
        dpVencimiento.setValue(null);
    }





   
    public ObservableList<Proveedor> obtenerProveedores() {
        ObservableList<Proveedor> proveedores = FXCollections.observableArrayList();
        String sql = "SELECT codigo, nombre FROM proveedores";
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                Proveedor proveedor = new Proveedor(rs.getInt("codigo"), rs.getString("nombre"));
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return proveedores;
    }
    
    
    public ObservableList<Proveedor> obtenerProveedoresDetallados() {
        ObservableList<Proveedor> proveedores = FXCollections.observableArrayList();
        String sql = "SELECT * FROM proveedores";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setCodigo(rs.getInt("codigo"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setEmail(rs.getString("email"));
                proveedor.setNif(rs.getString("nif"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setUrl(rs.getString("url"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setPersonaContacto(rs.getString("personaContacto"));
                proveedor.setFormaPago(rs.getString("formaPago"));

                proveedor.setImpuesto(rs.getDouble("impuesto"));
                proveedor.setRecargoEquiv(rs.getDouble("recargoEquiv"));
                proveedor.setDescuento(rs.getDouble("descuento"));

                Date diaPagoDate = rs.getDate("diaPago");
                LocalDate diaPago = diaPagoDate != null ? diaPagoDate.toLocalDate() : null;
                proveedor.setDiaPago(diaPago);

                Date vencimientoDate = rs.getDate("vencimiento");
                LocalDate vencimiento = vencimientoDate != null ? vencimientoDate.toLocalDate() : null;
                proveedor.setVencimiento(vencimiento);

                proveedor.setBanco(rs.getString("banco"));
                proveedor.setNumeroCuenta(rs.getString("numeroCuenta"));
                proveedor.setPortes(rs.getString("portes"));
                proveedor.setTransporte(rs.getString("transporte"));
                proveedor.setColor(rs.getString("color"));
                proveedor.setPosicionPantalla(rs.getInt("posicionPantalla"));
                proveedor.setObservaciones(rs.getString("observaciones"));
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proveedores;
    }



    @FXML
    private void guardarProveedor() {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(txtNombre.getText());
        proveedor.setEmail(txtEmail.getText());
        proveedor.setNif(txtNif.getText());
        proveedor.setTelefono(txtTelefono.getText());
        proveedor.setUrl(txtUrl.getText());
        proveedor.setDireccion(txtDireccion.getText());
        proveedor.setPersonaContacto(txtPersonaContacto.getText());
        proveedor.setFormaPago(txtFormaPago.getText());
        proveedor.setImpuesto(spinnerImpuesto.getValue()); 
        proveedor.setRecargoEquiv(spinnerRecargo.getValue()); 
        proveedor.setDescuento(spinnerDescuento.getValue()); 
        proveedor.setDiaPago(dpDiaPago.getValue()); 
        proveedor.setVencimiento(dpVencimiento.getValue()); 
        proveedor.setBanco(txtBanco.getText());
        proveedor.setNumeroCuenta(txtNumeroCuenta.getText());
        proveedor.setPortes(cbPortes.getValue());
        proveedor.setTransporte(txtTransporte.getText());
        proveedor.setColor(toHexString(colorPicker.getValue()));
        proveedor.setPosicionPantalla(spinnerPosicion.getValue());
        proveedor.setObservaciones(txtObservaciones.getText());

        boolean insertado = proveedorDAO.insertarProveedor(proveedor);
        if (insertado) {
            showAlert("Success", "Proveedor insertado correctamente", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "Error al insertar el proveedor", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void actualizarProveedor() {
        if (tableProveedores.getSelectionModel().getSelectedItem() != null) {
            Proveedor proveedorSeleccionado = tableProveedores.getSelectionModel().getSelectedItem();
            
            
            proveedorSeleccionado.setNombre(txtNombre.getText());
            proveedorSeleccionado.setEmail(txtEmail.getText());
            
            
            // Llamar al método de tu clase DAO para actualizar
            boolean fueActualizado = proveedorDAO.actualizarProveedor(proveedorSeleccionado);
            
            if (fueActualizado) {
                showAlert("Proveedor actualizado", "El proveedor ha sido actualizado correctamente.", Alert.AlertType.INFORMATION);
                
            } else {
                showAlert("Error al actualizar", "No se pudo actualizar el proveedor.", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Seleccione un proveedor", "Por favor, seleccione un proveedor de la tabla para actualizar.", Alert.AlertType.WARNING);
        }
    }

    
    @FXML
    private void cancelarProveedor() {
        System.out.println("Operación cancelada");
        cerrarVentana();
    }

    private void cerrarVentana() {
        
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
    
    private String toHexString(javafx.scene.paint.Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
    
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

