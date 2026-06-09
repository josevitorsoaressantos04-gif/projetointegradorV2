package sistema.view;

import sistema.DAO.ClienteDAO;
import sistema.DAO.DadosIniciaisDAO;
import sistema.DAO.ProdutoDAO;
import sistema.model.Cliente;
import sistema.model.Produto;
import sistema.model.Usuario;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.math.BigDecimal;

public class TesteBackendView extends JFrame {

    private Usuario usuarioLogado;

    private ClienteDAO clienteDAO;
    private ProdutoDAO produtoDAO;
    private DadosIniciaisDAO dadosIniciaisDAO;

    private JTextField campoNomeCliente;
    private JTextField campoCpfCnpj;
    private JTextField campoTelefone;
    private JTextField campoEmail;
    private JTextArea areaResultadoCliente;

    private JTextField campoNomeProduto;
    private JTextField campoDescricaoProduto;
    private JTextField campoValorCusto;
    private JTextField campoValorVenda;
    private JTextField campoEstoque;
    private JTextArea areaResultadoProduto;

    public TesteBackendView(Usuario usuarioLogado) {

        this.usuarioLogado = usuarioLogado;

        clienteDAO = new ClienteDAO();
        produtoDAO = new ProdutoDAO();
        dadosIniciaisDAO = new DadosIniciaisDAO();

        configurarTela();
        montarAbas();
    }

