package sistema.view.listagem;

import sistema.DAO.ClienteDAO;
import sistema.model.Cliente;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListagemPessoaView extends JFrame {

    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private ClienteDAO clienteDAO;

    public ListagemPessoaView() {
        clienteDAO = new ClienteDAO();

        setTitle("Listagem de Pessoas");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        montarTabela();
        carregarDados();
    }

    private void montarTabela() {
        modeloTabela = new DefaultTableModel();

        modeloTabela.addColumn("ID");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("CPF/CNPJ");
        modeloTabela.addColumn("Telefone");
        modeloTabela.addColumn("E-mail");

        tabela = new JTable(modeloTabela);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 20, 740, 420);
        add(scroll);
    }

    private void carregarDados() {
        modeloTabela.setRowCount(0);

        for (Cliente cliente : clienteDAO.listar()) {
            modeloTabela.addRow(new Object[]{
                    cliente.getIdCliente(),
                    cliente.getNome(),
                    cliente.getCpfCnpj(),
                    cliente.getTelefone(),
                    cliente.getEmail()
            });
        }
    }
}
