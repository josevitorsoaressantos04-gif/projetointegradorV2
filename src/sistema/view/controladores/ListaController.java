package sistema.view.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sistema.controller.ProdutoController;
import sistema.controller.ClienteController;
import sistema.controller.VendaController;

import sistema.model.Venda;

import java.math.BigDecimal;
import java.util.List;

public class ListaController {

    @FXML private TableView<sistema.model.Produto> tabelaProdutos;

    @FXML private TableColumn<sistema.model.Produto, Integer> colIdProduto;
    @FXML private TableColumn<sistema.model.Produto, String> colNomeProduto;
    @FXML private TableColumn<sistema.model.Produto, BigDecimal> colCusto;
    @FXML private TableColumn<sistema.model.Produto, BigDecimal> colValorVenda;
    @FXML private TableColumn<sistema.model.Produto, Integer> colEstoque;

    private ProdutoController produtoCtrl = new ProdutoController();

    @FXML private TableView<sistema.model.Cliente> tabelaClientes;

    @FXML private TableColumn<sistema.model.Cliente, Integer> colIdCliente;
    @FXML private TableColumn<sistema.model.Cliente, String> colNomeCliente;
    @FXML private TableColumn<sistema.model.Cliente, String> colCpfCnpj;
    @FXML private TableColumn<sistema.model.Cliente, String> colTelefone;
    @FXML private TableColumn<sistema.model.Cliente, String> colEmail;

    private ClienteController clienteCtrl = new ClienteController();

    @FXML private TableView<sistema.model.Venda> tabelaVendas;

    @FXML private TableColumn<sistema.model.Venda, Number> colIdVenda;
    @FXML private TableColumn<sistema.model.Venda, String> colValorTotal;
    @FXML private TableColumn<sistema.model.Venda, Number> colDesconto;
    @FXML private TableColumn<sistema.model.Venda, Number> colData;
    @FXML private TableColumn<sistema.model.Venda,String> colFormaPagamento;
    @FXML private TableColumn<sistema.model.Venda,String> colStatus;
    @FXML private TableColumn<sistema.model.Venda,String> colClienteIdCliente;


    private VendaController vendaCtrl = new VendaController();

    @FXML private Button btnAdicionarColuna;

    @FXML
    public void initialize() {
        configurarColunas();
        carregarDadosBanco();
    }

    private void configurarColunas(){
        colIdProduto.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
        colNomeProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCusto.setCellValueFactory(new PropertyValueFactory<>("valorCusto"));
        colValorVenda.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
        colEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));

        colIdCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpfCnpj.setCellValueFactory(new PropertyValueFactory<>("cpfCnpj"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        colIdVenda.setCellValueFactory(new PropertyValueFactory<>("idVenda"));
        colClienteIdCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        colValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        colDesconto.setCellValueFactory(new PropertyValueFactory<>("desconto"));
        colFormaPagamento.setCellValueFactory(new PropertyValueFactory<>("descricaoPagamento"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("descricaoStatus"));
        colData.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));

    }
    private void carregarDadosBanco(){
        try {
            List<sistema.model.Produto> produtosBanco = produtoCtrl.listarProdutos();

            ObservableList<sistema.model.Produto> dadosObservados = FXCollections.observableArrayList(produtosBanco);

            tabelaProdutos.setItems(dadosObservados);

        } catch (RuntimeException e){
            exibirAlerta("Erro de Banco de Dados", "Não foi possível carregar os produtos: " + e.getMessage(), Alert.AlertType.ERROR);
        }

        try {
            List<sistema.model.Cliente> clientesBanco = clienteCtrl.listarClientes();

            ObservableList<sistema.model.Cliente> dadosObservados = FXCollections.observableArrayList(clientesBanco);

            tabelaClientes.setItems(dadosObservados);

        } catch (RuntimeException e){
            exibirAlerta("Erro de Banco de Dados", "Não foi possível carregar os clientes: " + e.getMessage(), Alert.AlertType.ERROR);
        }

        try {
            List<sistema.model.Venda> vendasBanco = vendaCtrl.listarVendas();

            ObservableList<sistema.model.Venda> dadosObservados = FXCollections.observableArrayList(vendasBanco);

            tabelaVendas.setItems(dadosObservados);

        } catch (RuntimeException e){
            exibirAlerta("Erro de Banco de Dados", "Não foi possível carregar as vendas: " + e.getMessage(), Alert.AlertType.ERROR);
        }

    }
    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}