package sistema.view.listagem;

import sistema.DAO.UsuarioDAO;
import sistema.model.Usuario;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListagemUsuarioView extends JFrame {

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    private UsuarioDAO usuarioDAO;

    public ListagemUsuarioView() {
        usuarioDAO = new UsuarioDAO();

        configurarTela();
        montarTabela();
        carregarUsuarios();
    }

    private void configurarTela() {
        setTitle("Listagem de Usuarios");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void montarTabela() {
        modeloTabela = new DefaultTableModel();

        modeloTabela.addColumn("ID");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Login");
        modeloTabela.addColumn("Data Cadastro");

        tabela = new JTable(modeloTabela);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 20, 740, 420);
        add(scroll);
    }

    private void carregarUsuarios() {
        modeloTabela.setRowCount(0);

        for (Usuario usuario : usuarioDAO.listar()) {
            modeloTabela.addRow(new Object[]{
                    usuario.getIdUsuario(),
                    usuario.getNome(),
                    usuario.getLogin(),
                    usuario.getDataCadastro()
            });
        }
    }
}