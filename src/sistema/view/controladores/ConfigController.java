package sistema.view.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ConfigController {

    // Personalização de detalhes
    @FXML private TextField txtFormaPagamento;
    @FXML private TextField txtStatus;
    @FXML private TextField txtTipoTransacao;
    @FXML private Button btnSalvarPagamento;
    @FXML private Button btnSalvarStatus;
    @FXML private Button btnSalvarTransacao;

    // Imagem logo
    @FXML private ImageView imgLogo;
    @FXML private Button btnSelecionarLogo;
    @FXML private Button btnSalvarLogo;

    // Alteração de dados cadastrados
    @FXML private Button btnFiltrarClientes;
    @FXML private Button btnFiltrarProdutos;
    @FXML private Button btnFiltrarVendas;
    @FXML private ListView<String> listViewDados;
    @FXML private Button btnSalvarDados;

    // Cores
    @FXML private Button btnTemaAzul;
    @FXML private Button btnTemaVerde;
    @FXML private Button btnTemaEscuro;

    @FXML
    public void initialize() {
        configurarSecaoAdicoes();
        configurarSecaoLogo();
        configurarSecaoDados();
        configurarSecaoTemas();
    }

    private void configurarSecaoAdicoes() {
        btnSalvarPagamento.setOnAction(e -> System.out.println("Salvando Forma de Pagamento: " + txtFormaPagamento.getText()));
        btnSalvarStatus.setOnAction(e -> System.out.println("Salvando Status: " + txtStatus.getText()));
        btnSalvarTransacao.setOnAction(e -> System.out.println("Salvando Tipo de Transação: " + txtTipoTransacao.getText()));
    }

    private void configurarSecaoLogo() {
        // Carrega uma imagem padrão placeholder caso não tenha nenhuma definida
        imgLogo.setImage(new Image("https://www.pngkey.com/png/full/276-2767518_resultado-de-imagen-de-burro-png-ne-de.png"));

        btnSelecionarLogo.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Selecionar Logotipo");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg")
            );

            // Abre a janela para escolher o arquivo
            Stage stage = (Stage) btnSelecionarLogo.getScene().getWindow();
            File arquivoSelecionado = fileChooser.showOpenDialog(stage);

            if (arquivoSelecionado != null) {
                Image novaImagem = new Image(arquivoSelecionado.toURI().toString());
                imgLogo.setImage(novaImagem);
            }
        });

        btnSalvarLogo.setOnAction(e -> System.out.println("Logotipo do sistema atualizado com sucesso!"));
    }

    private void configurarSecaoDados() {
        // Listas simuladas para alimentar o ListView dinamicamente
        ObservableList<String> clientes = FXCollections.observableArrayList("");
        ObservableList<String> produtos = FXCollections.observableArrayList("");
        ObservableList<String> vendas = FXCollections.observableArrayList("");

        // Define Clientes como padrão inicial
        listViewDados.setItems(clientes);

        // Altera o conteúdo da ListView conforme o clique
        btnFiltrarClientes.setOnAction(e -> listViewDados.setItems(clientes));
        btnFiltrarProdutos.setOnAction(e -> listViewDados.setItems(produtos));
        btnFiltrarVendas.setOnAction(e -> listViewDados.setItems(vendas));

        btnSalvarDados.setOnAction(e -> System.out.println("Mudanças realizadas."));
    }

     private void configurarSecaoTemas() {
        /*
        btnTemaAzul.setOnAction(e ->
        btnTemaVerde.setOnAction(e ->
        btnTemaEscuro.setOnAction(e ->
        */
     }
}