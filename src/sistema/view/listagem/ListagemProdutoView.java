package sistema.view.listagem;

import sistema.DAO.ProdutoDAO;
import sistema.model.Produto;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListagemProdutoView extends JFrame {

    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private ProdutoDAO produtoDAO;

    public ListagemProdutoView() {
        produtoDAO = new ProdutoDAO();

        setTitle("Listagem de Produtos");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        montarTabela();
        carregarDados();
    }

    private void montarTabela() {
        modeloTabela = new DefaultTableModel();

        modeloTabela.addColumn("ID");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Descricao");
        modeloTabela.addColumn("Valor Custo");
        modeloTabela.addColumn("Valor Venda");
        modeloTabela.addColumn("Estoque");

        tabela = new JTable(modeloTabela);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 20, 790, 420);
        add(scroll);
    }

    private void carregarDados() {
        modeloTabela.setRowCount(0);

        for (Produto produto : produtoDAO.listar()) {
            modeloTabela.addRow(new Object[]{
                    produto.getIdProduto(),
                    produto.getNome(),
                    produto.getDescricao(),
                    produto.getValorCusto(),
                    produto.getValorVenda(),
                    produto.getEstoque()
            });
        }
    }
}