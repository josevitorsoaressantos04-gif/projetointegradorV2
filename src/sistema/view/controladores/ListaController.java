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

    @FXML private TableView<Vendas> tabelaVendas;

    @FXML private TableColumn<Vendas, Number> colIdVenda;
    @FXML private TableColumn<Vendas, String> colValorTotal;
    @FXML private TableColumn<Vendas, Number> colDesconto;
    @FXML private TableColumn<Vendas, String> colFormaPagamento;
    @FXML private TableColumn<Vendas, Number> colData;
    @FXML private TableColumn<Vendas,String> colStatus;

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
            List<sistema.model.Cliente> produtosBanco = clienteCtrl.listarClientes();

            ObservableList<sistema.model.Cliente> dadosObservados = FXCollections.observableArrayList(produtosBanco);

            tabelaClientes.setItems(dadosObservados);

        } catch (RuntimeException e){
            exibirAlerta("Erro de Banco de Dados", "Não foi possível carregar os clientes: " + e.getMessage(), Alert.AlertType.ERROR);
        }

    }
    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    public static class Vendas {}
}