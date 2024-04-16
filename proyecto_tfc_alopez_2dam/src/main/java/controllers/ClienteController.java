package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entidades.Cliente;
import entidades.ClienteDAO;

import java.time.LocalDate;
import java.util.List;

public class ClienteController {

    @FXML
    private TextField txtNombre, txtApellidos, txtDNICIF, txtTelefono, txtEmail, txtDireccion;
    @FXML
    private TextField txtDescuento, txtRecomendadoPor, txtAtendidoPor;
    @FXML
    private DatePicker dpCumpleanos, dpFechaAlta, dpUltimaVisita;
    @FXML
    private ComboBox<String> cbSexo;
    @FXML
    private CheckBox chkEnviarEmail, chkEnviarWhatsapp, chkEnviarSMS;
    @FXML
    private TextArea txtObservaciones;
    @FXML
    private Button btnGuardar, btnCancelar;

    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, Integer> columnCodigo;
    @FXML
    
    private TableColumn<Cliente, String> columnNombreApellidos;

    private ObservableList<Cliente> clientesOL;
    private ClienteDAO clienteDAO = new ClienteDAO();
    @FXML
    private TableColumn<Cliente, String> columnNombre;
    @FXML
    private TableColumn<Cliente, String> columnApellidos;
   


    public ClienteController() {
        clienteDAO = new ClienteDAO();
    }
    
    
    @FXML
    private void initialize() {
        configurarTabla();
        cargarClientes();
        
        cbSexo.setItems(FXCollections.observableArrayList("Hombre", "Mujer"));
    }

    private void configurarTabla() {
    	
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        
    }


    private void cargarClientes() {
        List<Cliente> clientes = clienteDAO.obtenerClientes();
        clientesOL = FXCollections.observableArrayList(clientes);
        tablaClientes.setItems(clientesOL);
    }

    private void rellenarFormularioConDatosCliente(Cliente cliente) {
        txtNombre.setText(cliente.getNombre());
        txtApellidos.setText(cliente.getApellidos());
        txtDNICIF.setText(cliente.getDniCif());
        txtTelefono.setText(cliente.getTelefono());
        txtEmail.setText(cliente.getEmail());
        txtDireccion.setText(cliente.getDireccion());
        txtDescuento.setText(String.valueOf(cliente.getDescuento()));
        txtRecomendadoPor.setText(cliente.getRecomendadoPor());
        txtAtendidoPor.setText(cliente.getAtendidoPor());
        dpCumpleanos.setValue(cliente.getCumpleanos());
        dpFechaAlta.setValue(cliente.getFechaAlta());
        dpUltimaVisita.setValue(cliente.getUltimaVisita());
        cbSexo.setValue(cliente.getSexo());
        chkEnviarEmail.setSelected(cliente.isEnviarEmail());
        chkEnviarWhatsapp.setSelected(cliente.isEnviarWhatsapp());
        chkEnviarSMS.setSelected(cliente.isEnviarSms());
        txtObservaciones.setText(cliente.getObservaciones());
    }

    @FXML
    private void guardarCliente() {
        // Implementación de guardarCliente
    }

    @FXML
    private void cancelarOperacion() {
        // Implementación de cancelarOperacion
    }
}

