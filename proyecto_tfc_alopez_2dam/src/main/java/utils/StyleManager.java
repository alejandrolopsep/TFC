package utils;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class StyleManager {

    private static final String CSS_PATH = "/default_stylesheet.css"; // Asegúrate de que este path es correcto

    // Aplica estilos a Scene
    public static void applyStyles(Scene scene) {
        if (scene != null) {
            applyCss(scene);
        }
    }

    // Aplica estilos a Stage
    public static void applyStyles(Stage stage) {
        if (stage != null) {
            if (stage.getScene() != null) {
                applyCss(stage.getScene());
            }
            // Escuchar cambios en la propiedad de la escena para aplicar estilos a nuevas escenas asignadas a este Stage
            stage.sceneProperty().addListener((obs, oldScene, newScene) -> {
                if (newScene != null) {
                    applyCss(newScene);
                }
            });
        }
    }

    // Método para aplicar el archivo CSS a la escena proporcionada
    private static void applyCss(Scene scene) {
        URL cssResource = StyleManager.class.getResource(CSS_PATH);
        if (cssResource != null) {
            String cssPath = cssResource.toExternalForm();
            if (!scene.getStylesheets().contains(cssPath)) {
                scene.getStylesheets().add(cssPath);
                System.out.println("Estilo CSS aplicado correctamente desde: " + cssPath);
            }
        } else {
            System.err.println("No se encontró el recurso CSS en la ruta especificada: " + CSS_PATH);
        }
    }
}

