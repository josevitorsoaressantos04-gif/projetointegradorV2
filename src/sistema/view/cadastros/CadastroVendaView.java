package sistema.view.cadastros;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CadastroVendaView extends JFrame {

    private JTextField campoIdPessoa;
    private JTextField campoIdProduto;
    private JTextField campoQuantidade;
    private JTextField campoValorTotal;

    public CadastroVendaView() {
        setTitle("Cadastro de Vendas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("Cadastro de Vendas");
        titulo.setBounds(30, 20, 300, 25);
        add(titulo);

        JLabel labelPessoa = new JLabel("ID Pessoa:");
        labelPessoa.setBounds(30, 70, 120, 25);
        add(labelPessoa);

        campoIdPessoa = new JTextField();
        campoIdPessoa.setBounds(150, 70, 300, 25);
        add(campoIdPessoa);

        JLabel labelProduto = new JLabel("ID Produto:");
        labelProduto.setBounds(30, 110, 120, 25);
        add(labelProduto);

        campoIdProduto = new JTextField();
        campoIdProduto.setBounds(150, 110, 300, 25);
        add(campoIdProduto);

        JLabel labelQuantidade = new JLabel("Quantidade:");
        labelQuantidade.setBounds(30, 150, 120, 25);
        add(labelQuantidade);

        campoQuantidade = new JTextField();
        campoQuantidade.setBounds(150, 150, 300, 25);
        add(campoQuantidade);

        JLabel labelValorTotal = new JLabel("Valor Total:");
        labelValorTotal.setBounds(30, 190, 120, 25);
        add(labelValorTotal);

        campoValorTotal = new JTextField();
        campoValorTotal.setBounds(150, 190, 300, 25);
        add(campoValorTotal);

        JButton botaoSalvar = new JButton("Salvar Venda");
        botaoSalvar.setBounds(150, 250, 150, 30);
        add(botaoSalvar);
    }
}