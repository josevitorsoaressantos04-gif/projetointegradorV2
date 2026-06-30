package sistema.DAO;

import sistema.conexao.ConexaoBanco;
import sistema.model.TipoMovimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoMovimentoDAO {

    public void inserir(TipoMovimento tipoMovimento) {
        String sql = """
                INSERT INTO tipo_movimento (
                    descricao
                ) VALUES (?)
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, tipoMovimento.getDescricao());
            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao inserir tipo de movimento: " + erro.getMessage());
        }
    }

    public List<TipoMovimento> listar() {
        String sql = """
                SELECT id, descricao
                FROM tipo_movimento
                ORDER BY id
                """;

        List<TipoMovimento> tipos = new ArrayList<>();

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                TipoMovimento tipo = new TipoMovimento();

                tipo.setIdMovimento(rs.getInt("id_movimento"));
                tipo.setDescricao(rs.getString("descricao"));

                tipos.add(tipo);
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao listar tipos de movimento: " + erro.getMessage());
        }

        return tipos;
    }
}