package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

import entidades.Empleado;
import entidades.EmpleadoDAO;

public class SeleccionEmpleadosHorarioController {

    @FXML
    private FlowPane flowPaneEmpleados;

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @FXML
    public void initialize() {
        cargarEmpleados();
    }
    
    

    private void cargarEmpleados() {
        List<Empleado> empleadosList = empleadoDAO.obtenerEmpleados();
        ObservableList<Empleado> empleados = FXCollections.observableArrayList(empleadosList);
        
        for (Empleado empleado : empleados) {
            VBox empleadoBox = new VBox();
            empleadoBox.setAlignment(Pos.CENTER);
            empleadoBox.setSpacing(5);
            empleadoBox.getStyleClass().add("empleado-box");
            
            Pane colorPane = new Pane();
            colorPane.setPrefSize(100, 100);
            colorPane.setStyle("-fx-background-color: " + empleado.getColor() + ";");
            colorPane.getStyleClass().add("color-pane");
            
            Label nombreLabel = new Label(empleado.getNombre() + " " + empleado.getApellidos());
            empleadoBox.getChildren().addAll(colorPane, nombreLabel);
            
            flowPaneEmpleados.getChildren().add(empleadoBox);
            empleadoBox.setOnMouseClicked(event -> abrirControlHorarioConEmpleado(empleado));
        }
    }
    
    
    
    
    private void abrirControlHorarioConEmpleado(Empleado empleadoSeleccionado) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ControlHorario.fxml"));
            Parent root = loader.load();

            ControlHorarioController controlHorarioController = loader.getController();
            // Ahora pasamos tanto el nombre como el apellido al método setNombreEmpleado.
            controlHorarioController.setNombreEmpleado(empleadoSeleccionado.getNombre(), empleadoSeleccionado.getApellidos());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            // Si deseas, también puedes incluir el apellido en el título de la ventana.
            stage.setTitle("Control Horario - " + empleadoSeleccionado.getNombre() + " " + empleadoSeleccionado.getApellidos());
            stage.show();
            stage.setMaximized(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}



