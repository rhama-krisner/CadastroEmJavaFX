package app.cadastro;

import app.cadastro.api.ApiClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Cadastrar implements Initializable {
    @FXML
    private Button btnCancelar;

    @FXML
    TextField textFieldNome;
    @FXML
    TextField textFieldIdade;
    @FXML
    TextField textFieldCpf;
    @FXML
    TextField textFieldEmail;

    private MainController mainController;

    public void setMainController(MainController mainController, MenuItem menuNovo) {
        this.mainController = mainController;
    }

    @FXML
    private void btnCadastrarOnClick() throws IOException {
        String nome = textFieldNome.getText();
        int idade = Integer.parseInt(textFieldIdade.getText());
        String cpf = textFieldCpf.getText();
        String email = textFieldEmail.getText();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nome", nome);
        jsonObject.put("idade",idade);
        jsonObject.put("cpf",cpf);
        jsonObject.put("email",email);

        StringBuilder sb = new StringBuilder();
        sb.append(jsonObject.toString(2));

        System.out.println(sb.toString());

        ApiClient.postPessoa(sb.toString());

        voltarParaMain();
    }

    // Método que será chamado para voltar ao MainController
    public void voltarParaMain() throws IOException {
        // Verifique se a referência ao MainController está configurada
        if (mainController != null) {
            // Chame um método no MainController para retornar à janela principal
            mainController.retornarAoMain();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (mainController != null){
            mainController.desativarMenuItem();
        }
    }
}
