package sistema.view.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import sistema.controller.StatusController;
import sistema.controller.TipoMovimentoController;
import sistema.controller.FormaPagamentoCtrl;
import sistema.controller.ProdutoController;
import sistema.controller.ClienteController;
import sistema.controller.VendaController;
import sistema.model.*;

import java.util.List;

public class CadastroController {

    @FXML private Button btnCliente;
    @FXML private Button btnProduto;
    @FXML private Button btnVendas;

    @FXML private GridPane formCliente;
    @FXML private GridPane formProduto;
    @FXML private GridPane formVendas;
    //cadastro cliente
    @FXML private TextField nomeCliente;
    @FXML private TextField cpfCnpjCliente;
    @FXML private TextField telefoneCliente;
    @FXML private TextField emailCliente;
    //cadastro produto
    @FXML private TextField nomeProduto;
    @FXML private TextField descricaoProduto;
    @FXML private TextField custoProduto;
    @FXML private TextField valorProduto;
    @FXML private Spinner<Integer> estoqueProduto;
    //cadastro venda
    @FXML private ChoiceBox<String> clienteVenda;
    @FXML private ChoiceBox<String> produtoVenda;
    @FXML private TextField quantidadeVenda;
    @FXML private TextField valorVenda;
    @FXML private TextField descontoVenda;
    @FXML private ChoiceBox<String> formaPagamentoVenda;
    @FXML private ChoiceBox<String> statusVenda;

    @FXML private Button btnSalvarProduto;
    @FXML private Button btnSalvarCliente;
    @FXML private Button btnSalvarVenda;

    private ClienteController clienteCtrl = new ClienteController();
    private ProdutoController produtoCtrl = new ProdutoController();
    private FormaPagamentoCtrl formaPagamentoCtrl = new FormaPagamentoCtrl();
    private StatusController statusCtrl = new StatusController();
    private VendaController vendaCtrl = new VendaController();

    @FXML
    public void initialize() {
        // Define as ações dos botões superiores
        btnCliente.setOnAction(e -> mostrarFormulario(formCliente));
        btnProduto.setOnAction(e -> mostrarFormulario(formProduto));
        btnVendas.setOnAction(e -> mostrarFormulario(formVendas));

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0);
        estoqueProduto.setValueFactory(valueFactory);

