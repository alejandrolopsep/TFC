package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import entidades.FormaDePago;
import entidades.FormaDePagoDAO;
import javafx.collections.FXCollections;

import java.util.List;

public class FormasDePagoController {
    @FXML
    private TextField txtNombre;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TableView<FormaDePago> tableFormasDePago;
    @FXML
    private TableColumn<FormaDePago, Integer> columnCodigo;
    @FXML
    private TableColumn<FormaDePago, String> columnNombre;
    @FXML
    private TableColumn<FormaDePago, String> columnColor;

    private final FormaDePagoDAO dao = new FormaDePagoDAO();

    @FXML
    private void initialize() {
        // Configurar las columnas de la tabla
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnColor.setCellValueFactory(new PropertyValueFactory<>("color"));

        // Cargar las formas de pago existentes en la tabla
        cargarFormasDePago();
    }

    private void cargarFormasDePago() {
        List<FormaDePago> formasDePago = dao.obtenerFormasDePago();
        tableFormasDePago.setItems(FXCollections.observableArrayList(formasDePago));
    }

    @FXML
    private void guardarFormaDePago() {
        String nombre = txtNombre.getText();
        Color color = colorPicker.getValue();
        String colorHex = String.format("#%02X%02X%02X", 
            (int)(color.getRed() * 255), 
            (int)(color.getGreen() * 255), 
            (int)(color.getBlue() * 255));

        FormaDePago formaDePago = new FormaDePago();
        formaDePago.setNombre(nombre);
        formaDePago.setColor(colorHex);

        boolean resultado = dao.insertarFormaDePago(formaDePago);
        if (resultado) {
            System.out.println("Forma de pago guardada con éxito.");
            cargarFormasDePago(); // Recargar la lista de formas de pago
        } else {
            System.out.println("Error al guardar la forma de pago.");
        }
    }

    @FXML
    private void cancelarFormaDePago() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}