    private void configurarTela() {
        setTitle("Gestão de Estoque - Usuário: " + usuarioLogado.getNome());
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void montarAbas() {
        JTabbedPane abas = new JTabbedPane();
        abas.setBounds(20, 20, 840, 560);

        abas.addTab("Clientes", montarAbaCliente());
        abas.addTab("Produtos", montarAbaProduto());
        abas.addTab("Sistema", montarAbaSistema());

        add(abas);
    }

    private JPanel montarAbaCliente() {
        JPanel painel = new JPanel();
        painel.setLayout(null);

        JLabel titulo = new JLabel("Cadastro de Cliente");
        titulo.setBounds(30, 20, 200, 25);
        painel.add(titulo);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(30, 60, 100, 25);
        painel.add(labelNome);

        campoNomeCliente = new JTextField();
        campoNomeCliente.setBounds(130, 60, 250, 25);
        painel.add(campoNomeCliente);

        JLabel labelCpfCnpj = new JLabel("CPF/CNPJ:");
        labelCpfCnpj.setBounds(30, 95, 100, 25);
        painel.add(labelCpfCnpj);

        campoCpfCnpj = new JTextField();
        campoCpfCnpj.setBounds(130, 95, 250, 25);
        painel.add(campoCpfCnpj);

        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setBounds(30, 130, 100, 25);
        painel.add(labelTelefone);

        campoTelefone = new JTextField();
        campoTelefone.setBounds(130, 130, 250, 25);
        painel.add(campoTelefone);

        JLabel labelEmail = new JLabel("E-mail:");
        labelEmail.setBounds(30, 165, 100, 25);
        painel.add(labelEmail);

        campoEmail = new JTextField();
        campoEmail.setBounds(130, 165, 250, 25);
        painel.add(campoEmail);

        JButton botaoCadastrar = new JButton("Cadastrar Cliente");
        botaoCadastrar.setBounds(130, 205, 180, 30);
        painel.add(botaoCadastrar);

        botaoCadastrar.addActionListener(e -> cadastrarCliente());

        JButton botaoListar = new JButton("Listar Clientes");
        botaoListar.setBounds(320, 205, 160, 30);
        painel.add(botaoListar);

        botaoListar.addActionListener(e -> listarClientes());

        JButton botaoLimpar = new JButton("Limpar Resultado");
        botaoLimpar.setBounds(490, 205, 170, 30);
        painel.add(botaoLimpar);

        botaoLimpar.addActionListener(e -> areaResultadoCliente.setText(""));

        areaResultadoCliente = new JTextArea();
        areaResultadoCliente.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(areaResultadoCliente);
        scrollPane.setBounds(30, 270, 760, 240);
        painel.add(scrollPane);

        return painel;
    }

    private JPanel montarAbaProduto() {
        JPanel painel = new JPanel();
        painel.setLayout(null);

        JLabel titulo = new JLabel("Cadastro de Produto");
        titulo.setBounds(30, 20, 200, 25);
        painel.add(titulo);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(30, 60, 120, 25);
        painel.add(labelNome);

        campoNomeProduto = new JTextField();
        campoNomeProduto.setBounds(150, 60, 250, 25);
        painel.add(campoNomeProduto);

        JLabel labelDescricao = new JLabel("Descrição:");
        labelDescricao.setBounds(30, 95, 120, 25);
        painel.add(labelDescricao);

        campoDescricaoProduto = new JTextField();
        campoDescricaoProduto.setBounds(150, 95, 250, 25);
        painel.add(campoDescricaoProduto);

        JLabel labelValorCusto = new JLabel("Valor Custo:");
        labelValorCusto.setBounds(30, 130, 120, 25);
        painel.add(labelValorCusto);

        campoValorCusto = new JTextField();
        campoValorCusto.setBounds(150, 130, 250, 25);
        painel.add(campoValorCusto);

        JLabel labelValorVenda = new JLabel("Valor Venda:");
        labelValorVenda.setBounds(30, 165, 120, 25);
        painel.add(labelValorVenda);

        campoValorVenda = new JTextField();
        campoValorVenda.setBounds(150, 165, 250, 25);
        painel.add(campoValorVenda);

        JLabel labelEstoque = new JLabel("Estoque:");
        labelEstoque.setBounds(30, 200, 120, 25);
        painel.add(labelEstoque);

        campoEstoque = new JTextField();
        campoEstoque.setBounds(150, 200, 250, 25);
        painel.add(campoEstoque);

        JButton botaoCadastrar = new JButton("Cadastrar Produto");
        botaoCadastrar.setBounds(150, 240, 180, 30);
        painel.add(botaoCadastrar);

        botaoCadastrar.addActionListener(e -> cadastrarProduto());

        JButton botaoListar = new JButton("Listar Produtos");
        botaoListar.setBounds(340, 240, 160, 30);
        painel.add(botaoListar);

        botaoListar.addActionListener(e -> listarProdutos());

        JButton botaoLimpar = new JButton("Limpar Resultado");
        botaoLimpar.setBounds(510, 240, 170, 30);
        painel.add(botaoLimpar);

        botaoLimpar.addActionListener(e -> areaResultadoProduto.setText(""));

        areaResultadoProduto = new JTextArea();
        areaResultadoProduto.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(areaResultadoProduto);
        scrollPane.setBounds(30, 300, 760, 210);
        painel.add(scrollPane);

        return painel;
    }

    private JPanel montarAbaSistema() {
        JPanel painel = new JPanel();
        painel.setLayout(null);

        JLabel titulo = new JLabel("Informações do Sistema");
        titulo.setBounds(30, 20, 250, 25);
        painel.add(titulo);

        JLabel usuario = new JLabel("Usuário logado: " + usuarioLogado.getNome());
        usuario.setBounds(30, 60, 400, 25);
        painel.add(usuario);

        JLabel login = new JLabel("Login: " + usuarioLogado.getLogin());
        login.setBounds(30, 95, 400, 25);
        painel.add(login);

        JButton botaoDadosIniciais = new JButton("Inserir Dados Iniciais");
        botaoDadosIniciais.setBounds(30, 140, 220, 30);
        painel.add(botaoDadosIniciais);

        botaoDadosIniciais.addActionListener(e -> inserirDadosIniciais());

        JButton botaoSair = new JButton("Sair do Sistema");
        botaoSair.setBounds(30, 185, 220, 30);
        painel.add(botaoSair);

        botaoSair.addActionListener(e -> {
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
            dispose();
        });

        return painel;
    }

    private void cadastrarCliente() {
        try {
            Cliente cliente = new Cliente();

            cliente.setNome(campoNomeCliente.getText());
            cliente.setCpfCnpj(campoCpfCnpj.getText());
            cliente.setTelefone(campoTelefone.getText());
            cliente.setEmail(campoEmail.getText());

            clienteDAO.cadastrarCliente(cliente);

            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");

            limparCamposCliente();
            listarClientes();

        } catch (RuntimeException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    erro.getMessage(),
                    "Erro ao cadastrar cliente",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void listarClientes() {
        try {
            areaResultadoCliente.setText("");
            areaResultadoCliente.append("CLIENTES CADASTRADOS\n");
            areaResultadoCliente.append("-----------------------------------\n");

            for (Cliente cliente : clienteDAO.listar()) {
                areaResultadoCliente.append("ID: " + cliente.getIdCliente() + "\n");
                areaResultadoCliente.append("Nome: " + cliente.getNome() + "\n");
                areaResultadoCliente.append("CPF/CNPJ: " + cliente.getCpfCnpj() + "\n");
                areaResultadoCliente.append("Telefone: " + cliente.getTelefone() + "\n");
                areaResultadoCliente.append("E-mail: " + cliente.getEmail() + "\n");
                areaResultadoCliente.append("-----------------------------------\n");
            }

        } catch (RuntimeException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    erro.getMessage(),
                    "Erro ao listar clientes",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void cadastrarProduto() {
        try {
            Produto produto = new Produto();

            produto.setNome(campoNomeProduto.getText());
            produto.setDescricao(campoDescricaoProduto.getText());
            produto.setValorCusto(new BigDecimal(campoValorCusto.getText()));
            produto.setValorVenda(new BigDecimal(campoValorVenda.getText()));
            produto.setEstoque(Integer.parseInt(campoEstoque.getText()));

            produtoDAO.inserir(produto);

            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");

            limparCamposProduto();
            listarProdutos();

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    "Informe valores válidos para custo, venda e estoque.",
                    "Erro de validação",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (RuntimeException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    erro.getMessage(),
                    "Erro ao cadastrar produto",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void listarProdutos() {
        try {
            areaResultadoProduto.setText("");
            areaResultadoProduto.append("PRODUTOS CADASTRADOS\n");
            areaResultadoProduto.append("-----------------------------------\n");

            for (Produto produto : produtoDAO.listar()) {
                areaResultadoProduto.append("ID: " + produto.getIdProduto() + "\n");
                areaResultadoProduto.append("Nome: " + produto.getNome() + "\n");
                areaResultadoProduto.append("Descrição: " + produto.getDescricao() + "\n");
                areaResultadoProduto.append("Valor Custo: " + produto.getValorCusto() + "\n");
                areaResultadoProduto.append("Valor Venda: " + produto.getValorVenda() + "\n");
                areaResultadoProduto.append("Estoque: " + produto.getEstoque() + "\n");
                areaResultadoProduto.append("-----------------------------------\n");
            }

        } catch (RuntimeException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    erro.getMessage(),
                    "Erro ao listar produtos",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void inserirDadosIniciais() {
        try {
            dadosIniciaisDAO.inserirDadosIniciais();

            JOptionPane.showMessageDialog(
                    this,
                    "Dados iniciais inseridos com sucesso!"
            );

        } catch (RuntimeException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    erro.getMessage(),
                    "Erro ao inserir dados iniciais",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void limparCamposCliente() {
        campoNomeCliente.setText("");
        campoCpfCnpj.setText("");
        campoTelefone.setText("");
        campoEmail.setText("");
    }

    private void limparCamposProduto() {
        campoNomeProduto.setText("");
        campoDescricaoProduto.setText("");
        campoValorCusto.setText("");
        campoValorVenda.setText("");
        campoEstoque.setText("");
    }
}