        if (btnSalvarVenda != null){btnSalvarVenda.setOnAction(e -> salvarVenda());}
        if (btnSalvarCliente != null) btnSalvarCliente.setOnAction(e -> salvarCliente());
        if (btnSalvarProduto != null) btnSalvarProduto.setOnAction(e -> salvarProduto());
        carregarChoiceBox();
    }

    private void mostrarFormulario(GridPane formularioSelecionado) {
        formCliente.setVisible(false);
        formProduto.setVisible(false);
        formVendas.setVisible(false);

        formularioSelecionado.setVisible(true);
    }

    private void carregarChoiceBox(){
        try{
            List<Cliente> clientes = clienteCtrl.listarClientes();
            for (Cliente c : clientes){
                clienteVenda.getItems().add(c.getIdCliente() + " - " + c.getNome());
            }
            List<Produto> produtos = produtoCtrl.listarProdutos();
            for (Produto p : produtos) {
                produtoVenda.getItems().add(p.getIdProduto() + " - " + p.getNome() + " (R$ " + p.getValorVenda() + ")");
            }
            List<FormaPagamento> pagamentos = formaPagamentoCtrl.listarFormasPagamento();
            for (FormaPagamento f : pagamentos) {
                formaPagamentoVenda.getItems().add(f.getIdPagamento() + " - " + f.getDescricao());
            }
            List<Status> status = statusCtrl.listarStatus();
            for (Status s : status) {
                statusVenda.getItems().add(s.getIdStatus() + " - " + s.getDescricao());
            }
        } catch (Exception e) {
            exibirAlerta("Erro de Carregamento", "Não foi possível buscar as opções no banco: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void salvarCliente(){
        String nome = nomeCliente.getText();
        String cpfCnpj = cpfCnpjCliente.getText();
        String telefone = telefoneCliente.getText();
        String email = emailCliente.getText();

        try {
            clienteCtrl.cadastrarCliente(nome, cpfCnpj, telefone, email);

            exibirAlerta("Sucesso", "Cliente cadastrado com sucesso!", Alert.AlertType.INFORMATION);

            // Limpa os campos
            nomeCliente.clear();
            cpfCnpjCliente.clear();
            telefoneCliente.clear();
            emailCliente.clear();

        } catch (RuntimeException e) {
            exibirAlerta("Regra de Negócio", e.getMessage(), Alert.AlertType.WARNING);
        } catch (Exception e) {
            exibirAlerta("Erro do Sistema", "Ocorreu um erro ao salvar o cliente: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void salvarProduto(){
        String nome = nomeProduto.getText();
        String descricao = descricaoProduto.getText();
        String custo = custoProduto.getText();
        String valor = valorProduto.getText();
        Integer estoque = estoqueProduto.getValue();

        try {
            // Chama o ProdutoCtrl que criamos anteriormente
            produtoCtrl.cadastrarProduto(nome, descricao, custo, valor, estoque);

            exibirAlerta("Sucesso", "Produto cadastrado com sucesso!", Alert.AlertType.INFORMATION);

            // Limpa os campos
            nomeProduto.clear();
            descricaoProduto.clear();
            custoProduto.clear();
            valorProduto.clear();
            estoqueProduto.setValueFactory(null);

        } catch (RuntimeException e) {
            // Vai capturar as validações do ProdutoService (ex: "O estoque não pode ser negativo")
            exibirAlerta("Regra de Negócio", e.getMessage(), Alert.AlertType.WARNING);
        } catch (Exception e) {
            exibirAlerta("Erro do Sistema", "Ocorreu um erro ao salvar o produto: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void salvarVenda(){
        if (clienteVenda.getValue() == null || produtoVenda.getValue() == null || formaPagamentoVenda.getValue() == null || statusVenda.getValue() == null) {
            exibirAlerta("Aviso", "Preencha todas as seleções obrigatórias (Cliente, Produto, Pagamento e Status).", Alert.AlertType.WARNING);
            return;
        }
        try{
            String idCliente = clienteVenda.getValue().split(" - ")[0].trim();
            String idProduto = produtoVenda.getValue().split(" - ")[0].trim();
            String idPagamento = formaPagamentoVenda.getValue().split(" - ")[0].trim();
            String idStatus = statusVenda.getValue().split(" - ")[0].trim();

            String quantidade = quantidadeVenda.getText();
            String valorUnitario = valorVenda.getText();
            String desconto = descontoVenda.getText();

            Usuario usuarioDaSessao = new Usuario();
            usuarioDaSessao.setIdUsuario(1);

            int idVendaGerado = vendaCtrl.registrarVenda(
                    idCliente, idProduto, quantidade, valorUnitario,
                    desconto, idPagamento, idStatus, usuarioDaSessao
            );
            exibirAlerta("Sucesso", "A venda #" + idVendaGerado + " foi registrada e o estoque foi abatido!", Alert.AlertType.INFORMATION);

            quantidadeVenda.clear();
            valorVenda.clear();
            descontoVenda.clear();
            clienteVenda.setValue(null);
            produtoVenda.setValue(null);
            formaPagamentoVenda.setValue(null);
            statusVenda.setValue(null);
        } catch (RuntimeException e) {
            exibirAlerta("Regra de Negócio", e.getMessage(), Alert.AlertType.WARNING);
        } catch (Exception e){
            exibirAlerta("Erro do Sistema", "Ocorreu um erro crítico ao salvar: " + e.getMessage(), Alert.AlertType.ERROR);
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
