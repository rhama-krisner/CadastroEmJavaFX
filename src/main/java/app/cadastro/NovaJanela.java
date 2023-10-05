package app.cadastro;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class NovaJanela {
    @FXML
    private Button btnCancelar;

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // Método que será chamado para voltar ao MainController
    public void voltarParaMain() throws IOException {
        // Verifique se a referência ao MainController está configurada
        if (mainController != null) {
            // Chame um método no MainController para retornar à janela principal
            mainController.retornarAoMain();
        }
    }
}
