/* package sistema.view;

import sistema.model.Usuario;
import sistema.view.cadastros.*;
import sistema.view.listagem.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

public class PainelInicialView extends JFrame {

    private Usuario usuarioLogado;
    private JPanel painelConteudo;
    private CardLayout cardLayout;

    public PainelInicialView(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;

        configurarTela();
        montarMenuLateral();
        montarPainelConteudo();
    }

    private void configurarTela() {
        setTitle("Sistema - Usuario: " + usuarioLogado.getNome());
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
    }

    private void montarMenuLateral() {
        JPanel menu = new JPanel();
        menu.setLayout(null);
        menu.setBounds(0, 0, 220, 650);
        menu.setBackground(new Color(45, 45, 45));
        add(menu);

        JLabel titulo = new JLabel("Menu");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(30, 25, 150, 30);
        menu.add(titulo);

        JButton botaoInicio = criarBotao("Inicio", 80);
        JButton botaoCadastros = criarBotao("Cadastros/Exclusao", 130);
        JButton botaoListagem = criarBotao("Listagem", 180);
        JButton botaoConfiguracoes = criarBotao("Configuracoes", 230);
        JButton botaoSair = criarBotao("Sair", 530);

        menu.add(botaoInicio);
        menu.add(botaoCadastros);
        menu.add(botaoListagem);
        menu.add(botaoConfiguracoes);
        menu.add(botaoSair);

        botaoInicio.addActionListener(e -> cardLayout.show(painelConteudo, "inicio"));
        botaoCadastros.addActionListener(e -> cardLayout.show(painelConteudo, "cadastros"));
        botaoListagem.addActionListener(e -> cardLayout.show(painelConteudo, "listagem"));
        botaoConfiguracoes.addActionListener(e -> cardLayout.show(painelConteudo, "configuracoes"));

        botaoSair.addActionListener(e -> {
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
            dispose();
        });
    }

    private JButton criarBotao(String texto, int y) {
        JButton botao = new JButton(texto);
        botao.setBounds(20, y, 180, 35);
        return botao;
    }

    private void montarPainelConteudo() {
        cardLayout = new CardLayout();

        painelConteudo = new JPanel();
        painelConteudo.setLayout(cardLayout);
        painelConteudo.setBounds(220, 0, 780, 650);
        add(painelConteudo);

        painelConteudo.add(montarInicio(), "inicio");
        painelConteudo.add(montarCadastros(), "cadastros");
        painelConteudo.add(montarListagem(), "listagem");
        painelConteudo.add(montarConfiguracoes(), "configuracoes");

        cardLayout.show(painelConteudo, "inicio");
    }

    private JPanel montarInicio() {
        JPanel painel = new JPanel();
        painel.setLayout(null);

        JLabel titulo = new JLabel("Painel Inicial");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(40, 30, 300, 30);
        painel.add(titulo);

        JLabel usuario = new JLabel("Usuario logado: " + usuarioLogado.getNome());
        usuario.setBounds(40, 80, 400, 25);
        painel.add(usuario);

        return painel;
    }

    private JPanel montarCadastros() {
        JPanel painel = new JPanel();
        painel.setLayout(null);

        JLabel titulo = new JLabel("Cadastros e Exclusao");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(40, 30, 350, 30);
        painel.add(titulo);

        JButton botaoPessoas = new JButton("Pessoas");
        botaoPessoas.setBounds(40, 90, 180, 40);
        painel.add(botaoPessoas);

        JButton botaoProdutos = new JButton("Produtos");
        botaoProdutos.setBounds(240, 90, 180, 40);
        painel.add(botaoProdutos);

        JButton botaoVendas = new JButton("Vendas");
        botaoVendas.setBounds(440, 90, 180, 40);
        painel.add(botaoVendas);

        JButton botaoMovimentacoes = new JButton("Movimentacoes");
        botaoMovimentacoes.setBounds(40, 150, 180, 40);
        painel.add(botaoMovimentacoes);

        JLabel resultado = new JLabel("Selecione uma opcao.");
        resultado.setBounds(40, 230, 600, 25);
        painel.add(resultado);

        botaoPessoas.addActionListener(e -> abrirCadastroPessoas());
        botaoProdutos.addActionListener(e -> abrirCadastroProdutos());
        botaoVendas.addActionListener(e -> abrirCadastroVendas());
        botaoMovimentacoes.addActionListener(e -> abrirCadastroMovimentacoes());

        return painel;
    }

    private JPanel montarListagem() {
        JPanel painel = new JPanel();
        painel.setLayout(null);

        JLabel titulo = new JLabel("Listagem");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(40, 30, 300, 30);
        painel.add(titulo);

        JButton botaoPessoas = new JButton("Listar Pessoas");
        botaoPessoas.setBounds(40, 90, 180, 40);
        painel.add(botaoPessoas);

        JButton botaoProdutos = new JButton("Listar Produtos");
        botaoProdutos.setBounds(240, 90, 180, 40);
        painel.add(botaoProdutos);

        JButton botaoVendas = new JButton("Listar Vendas");
        botaoVendas.setBounds(440, 90, 180, 40);
        painel.add(botaoVendas);

        JButton botaoMovimentacoes = new JButton("Listar Movimentacoes");
        botaoMovimentacoes.setBounds(40, 150, 180, 40);
        painel.add(botaoMovimentacoes);

        botaoPessoas.addActionListener(e -> abrirListagemPessoas());
        botaoProdutos.addActionListener(e -> abrirListagemProdutos());
        botaoVendas.addActionListener(e -> abrirListagemVendas());
        botaoMovimentacoes.addActionListener(e -> abrirListagemMovimentacoes());

        return painel;
    }

    private JPanel montarConfiguracoes() {
        JPanel painel = new JPanel();
        painel.setLayout(null);

        JLabel titulo = new JLabel("Configuracoes");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(40, 30, 300, 30);
        painel.add(titulo);

        JButton botaoCadastroUsuario = new JButton("Cadastro de Usuario");
        botaoCadastroUsuario.setBounds(40, 90, 220, 40);
        painel.add(botaoCadastroUsuario);

        JButton botaoListagemUsuarios = new JButton("Listagem de Usuarios");
        botaoListagemUsuarios.setBounds(280, 90, 220, 40);
        painel.add(botaoListagemUsuarios);

        JButton botaoLogs = new JButton("Logs");
        botaoLogs.setBounds(520, 90, 120, 40);
        painel.add(botaoLogs);

        botaoCadastroUsuario.addActionListener(e -> abrirCadastroUsuario());
        botaoListagemUsuarios.addActionListener(e -> abrirListagemUsuarios());
        botaoLogs.addActionListener(e -> abrirLogs());

        return painel;
    }

    private void abrirCadastroPessoas() {
        CadastroPessoaView tela = new CadastroPessoaView();
        tela.setVisible(true);
    }

    private void abrirCadastroProdutos() {
        CadastroProdutoView tela = new CadastroProdutoView();
        tela.setVisible(true);
    }

    private void abrirCadastroVendas() {
        CadastroVendaView tela = new CadastroVendaView();
        tela.setVisible(true);
    }

    private void abrirCadastroMovimentacoes() {
        CadastroMovimentacaoView tela = new CadastroMovimentacaoView();
        tela.setVisible(true);
    }

    private void abrirListagemPessoas() {
        ListagemPessoaView tela = new ListagemPessoaView();
        tela.setVisible(true);
    }

    private void abrirListagemProdutos() {
        ListagemProdutoView tela = new ListagemProdutoView();
        tela.setVisible(true);
    }

    private void abrirListagemVendas() {
        ListagemVendaView tela = new ListagemVendaView();
        tela.setVisible(true);
    }

    private void abrirListagemMovimentacoes() {
        ListagemMovimentacaoView tela = new ListagemMovimentacaoView();
        tela.setVisible(true);
    }

    private void abrirCadastroUsuario() {
        CadastroUsuarioView tela = new CadastroUsuarioView();
        tela.setVisible(true);
    }

    private void abrirListagemUsuarios() {
        ListagemUsuarioView tela = new ListagemUsuarioView();
        tela.setVisible(true);
    }

    private void abrirLogs() {
        LogsView tela = new LogsView();
        tela.setVisible(true);
    }
} */