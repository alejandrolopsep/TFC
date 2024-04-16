package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import entidades.Seccion;
import entidades.SeccionDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeccionesController {

	@FXML
	private ListView<Seccion> listViewSecciones;
	@FXML
	private TextField textFieldNombre;
	@FXML
	private ColorPicker colorPickerColor;
	@FXML
	private TextField textFieldPosicion;
	@FXML
	private Button btnAgregar;
	@FXML
	private Button btnEliminar;
	@FXML
	private Button btnActualizar;
	@FXML
	private VBox vBoxSecciones;

	private final SeccionDAO seccionDAO = new SeccionDAO();

	@FXML
	public void initialize() {
	    cargarSecciones();

	    listViewSecciones.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> mostrarDetallesSeccion(newValue));

	    listViewSecciones.setCellFactory(param -> new ListCell<Seccion>() {
	        @Override
	        protected void updateItem(Seccion seccion, boolean empty) {
	            super.updateItem(seccion, empty);
	            if (empty || seccion == null) {
	                setText(null);
	                setStyle("-fx-background-color: transparent;"); 
	            } else {
	                setText(seccion.getNombre());
	                setStyle("-fx-background-color: " + seccion.getColor() + ";");
	            }
	        }
	    });
	}

	private void cargarSecciones() {
	    List<Seccion> secciones = seccionDAO.obtenerSecciones();
	    listViewSecciones.getItems().setAll(secciones);
	}

	private void mostrarDetallesSeccion(Seccion seccion) {
	    if (seccion != null) {
	        textFieldNombre.setText(seccion.getNombre());
	        colorPickerColor.setValue(Color.web(seccion.getColor()));
	        textFieldPosicion.setText(String.valueOf(seccion.getPosicion()));
	    } else {
	        textFieldNombre.clear();
	        colorPickerColor.setValue(Color.WHITE); 
	        textFieldPosicion.clear();
	    }
	}



	@FXML
	private void handleAgregarAction() {
		Seccion seccion = new Seccion();
		seccion.setNombre(textFieldNombre.getText());
		seccion.setColor(toHexString(colorPickerColor.getValue()));
		seccion.setPosicion(Integer.parseInt(textFieldPosicion.getText()));
		seccionDAO.agregarSeccion(seccion);
		cargarSecciones();
	}

	@FXML
	private void handleEliminarAction() {
	    Seccion seleccionada = listViewSecciones.getSelectionModel().getSelectedItem();
	    if (seleccionada != null) {
	        seccionDAO.eliminarSeccion(seleccionada.getCodigo());
	        cargarSecciones(); 
	        limpiarDetalles(); 
	        listViewSecciones.getSelectionModel().clearSelection(); 
	    }
	}

	private void limpiarDetalles() {
	    
	    textFieldNombre.clear();
	    colorPickerColor.setValue(Color.WHITE); 
	    textFieldPosicion.clear();
	}


	@FXML
	private void handleActualizarAction() {
		Seccion seleccionada = listViewSecciones.getSelectionModel().getSelectedItem();
		if (seleccionada != null) {
			seleccionada.setNombre(textFieldNombre.getText());
			seleccionada.setColor(toHexString(colorPickerColor.getValue()));
			seleccionada.setPosicion(Integer.parseInt(textFieldPosicion.getText()));
			seccionDAO.actualizarSeccion(seleccionada);
			cargarSecciones();
		}
	}

	private String toHexString(Color color) {
		return String.format("#%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255),
				(int) (color.getBlue() * 255));
	}
}
