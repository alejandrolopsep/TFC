package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import entidades.ControlHorario;
import entidades.ControlHorarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;

public class ControlHorarioController implements Initializable {

    @FXML private Label labelFechaHora;
    @FXML private ComboBox<String> cbEstado;
    @FXML private TextArea taObservaciones;
    @FXML private TextField tfContrasena;
    @FXML private TableView<ControlHorario> tableViewHorarios;
    @FXML private TableColumn<ControlHorario, LocalDate> columnFecha;
    @FXML private TableColumn<ControlHorario, LocalTime> columnHoraEntrada;
    @FXML private TableColumn<ControlHorario, LocalTime> columnHoraSalida;
    @FXML private TableColumn<ControlHorario, String> columnObservaciones;
    @FXML private Label lblMensaje;
    @FXML private Label labelNombreEmpleado;

    private ControlHorarioDAO controlHorarioDAO = new ControlHorarioDAO();
    private int currentEmpleadoId = -1;  // ID del empleado actual

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizarFechaHora();
        cbEstado.setItems(FXCollections.observableArrayList("Entry", "Exit"));
        setupTableColumns();
    }

    private void setupTableColumns() {
        columnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnHoraEntrada.setCellValueFactory(new PropertyValueFactory<>("horaEntrada"));
        columnHoraSalida.setCellValueFactory(new PropertyValueFactory<>("horaSalida"));
        columnObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
    }

    private void actualizarTabla(int empleadoId) {
        try {
            tableViewHorarios.setItems(FXCollections.observableArrayList(controlHorarioDAO.obtenerRegistrosPorEmpleado(empleadoId)));
        } catch (SQLException e) {
            mostrarMensaje("Error al cargar los datos: " + e.getMessage());
        }
    }

    private void actualizarFechaHora() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Platform.runLater(() -> {
                labelFechaHora.setText(LocalDateTime.now().toString());
            });
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    @FXML
    private void registrarEntradaSalida() {
        try {
            if (tfContrasena.getText().trim().isEmpty() || cbEstado.getValue() == null) {
                mostrarMensaje("Por favor, complete todos los campos requeridos.");
                return;
            }

            String contrasena = tfContrasena.getText().trim();
            String estado = cbEstado.getValue();
            String observaciones = taObservaciones.getText().trim();
            int empleadoId = controlHorarioDAO.obtenerEmpleadoIdPorContrasena(contrasena);

            if (empleadoId == -1) {
                mostrarMensaje("Error: Contraseña no válida o empleado no encontrado.");
                return;
            }

            currentEmpleadoId = empleadoId;  // Guarda el ID del empleado actual
            ControlHorario controlHorario = new ControlHorario(estado, LocalDateTime.now(), observaciones, empleadoId);
            controlHorarioDAO.registrarActualizarHorario(controlHorario);
            actualizarTabla(empleadoId);  // Actualiza la tabla solo para el empleado actual
            mostrarMensaje("Registro actualizado exitosamente.");
            limpiarCampos();
        } catch (SQLException e) {
            mostrarMensaje("Error al registrar: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        taObservaciones.clear();
        tfContrasena.clear();
        cbEstado.getSelectionModel().clearSelection();
    }

    private void mostrarMensaje(String mensaje) {
        if (lblMensaje != null) {
            lblMensaje.setText(mensaje);
        } else {
            System.out.println("Label not initialized.");
        }
    }
    
    public void setNombreEmpleado(String nombre, String apellido) {
        if (labelNombreEmpleado != null) {
            String nombreCompleto = nombre + " " + apellido;
            labelNombreEmpleado.setText(nombreCompleto); // Setting the text of the label
        } else {
            System.out.println("Label not initialized.");
        }
    }
    
}
