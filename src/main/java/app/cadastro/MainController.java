package app.cadastro;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    AnchorPane mainController;

    @FXML
    BorderPane borderPane;

    @FXML
    MenuItem menuNovo;

    @FXML
    public void mudandoDeAnchorPane(){
        try {
            // Carregue a nova janela a partir do arquivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Novajanela.fxml"));
            AnchorPane novaJanela = loader.load();

            NovaJanela novaJanelaController = loader.getController();
            novaJanelaController.setMainController(this);

            // Substitua o conteúdo do BorderPane pelo conteúdo da nova janela
            borderPane.setCenter(novaJanela);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void retornarAoMain() throws IOException {
        try {
            // Carregue novamente o conteúdo original do BorderPane (por exemplo, a partir do arquivo FXML original)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
            AnchorPane conteudoOriginal = loader.load();

            // Remova o conteúdo atual do BorderPane
            borderPane.setCenter(null);

            // Adicione o conteúdo original de volta ao BorderPane
            borderPane.setCenter(conteudoOriginal);
            borderPane.setTop(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuNovo.setOnAction(actionEvent -> {
            mudandoDeAnchorPane();
        });
    }
}