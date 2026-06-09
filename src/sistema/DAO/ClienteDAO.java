package sistema.DAO;
import sistema.conexao.ConexaoBanco;
import sistema.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//ajuste rapido
//develop
public class ClienteDAO {

    public void cadastrarCliente(Cliente cliente) {
        String sql = """
                INSERT INTO cliente (
                    nome,
                    cpf_cnpj,
                    telefone,
                    email
                ) VALUES (?, ?, ?, ?)
                """;

        try (
                Connection conexao = ConexaoBanco.conectar()
        ) {
            assert conexao != null;
            try (PreparedStatement stmt =  conexao.prepareStatement(sql)
            ) {

                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getCpfCnpj());
                stmt.setString(3, cliente.getTelefone());
                stmt.setString(4, cliente.getEmail());

                stmt.executeUpdate();

                System.out.println("Cliente cadastrado com sucesso!");

            }
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao cadastrar cliente: " + erro.getMessage());
        }
    }

    public void atualizarCliente( Cliente cliente) {

        String sql = """
                UPDATE cliente
                SET nome = ?,
                    cpf_cnpj = ?,
                    telefone = ?,
                    email = ?
                WHERE id_cliente = ?
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpfCnpj());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setInt(5, cliente.getIdCliente());

            stmt.executeUpdate();

            System.out.println("Cliente atualizado com sucesso!");

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao atualizar cliente: " + erro.getMessage());
        }
    }

    public void excluirCliente(int idCliente) {

        String sql = """
                DELETE FROM cliente
                WHERE id_cliente = ?
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {

            stmt.setInt(1, idCliente);
            stmt.executeUpdate();

            System.out.println("Cliente excluído com sucesso!");

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao excluir cliente: " + erro.getMessage());
        }
    }
}