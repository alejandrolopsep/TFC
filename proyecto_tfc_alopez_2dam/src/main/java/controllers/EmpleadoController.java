package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entidades.Empleado;
import entidades.EmpleadoDAO;

import java.time.LocalDate;
import java.util.List;

public class EmpleadoController {

    @FXML
    private TextField txtNombre, txtApellidos, txtDireccion, txtCifNif, txtTelefono, txtNumeroSS, txtCategoria;
    @FXML
    private PasswordField txtContrasena; // Campo de contraseña
    @FXML
    private DatePicker dpFechaNacimiento, dpFechaAlta;
    @FXML
    private ComboBox<String> cbSexo;
    @FXML
    private CheckBox chkApareceEnAgenda, chkApareceEnCaja;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Button btnGuardar, btnCancelar;
    @FXML
    private TextArea txtObservaciones;

    @FXML
    private TableView<Empleado> tablaEmpleados;
    @FXML
    private TableColumn<Empleado, Integer> columnCodigo;
    @FXML
    private TableColumn<Empleado, String> columnNombre;
    @FXML
    private TableColumn<Empleado, String> columnApellidos;

    private ObservableList<Empleado> empleadosOL;
    private EmpleadoDAO empleadoDAO;

    public EmpleadoController() {
        empleadoDAO = new EmpleadoDAO();
    }

    @FXML
    private void initialize() {
        configurarTabla();
        cargarEmpleados();
        cbSexo.getItems().addAll("Hombre", "Mujer");
    }

    private void configurarTabla() {
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));

        tablaEmpleados.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                rellenarFormularioConDatosEmpleado(newSelection);
            }
        });
    }

    private void cargarEmpleados() {
        List<Empleado> empleados = empleadoDAO.obtenerEmpleados();
        empleadosOL = FXCollections.observableArrayList(empleados);
        tablaEmpleados.setItems(empleadosOL);
    }

    private void rellenarFormularioConDatosEmpleado(Empleado empleado) {
        txtNombre.setText(empleado.getNombre());
        txtApellidos.setText(empleado.getApellidos());
        txtDireccion.setText(empleado.getDireccion());
        dpFechaNacimiento.setValue(empleado.getFechaNacimiento());
        dpFechaAlta.setValue(empleado.getFechaAlta());
        txtCifNif.setText(empleado.getCifNif());
        txtTelefono.setText(empleado.getTelefono());
        txtNumeroSS.setText(empleado.getNumeroSS());
        txtCategoria.setText(empleado.getCategoria());
        cbSexo.setValue(empleado.getSexo());
        chkApareceEnAgenda.setSelected(empleado.isApareceEnAgenda());
        chkApareceEnCaja.setSelected(empleado.isApareceEnCaja());
        colorPicker.setValue(Color.valueOf(empleado.getColor()));
        txtObservaciones.setText(empleado.getObservaciones());
        // No se carga la contraseña por razones de seguridad
    }

    @FXML
    private void guardarEmpleado() {
        Empleado empleado = new Empleado();
        empleado.setNombre(txtNombre.getText());
        empleado.setApellidos(txtApellidos.getText());
        empleado.setDireccion(txtDireccion.getText());
        empleado.setFechaNacimiento(dpFechaNacimiento.getValue());
        empleado.setFechaAlta(dpFechaAlta.getValue());
        empleado.setCifNif(txtCifNif.getText());
        empleado.setTelefono(txtTelefono.getText());
        empleado.setNumeroSS(txtNumeroSS.getText());
        empleado.setCategoria(txtCategoria.getText());
        empleado.setSexo(cbSexo.getValue());
        empleado.setApareceEnAgenda(chkApareceEnAgenda.isSelected());
        empleado.setApareceEnCaja(chkApareceEnCaja.isSelected());
        empleado.setColor(String.format("#%02X%02X%02X",
                (int) (colorPicker.getValue().getRed() * 255),
                (int) (colorPicker.getValue().getGreen() * 255),
                (int) (colorPicker.getValue().getBlue() * 255)));
        empleado.setObservaciones(txtObservaciones.getText());
        empleado.setContrasena(txtContrasena.getText()); // Guarda la contraseña

        boolean resultado = empleadoDAO.insertarEmpleado(empleado);
        if (resultado) {
            empleadosOL.add(empleado);
            // Opcional: Mostrar un mensaje de éxito
        } else {
            // Opcional: Mostrar un mensaje de error
        }
    }

    @FXML
    private void cancelarOperacion() {
        // Limpiar formulario o cerrar ventana
    }
}


