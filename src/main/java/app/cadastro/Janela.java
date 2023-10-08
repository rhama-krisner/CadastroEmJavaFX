package app.cadastro;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Janela {
    public static void abrir(Class<?> classe,String uri, String titulo, Boolean resizable) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(classe.getResource(uri));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.setResizable(resizable);
        stage.show();
    }

    public static void fechar(Button button){
        Window window = button.getScene().getWindow();
        if (window instanceof Stage){
            Stage stage = (Stage) window;
            stage.close();
        }
    }
}
