package sistema.view.cadastros;

import sistema.controller.UsuarioController;
import sistema.model.Usuario;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CadastroUsuarioView extends JFrame {

    private JTextField campoNome;
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JPasswordField campoConfirmarSenha;

    private UsuarioController usuarioController;

    public CadastroUsuarioView() {
       usuarioController = new UsuarioController();

        configurarTela();
        montarComponentes();
    }

    private void configurarTela() {
        setTitle("Cadastro de Usuário");
        setSize(600, 420);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
    }

    private void montarComponentes() {
        JLabel titulo = new JLabel("Cadastro de Usuário");
        titulo.setBounds(30, 20, 300, 25);
        add(titulo);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(30, 70, 130, 25);
        add(labelNome);

        campoNome = new JTextField();
        campoNome.setBounds(170, 70, 300, 25);
        add(campoNome);

        JLabel labelLogin = new JLabel("Login:");
        labelLogin.setBounds(30, 110, 130, 25);
        add(labelLogin);

        campoLogin = new JTextField();
        campoLogin.setBounds(170, 110, 300, 25);
        add(campoLogin);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(30, 150, 130, 25);
        add(labelSenha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(170, 150, 300, 25);
        add(campoSenha);

        JLabel labelConfirmarSenha = new JLabel("Confirmar senha:");
        labelConfirmarSenha.setBounds(30, 190, 130, 25);
        add(labelConfirmarSenha);

        campoConfirmarSenha = new JPasswordField();
        campoConfirmarSenha.setBounds(170, 190, 300, 25);
        add(campoConfirmarSenha);

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.setBounds(170, 250, 120, 30);
        add(botaoSalvar);

        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBounds(310, 250, 120, 30);
        add(botaoLimpar);

        botaoSalvar.addActionListener(e -> salvarUsuario());
        botaoLimpar.addActionListener(e -> limparCampos());
    }

    private void salvarUsuario() {
        try {
            String nome = campoNome.getText();
            String login = campoLogin.getText();
            String senha = new String(campoSenha.getPassword());
            String confirmarSenha = new String(campoConfirmarSenha.getPassword());

            if (!senha.equals(confirmarSenha)) {
                JOptionPane.showMessageDialog(
                        this,
                        "As senhas não conferem.",
                        "Validação",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            Usuario usuario = usuarioController.cadastrarUsuario(
                    nome,
                    login,
                    senha
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Usuário cadastrado com sucesso!\nUsuário: " + usuario.getNome()
            );

            limparCampos();

        } catch (RuntimeException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    erro.getMessage(),
                    "Erro ao cadastrar usuário",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void limparCampos() {
        campoNome.setText("");
        campoLogin.setText("");
        campoSenha.setText("");
        campoConfirmarSenha.setText("");
    }
}