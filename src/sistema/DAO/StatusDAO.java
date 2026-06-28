package sistema.DAO;

import sistema.conexao.ConexaoBanco;
import sistema.model.Status;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO {

    public void inserir(Status status) {
        String sql = """
                INSERT INTO `status` (
                    id,
                    descricao
                ) VALUES (?, ?)
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setInt(1, status.getIdStatus());
            stmt.setString(2, status.getDescricao());

            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao inserir status: " + erro.getMessage());
        }
    }

    public List<Status> listar() {
        String sql = """
                SELECT id, descricao
                FROM `status`
                ORDER BY id
                """;

        List<Status> statusList = new ArrayList<>();

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Status status = new Status();

                status.setIdStatus(rs.getInt("id"));
                status.setDescricao(rs.getString("descricao"));

                statusList.add(status);
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao listar status: " + erro.getMessage());
        }

        return statusList;
    }
}