package app.cadastro;

import app.cadastro.api.ApiClient;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TableView<Pessoa> tableView = new TableView<>();

    // Table Colum
    @FXML
    private TableColumn<Pessoa, Long> idColum;
    @FXML
    private TableColumn<Pessoa, String> nomeColum;
    @FXML
    private TableColumn<Pessoa, Integer> idadeColum;
    @FXML
    private TableColumn<Pessoa, String> cpfColum;
    @FXML
    private TableColumn<Pessoa, String> emailColum;

    @FXML
    private AnchorPane mainController;
    @FXML
    private BorderPane borderPane;
    @FXML
    private MenuItem menuNovo;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem menuItemSobre;

    @FXML
    public void mudandoDeAnchorPane() {
        try {
            String url = "NovaJanela.fxml";
            // Carregue a nova janela a partir do arquivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            AnchorPane novaJanela = loader.load();

            NovaJanela novaJanelaController = loader.getController();
            novaJanelaController.setMainController(this, menuNovo);

            // Substitua o conteúdo do BorderPane pelo conteúdo da nova janela
            borderPane.setCenter(novaJanela);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void retornarAoMain() throws IOException {
        try {
            // Carrega o conteúdo original do BorderPane
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
            AnchorPane conteudoOriginal = loader.load();

            // Remove o conteúdo atual do BorderPane
            borderPane.setCenter(null);

            // Adiciona o conteúdo original de volta ao BorderPane
            borderPane.setCenter(conteudoOriginal);
            borderPane.setTop(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColum.setCellValueFactory(new PropertyValueFactory<Pessoa, Long>("id"));
        nomeColum.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
        cpfColum.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("cpf"));
        idadeColum.setCellValueFactory(new PropertyValueFactory<Pessoa, Integer>("idade"));
        emailColum.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("email"));

        tableView.setItems(initialData());

        menuNovo.setOnAction(actionEvent -> {
            mudandoDeAnchorPane();
        });


    }

    ObservableList<Pessoa> initialData(){
        try {
            List<Pessoa> pessoas = ApiClient.getPessoas();
            if (pessoas != null){
                return FXCollections.observableArrayList(pessoas);
            } else {
                System.out.println("Erro em retornar pessoas");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList();
    }


    public void desativarMenuItem() {
        menuNovo.setDisable(true);
    }

    @FXML
    private void menuItemSobreOnClicked(ActionEvent actionEvent) throws IOException {
        String uri = "SobreView.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(uri));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        stage.setTitle("Sobre");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }


}
