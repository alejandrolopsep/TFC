package proyecto;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.StyleManager;

public class InicioSesion extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Crear el escenario para la pantalla de carga
        Stage splashStage = new Stage(StageStyle.UNDECORATED);
        Parent splashRoot = FXMLLoader.load(getClass().getResource("/Portada.fxml"));
        Scene splashScene = new Scene(splashRoot);
        splashStage.setScene(splashScene);
        splashStage.setMaximized(true);

        // Aplicar estilos a la escena de la pantalla de carga
        StyleManager.applyStyles(splashScene);

        // Pantalla de inicio de sesión
        Parent loginRoot = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
        Scene loginScene = new Scene(loginRoot);
        
        // Aplicar estilos a la escena de inicio de sesión
        StyleManager.applyStyles(loginScene);

        splashStage.show();

        new Thread(() -> {
            try {
                Thread.sleep(3000);  // Espera 3 segundos antes de mostrar la pantalla de inicio de sesión
                Platform.runLater(() -> {
                    splashStage.close();  // Cierra la pantalla de portada
                    Stage loginStage = new Stage();
                    loginStage.setTitle("TPV Login");
                    loginStage.setScene(loginScene);
                    loginStage.setMaximized(true);  // Establece la ventana al máximo
                    loginStage.show();
                    
                    // Si cambias la escena en algún momento, asegúrate de aplicar los estilos nuevamente
                    loginStage.sceneProperty().addListener((obs, oldScene, newScene) -> {
                        if (newScene != null) {
                            StyleManager.applyStyles(newScene);
                        }
                    });
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


