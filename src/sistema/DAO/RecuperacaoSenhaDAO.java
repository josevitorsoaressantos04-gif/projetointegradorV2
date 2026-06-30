package sistema.DAO;

import sistema.conexao.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecuperacaoSenhaDAO {

    public boolean loginExiste(String login) {

        String sql = """
                SELECT id
                FROM usuario
                WHERE login = ?
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, login);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao verificar login: " + erro.getMessage());
        }
    }

    public boolean codigoExiste(String codigo) {

        String sql = """
                SELECT id
                FROM usuario
                WHERE recuperacaoSenha = ?
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao verificar código de recuperação: " + erro.getMessage());
        }
    }

    public void salvarCodigoRecuperacao(String login, String codigo) {

        String sql = """
                UPDATE usuario
                SET recuperacaoSenha = ?
                WHERE login = ?
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, codigo);
            stmt.setString(2, login);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum usuário encontrado com este login.");
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao salvar código de recuperação: " + erro.getMessage());
        }
    }

    public boolean validarCodigo(String codigo) {

        String sql = """
                SELECT id
                FROM usuario
                WHERE recuperacaoSenha = ?
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao validar código de recuperação: " + erro.getMessage());
        }
    }

    public void alterarSenhaPorCodigo(String codigoAtual, String novaSenha, String novoCodigoRecuperacao) {

        String sql = """
                UPDATE usuario
                SET senha = ?,
                    recuperacaoSenha = ?
                WHERE recuperacaoSenha = ?
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, novaSenha);
            stmt.setString(2, novoCodigoRecuperacao);
            stmt.setString(3, codigoAtual);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Código de recuperação inválido.");
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao alterar senha: " + erro.getMessage());
        }
    }
}