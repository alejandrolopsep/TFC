package controllers;



import javafx.fxml.FXML;
import entidades.Cliente;
import entidades.ClienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ClienteController {

    @FXML
    private VBox formularioCliente;

    @FXML
    private TextField txtNombre, txtApellidos, txtTelefono, txtEmail;

    @FXML
    private DatePicker datePickerFechaNacimiento;

    
    
    @FXML
    private TextField txtFechaNacimiento;
    @FXML
    private ListView<Cliente> listaClientes;

    @FXML
    private Button btnRegresar, btnAñadirCliente, btnEliminarCliente, btnMostrarClientes;

    private final ClienteDAO clienteDAO = new ClienteDAO();

    @FXML
    private void initialize() {
        // Initialize client list when the controller is loaded
        actualizarListaClientes();
    }

    @FXML
    private void mostrarFormularioAñadir() {
        formularioCliente.setVisible(true);
    }

    @FXML
    private void handleAñadirCliente() {
        // Obtén los valores del formulario
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        LocalDate fechaNacimiento = datePickerFechaNacimiento.getValue();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        
     

        // Crea y añade el cliente (ajusta según tu implementación)
        Cliente nuevoCliente = new Cliente(nombre, apellidos, fechaNacimiento, telefono, email);
        clienteDAO.añadirCliente(nuevoCliente);

        // Actualiza la lista y limpia el formulario
        actualizarListaClientes();
        limpiarFormulario();
    }


    @FXML
    private void handleCancelar() {
        formularioCliente.setVisible(false);
        limpiarFormulario();
    }

    @FXML
    private void handleMostrarClientes() {
        actualizarListaClientes();
    }

    @FXML
    private void handleEliminarClienteSeleccionado() {
        Cliente seleccionado = listaClientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            clienteDAO.eliminarCliente(seleccionado.getId());
            mostrarAlerta("Éxito", "Cliente eliminado correctamente.");
            actualizarListaClientes();
        } else {
            mostrarAlerta("Error", "Por favor, seleccione un cliente para eliminar.");
        }
    }

    private void actualizarListaClientes() {
        List<Cliente> clientes = clienteDAO.obtenerClientes();
        ObservableList<Cliente> lista = FXCollections.observableArrayList(clientes);
        listaClientes.setItems(lista);
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        txtApellidos.clear();
        txtTelefono.clear();
        txtEmail.clear();
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
    
    @FXML
    private void onRegresarClicked() {
        // Obtiene el Stage actual y lo cierra
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        stage.close();
    }
}
