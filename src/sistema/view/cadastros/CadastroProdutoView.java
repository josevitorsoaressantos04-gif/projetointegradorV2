package sistema.view.cadastros;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CadastroProdutoView extends JFrame {

    private JTextField campoNome;
    private JTextField campoDescricao;
    private JTextField campoValorCusto;
    private JTextField campoValorVenda;
    private JTextField campoEstoque;

    public CadastroProdutoView() {
        setTitle("Cadastro de Produtos");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("Cadastro de Produtos");
        titulo.setBounds(30, 20, 300, 25);
        add(titulo);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(30, 70, 120, 25);
        add(labelNome);

        campoNome = new JTextField();
        campoNome.setBounds(150, 70, 300, 25);
        add(campoNome);

        JLabel labelDescricao = new JLabel("Descricao:");
        labelDescricao.setBounds(30, 110, 120, 25);
        add(labelDescricao);

        campoDescricao = new JTextField();
        campoDescricao.setBounds(150, 110, 300, 25);
        add(campoDescricao);

        JLabel labelValorCusto = new JLabel("Valor Custo:");
        labelValorCusto.setBounds(30, 150, 120, 25);
        add(labelValorCusto);

        campoValorCusto = new JTextField();
        campoValorCusto.setBounds(150, 150, 300, 25);
        add(campoValorCusto);

        JLabel labelValorVenda = new JLabel("Valor Venda:");
        labelValorVenda.setBounds(30, 190, 120, 25);
        add(labelValorVenda);

        campoValorVenda = new JTextField();
        campoValorVenda.setBounds(150, 190, 300, 25);
        add(campoValorVenda);

        JLabel labelEstoque = new JLabel("Estoque:");
        labelEstoque.setBounds(30, 230, 120, 25);
        add(labelEstoque);

        campoEstoque = new JTextField();
        campoEstoque.setBounds(150, 230, 300, 25);
        add(campoEstoque);

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.setBounds(150, 290, 120, 30);
        add(botaoSalvar);

        JButton botaoExcluir = new JButton("Excluir");
        botaoExcluir.setBounds(290, 290, 120, 30);
        add(botaoExcluir);
    }
}