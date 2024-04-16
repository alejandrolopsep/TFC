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

public class SeleccionEmpleadosController {

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
            empleadoBox.setOnMouseClicked(event -> abrirCajaConEmpleado(empleado));
        }
    }
    
    private void abrirCajaConEmpleado(Empleado empleado) {
        try {
            // Carga el FXML de Caja
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Caja.fxml"));
            Parent root = loader.load();

            // Obtiene el controlador de Caja y establece el nombre del empleado
            CajaController cajaController = loader.getController();
            cajaController.setNombreEmpleado(empleado.getNombre() + " " + empleado.getApellidos());

            // Prepara y muestra la nueva ventana
            Stage stage = new Stage();
            stage.setTitle("Caja");
            stage.setScene(new Scene(root));
            stage.setMaximized(true); // Establece la ventana a pantalla completa
            stage.show();

            // Opcional: Cierra la ventana actual, si es necesario
            // ((Stage) flowPaneEmpleados.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    

}



