package sistema.view;

import sistema.controller.LoginController;
import sistema.model.Usuario;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame {

    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;
    private JButton botaoSair;

    private LoginController loginController;

    public LoginView() {
        loginController = new LoginController();

        configurarTela();
        montarComponentes();

    }

    private void configurarTela() {
        setTitle("Login - Gestão de Estoque");
        setSize(400, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
    }

    private void montarComponentes() {

        JLabel titulo = new JLabel("Acesso ao Sistema");
        titulo.setBounds(135, 25, 180, 25);
        add(titulo);

        JLabel labelLogin = new JLabel("Login:");
        labelLogin.setBounds(55, 75, 100, 25);
        add(labelLogin);

        campoLogin = new JTextField();
        campoLogin.setBounds(135, 75, 190, 25);
        add(campoLogin);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(55, 115, 100, 25);
        add(labelSenha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(135, 115, 190, 25);
        add(campoSenha);

        botaoEntrar = new JButton("Entrar");
        botaoEntrar.setBounds(135, 165, 90, 30);
        add(botaoEntrar);

        botaoSair = new JButton("Sair");
        botaoSair.setBounds(235, 165, 90, 30);
        add(botaoSair);

        JButton botaoRecuperarSenha = new JButton("Recuperar Senha");
        botaoRecuperarSenha.setBounds(135, 215, 190, 30);
        add(botaoRecuperarSenha);

        botaoEntrar.addActionListener(e -> realizarLogin());
        botaoSair.addActionListener(e -> System.exit(0));

        botaoRecuperarSenha.addActionListener(e -> {
            RecuperacaoSenhaView tela = new RecuperacaoSenhaView();
            tela.setVisible(true);
        });
    }

    private void realizarLogin() {
        try {
            String login = campoLogin.getText();
            String senha = new String(campoSenha.getPassword());

            Usuario usuarioLogado = loginController.realizarLogin(login, senha);

            JOptionPane.showMessageDialog(
                    this,
                    "Login realizado com sucesso!\nBem-vindo, " + usuarioLogado.getNome()
            );

            PainelInicialView painelInicial = new PainelInicialView(usuarioLogado);
            painelInicial.setVisible(true);


            dispose();

        } catch (RuntimeException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    erro.getMessage(),
                    "Erro ao realizar login",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}