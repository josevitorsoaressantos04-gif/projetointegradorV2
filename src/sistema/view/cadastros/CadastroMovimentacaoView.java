package sistema.view.cadastros;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CadastroMovimentacaoView extends JFrame {

    private JTextField campoIdProduto;
    private JTextField campoTipo;
    private JTextField campoQuantidade;
    private JTextField campoObservacao;

    public CadastroMovimentacaoView() {
        setTitle("Cadastro de Movimentacoes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("Cadastro de Movimentacoes");
        titulo.setBounds(30, 20, 300, 25);
        add(titulo);

        JLabel labelProduto = new JLabel("ID Produto:");
        labelProduto.setBounds(30, 70, 120, 25);
        add(labelProduto);

        campoIdProduto = new JTextField();
        campoIdProduto.setBounds(150, 70, 300, 25);
        add(campoIdProduto);

        JLabel labelTipo = new JLabel("Tipo:");
        labelTipo.setBounds(30, 110, 120, 25);
        add(labelTipo);

        campoTipo = new JTextField();
        campoTipo.setBounds(150, 110, 300, 25);
        add(campoTipo);

        JLabel labelQuantidade = new JLabel("Quantidade:");
        labelQuantidade.setBounds(30, 150, 120, 25);
        add(labelQuantidade);

        campoQuantidade = new JTextField();
        campoQuantidade.setBounds(150, 150, 300, 25);
        add(campoQuantidade);

        JLabel labelObservacao = new JLabel("Observacao:");
        labelObservacao.setBounds(30, 190, 120, 25);
        add(labelObservacao);

        campoObservacao = new JTextField();
        campoObservacao.setBounds(150, 190, 300, 25);
        add(campoObservacao);

        JButton botaoSalvar = new JButton("Salvar Movimentacao");
        botaoSalvar.setBounds(150, 250, 190, 30);
        add(botaoSalvar);
    }
}