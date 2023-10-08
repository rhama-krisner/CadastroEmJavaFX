package app.cadastro;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import java.awt.Desktop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class SobreController implements Initializable {
    @FXML
    private Button btnFechar;
    @FXML
    private Label label;
    @FXML
    private void btnFecharOnClicked(){
      Janela.fechar(btnFechar);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
