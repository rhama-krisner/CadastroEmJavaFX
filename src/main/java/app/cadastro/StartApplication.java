package app.cadastro;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String uri = "MainView.fxml";
        Janela.abrir(StartApplication.class, uri, "Cadastro", false);
    }

    public static void main(String[] args) {
        launch();
    }

    @FXML
    public void onMenuItemSobreClicked() throws IOException {
        Janela.abrir(MainController.class, "SobreView.fxml", "Sobre", false);
    }

}