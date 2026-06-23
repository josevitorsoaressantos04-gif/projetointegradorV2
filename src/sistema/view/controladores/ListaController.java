package sistema.view.controladores;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListaController {

    @FXML private TableView<Produto> tabelaProdutos;
    @FXML private TableColumn<Produto, Number> colIdProduto;
    @FXML private TableColumn<Produto, String> colNomeProduto;
    @FXML private TableColumn<Produto, Number> colCusto;
    @FXML private TableColumn<Produto, Number> colValorVenda;
    @FXML private TableColumn<Produto, Number> colEstoque;

    @FXML private TableView<Cliente> tabelaClientes;
    @FXML private TableColumn<Cliente, Number> colIdCliente;
    @FXML private TableColumn<Cliente, String> colNomeCliente;
    @FXML private TableColumn<Cliente, Number> colCpf;
    @FXML private TableColumn<Cliente, Number> colTelefone;
    @FXML private TableColumn<Cliente, Number> colEmail;

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
        // Configurando como as colunas vão ler os dados do objeto Produto
        colIdProduto.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colNomeProduto.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        colCusto.setCellValueFactory(cellData -> cellData.getValue().precoProperty());

        // Simulando dados de banco de dados
        ObservableList<Produto> dadosMock = FXCollections.observableArrayList(
                new Produto(1, "Notebook Dell Inspiron", 4500.00),
                new Produto(2, "Mouse Sem Fio Logitech", 120.50),
                new Produto(3, "Teclado Mecânico Redragon", 280.00),
                new Produto(4, "Monitor LG 24 Polegadas", 850.00),
                new Produto(5, "Cadeira de Escritório Ergonômica", 1150.00)
        );

        tabelaProdutos.setItems(dadosMock);
    }

    public static class Cliente {}
    public static class Vendas {}

    // Classe de Modelo Interna (Você pode extraí-la para um arquivo Produto.java depois)
    public static class Produto {
        private final SimpleIntegerProperty idProduto;
        private final SimpleStringProperty nomeProduto;
        private final SimpleDoubleProperty preco;

        public Produto(int id, String nome, double preco) {
            this.idProduto = new SimpleIntegerProperty(id);
            this.nomeProduto = new SimpleStringProperty(nome);
            this.preco = new SimpleDoubleProperty(preco);
        }

        public SimpleIntegerProperty idProperty() {
            return idProduto;
        }

        public SimpleStringProperty nomeProperty() {
            return nomeProduto;
        }

        public SimpleDoubleProperty precoProperty() {
            return preco;
        }
    }
}
