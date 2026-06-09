package sistema.view.cadastros;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CadastroPessoaView extends JFrame {

    private JTextField campoNome;
    private JTextField campoCpfCnpj;
    private JTextField campoTelefone;
    private JTextField campoEmail;

    public CadastroPessoaView() {
        setTitle("Cadastro de Pessoas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("Cadastro de Pessoas");
        titulo.setBounds(30, 20, 300, 25);
        add(titulo);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(30, 70, 100, 25);
        add(labelNome);

        campoNome = new JTextField();
        campoNome.setBounds(140, 70, 300, 25);
        add(campoNome);

        JLabel labelCpfCnpj = new JLabel("CPF/CNPJ:");
        labelCpfCnpj.setBounds(30, 110, 100, 25);
        add(labelCpfCnpj);

        campoCpfCnpj = new JTextField();
        campoCpfCnpj.setBounds(140, 110, 300, 25);
        add(campoCpfCnpj);

        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setBounds(30, 150, 100, 25);
        add(labelTelefone);

        campoTelefone = new JTextField();
        campoTelefone.setBounds(140, 150, 300, 25);
        add(campoTelefone);

        JLabel labelEmail = new JLabel("E-mail:");
        labelEmail.setBounds(30, 190, 100, 25);
        add(labelEmail);

        campoEmail = new JTextField();
        campoEmail.setBounds(140, 190, 300, 25);
        add(campoEmail);

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.setBounds(140, 250, 120, 30);
        add(botaoSalvar);

        JButton botaoExcluir = new JButton("Excluir");
        botaoExcluir.setBounds(280, 250, 120, 30);
        add(botaoExcluir);
    }
}