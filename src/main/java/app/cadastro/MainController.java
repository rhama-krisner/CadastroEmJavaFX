package app.cadastro;

import app.cadastro.api.ApiClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private TableView<Pessoa> tableView;
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
    private Pagination pagination;
    private static final int itensPorPagina = 10;
    private ObservableList<Pessoa> data;
    @FXML
    public void mudandoDeAnchorPane() {
        try {
            String url = "NovaJanela.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            AnchorPane novaJanela = loader.load();
            Cadastrar cadastrarController = loader.getController();
            cadastrarController.setMainController(this, menuNovo);
            borderPane.setCenter(novaJanela);
        } catch (IOException e) {
            e.printStackTrace();
        }
        menuNovo.setDisable(true);
        pagination.setVisible(false);
    }

    public void retornarAoMain() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
            AnchorPane conteudoOriginal = loader.load();
            borderPane.setCenter(null);
            borderPane.setCenter(conteudoOriginal);
            borderPane.setTop(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //dados que serão carregados na tableView
    ObservableList<Pessoa> initialData() {
        try {
            List<Pessoa> pessoas = ApiClient.getPessoas();
            if (pessoas != null) {
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

    private int calcularNumeroDePaginas() {
        return (int) Math.ceil((double) initialData().size() / itensPorPagina);
    }

    private void paginacaoConfig() {
        pagination.setPageCount(calcularNumeroDePaginas());
        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            int indexInicial = newIndex.intValue() * itensPorPagina;
            int indexFinal = Math.min(indexInicial + itensPorPagina, data.size()); // Use a lista de dados armazenada
            tableView.setItems(FXCollections.observableArrayList(data.subList(indexInicial, indexFinal)));
        });
    }

    private void listaDeDados() {
        idColum.setCellValueFactory(new PropertyValueFactory<Pessoa, Long>("id"));
        nomeColum.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
        cpfColum.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("cpf"));
        idadeColum.setCellValueFactory(new PropertyValueFactory<Pessoa, Integer>("idade"));
        emailColum.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("email"));

        data = initialData();
        
        tableView.setItems(FXCollections.observableArrayList(data));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paginacaoConfig();
        listaDeDados();

        // Exibir apenas a primeira página na paginação
        int indexInicial = 0;
        int indexFinal = Math.min(indexInicial + itensPorPagina, data.size());
        tableView.setItems(FXCollections.observableArrayList(data.subList(indexInicial, indexFinal)));

        menuNovo.setOnAction(actionEvent -> mudandoDeAnchorPane());
    }

}
