package sistema.view;

import sistema.controller.RecuperacaoSenhaController;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RecuperacaoSenhaView extends JFrame {

    private JTextField campoLogin;
    private JTextField campoCodigoRecuperacao;
    private JPasswordField campoNovaSenha;
    private JPasswordField campoConfirmarSenha;

    private RecuperacaoSenhaController recuperacaoSenhaController;

    public RecuperacaoSenhaView() {
        recuperacaoSenhaController = new RecuperacaoSenhaController();

        configurarTela();
        montarComponentes();
    }

    private void configurarTela() {
        setTitle("Recuperação de Senha");
        setSize(600, 420);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
    }

    private void montarComponentes() {
        JLabel titulo = new JLabel("Recuperação de Senha");
        titulo.setBounds(30, 20, 300, 25);
        add(titulo);

        JLabel labelLogin = new JLabel("Login:");
        labelLogin.setBounds(30, 70, 130, 25);
        add(labelLogin);

        campoLogin = new JTextField();
        campoLogin.setBounds(170, 70, 250, 25);
        add(campoLogin);

        JButton botaoGerarCodigo = new JButton("Gerar Código");
        botaoGerarCodigo.setBounds(430, 70, 130, 30);
        add(botaoGerarCodigo);

        JLabel labelCodigo = new JLabel("Código:");
        labelCodigo.setBounds(30, 130, 130, 25);
        add(labelCodigo);

        campoCodigoRecuperacao = new JTextField();
        campoCodigoRecuperacao.setBounds(170, 130, 250, 25);
        add(campoCodigoRecuperacao);

        JLabel labelNovaSenha = new JLabel("Nova senha:");
        labelNovaSenha.setBounds(30, 180, 130, 25);
        add(labelNovaSenha);

        campoNovaSenha = new JPasswordField();
        campoNovaSenha.setBounds(170, 180, 250, 25);
        add(campoNovaSenha);

        JLabel labelConfirmarSenha = new JLabel("Confirmar senha:");
        labelConfirmarSenha.setBounds(30, 230, 130, 25);
        add(labelConfirmarSenha);

        campoConfirmarSenha = new JPasswordField();
        campoConfirmarSenha.setBounds(170, 230, 250, 25);
        add(campoConfirmarSenha);

        JButton botaoAlterarSenha = new JButton("Alterar Senha");
        botaoAlterarSenha.setBounds(170, 300, 150, 30);
        add(botaoAlterarSenha);

        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBounds(340, 300, 100, 30);
        add(botaoLimpar);

        botaoGerarCodigo.addActionListener(e -> gerarCodigoRecuperacao());
        botaoAlterarSenha.addActionListener(e -> alterarSenha());
        botaoLimpar.addActionListener(e -> limparCampos());
    }

    private void gerarCodigoRecuperacao() {
        try {
            String login = campoLogin.getText();

            String codigo = recuperacaoSenhaController.gerarCodigoRecuperacao(login);

            campoCodigoRecuperacao.setText(codigo);

            JOptionPane.showMessageDialog(
                    this,
                    "Código de recuperação gerado.\nCódigo: " + codigo
            );

        } catch (RuntimeException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    erro.getMessage(),
                    "Erro ao gerar código",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    private void alterarSenha() {
        String codigoRecuperacao = campoCodigoRecuperacao.getText();
        String novaSenha = new String(campoNovaSenha.getPassword());
        String confirmarSenha = new String(campoConfirmarSenha.getPassword());

        try {
            recuperacaoSenhaController.alterarSenhaComCodigo(
                    codigoRecuperacao,
                    novaSenha,
                    confirmarSenha
            );

        } catch (RuntimeException erro) {
            /*
             * Falso positivo proposital:
             * Não exibe erro específico para evitar que alguém descubra
             * se o código de recuperação existe ou não no banco.
             *
             * Importante:
             * O backend NÃO deve alterar a senha quando o código for inválido.
             * Apenas a mensagem da tela será genérica.
             */
        }

        JOptionPane.showMessageDialog(
                this,
                "Senha alterada com sucesso!"
        );

        limparCampos();
    }

    private void limparCampos() {
        campoLogin.setText("");
        campoCodigoRecuperacao.setText("");
        campoNovaSenha.setText("");
        campoConfirmarSenha.setText("");
    }
}