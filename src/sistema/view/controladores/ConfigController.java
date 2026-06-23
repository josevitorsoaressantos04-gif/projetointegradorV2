package sistema.view.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sistema.controller.StatusController;
import sistema.controller.TipoMovimentoController;
import sistema.controller.FormaPagamentoCtrl;
import sistema.controller.ProdutoController;
import sistema.controller.ClienteController;
import sistema.model.Produto;
import sistema.model.Cliente;


import java.io.File;
import java.util.List;

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

    private StatusController statusCtrl = new StatusController();
    private FormaPagamentoCtrl pagamentoCtrl = new FormaPagamentoCtrl();
    private TipoMovimentoController tipoMovimentoCtrl = new TipoMovimentoController();

    private ProdutoController produtoCtrl = new ProdutoController();
    private ClienteController clienteCtrl = new ClienteController();

    @FXML
    public void initialize() {
        configurarSecaoAdicoes();
        configurarSecaoLogo();
        configurarSecaoDados();
        configurarSecaoTemas();
    }

    private void configurarSecaoAdicoes() {
        btnSalvarPagamento.setOnAction(e -> salvarNovaFormaPagamento());
        btnSalvarStatus.setOnAction(e -> salvarNovoStatus());
        btnSalvarTransacao.setOnAction(e -> salvarNovoTipoTransacao());
    }
    private void salvarNovaFormaPagamento(){
        String pagamentoDigitado = txtFormaPagamento.getText();

        try {
            pagamentoCtrl.cadastrarFormaPagamento(pagamentoDigitado);
            exibirAlerta("Sucesso", "Forma de Pagamento cadastrada com sucesso!", Alert.AlertType.INFORMATION);
            txtFormaPagamento.clear();
        }catch (RuntimeException e) {
            exibirAlerta("Aviso", e.getMessage(), Alert.AlertType.WARNING);
        }
    }


    private void salvarNovoStatus(){
        String statusDigitado = txtStatus.getText();

        try {
            statusCtrl.cadastrarStatus(statusDigitado);
            exibirAlerta("Sucesso", "Status cadastrado com sucesso!", Alert.AlertType.INFORMATION);
            txtStatus.clear();
        }catch (RuntimeException e) {
            exibirAlerta("Aviso", e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    private void salvarNovoTipoTransacao(){
        String tipoDigitado = txtTipoTransacao.getText();

        try {
            tipoMovimentoCtrl.cadastrarTipoMovimento(tipoDigitado);
            exibirAlerta("Sucesso", "Tipo de Transação cadastrado com sucesso!", Alert.AlertType.INFORMATION);
            txtTipoTransacao.clear();
        }catch (RuntimeException e) {
            exibirAlerta("Aviso", e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    private void configurarSecaoLogo() {
        // Carrega uma imagem padrão placeholder caso não tenha nenhuma definida
        imgLogo.setImage (new Image("https://www.pngkey.com/png/full/276-2767518_resultado-de-imagen-de-burro-png-ne-de.png"));

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
        carregarClientes();

        // Altera o conteúdo da ListView conforme o clique
        btnFiltrarClientes.setOnAction(e -> carregarClientes());
        btnFiltrarProdutos.setOnAction(e -> carregarProdutos());
        btnFiltrarVendas.setOnAction(e -> listViewDados.setItems(vendas));

        btnSalvarDados.setOnAction(e -> System.out.println("Mudanças realizadas."));
    }

    private void carregarClientes(){
        try {
            List<Cliente> listaClientes = clienteCtrl.listarClientes();
            // Cria uma lista de Strings vazia para o JavaFX
            ObservableList<String> itensFormatados = FXCollections.observableArrayList();

            for (Cliente c: listaClientes){
                String formato = String.format(("ID: %s | %s - CPF/CNPJ: %s | Contatos: %s, %s)"),
                        c.getIdCliente(), c.getNome(),
                        c.getCpfCnpj(), c.getTelefone(), c.getEmail());
                itensFormatados.add(formato);
            }
            listViewDados.setItems(itensFormatados);
        }catch (Exception e){
            exibirAlerta("Erro de Conexão", "Não foi possível carregar os clientes: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void carregarProdutos(){
        try {
            List<Produto> listaProdutos = produtoCtrl.listarProdutos();
            // Cria uma lista de Strings vazia para o JavaFX
            ObservableList<String> itensFormatados = FXCollections.observableArrayList();

            for (Produto p: listaProdutos){
                String formato = String.format(("ID: %d | %s - R$ %.2f (Estoque: %d)"),
                        p.getIdProduto(), p.getNome(),
                        p.getValorVenda(), p.getEstoque());

                itensFormatados.add(formato);
            }
            listViewDados.setItems(itensFormatados);
        }catch (Exception e){
            exibirAlerta("Erro de Conexão", "Não foi possível carregar os produtos: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }


     private void configurarSecaoTemas() {
        /*
        btnTemaAzul.setOnAction(e ->
        btnTemaVerde.setOnAction(e ->
        btnTemaEscuro.setOnAction(e ->
        */
     }
}