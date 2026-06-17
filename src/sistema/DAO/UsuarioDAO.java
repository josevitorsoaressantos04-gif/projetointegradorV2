package sistema.DAO;

import sistema.conexao.ConexaoBanco;
import sistema.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void cadastrar(Usuario usuario) {

        String sql = """
                INSERT INTO usuario (
                    nome,
                    login,
                    senha,
                    data_cadastro
                ) VALUES (
                    ?, ?, ?, NOW()
                )
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());

            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao cadastrar usuario: " + erro.getMessage());
        }
    }

    public List<Usuario> listar() {

        String sql = """
                SELECT
                    id_usuario,
                    nome,
                    login,
                    senha,
                    data_cadastro
                FROM usuario
                ORDER BY nome
                """;

        List<Usuario> usuarios = new ArrayList<>();

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet resultado = stmt.executeQuery()
        ) {
            while (resultado.next()) {

                Usuario usuario = new Usuario();

                usuario.setIdUsuario(resultado.getInt("id_usuario"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setLogin(resultado.getString("login"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setDataCadastro(resultado.getTimestamp("data_cadastro").toLocalDateTime());

                usuarios.add(usuario);
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao listar usuarios: " + erro.getMessage());
        }

        return usuarios;
    }

    public Usuario buscarPorLoginESenha(String login, String senha) {

        String sql = """
                SELECT
                    id_usuario,
                    nome,
                    login,
                    senha,
                    recuperacaoSenha,
                    data_cadastro
                FROM usuario
                WHERE login = ?
                AND senha = ?
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    Usuario usuario = new Usuario();

                    usuario.setIdUsuario(resultado.getInt("id_usuario"));
                    usuario.setNome(resultado.getString("nome"));
                    usuario.setLogin(resultado.getString("login"));
                    usuario.setSenha(resultado.getString("senha"));
                    usuario.setDataCadastro(resultado.getTimestamp("data_cadastro").toLocalDateTime());
                    stmt.setString(4, usuario.getRecuperacaoSenha());

                    return usuario;
                }
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao buscar usuario: " + erro.getMessage());
        }

        return null;
    }

    public void alterarSenha(int idUsuario, String novaSenha) {

        String sql = """
                UPDATE usuario
                SET senha = ?
                WHERE id_usuario = ?
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, novaSenha);
            stmt.setInt(2, idUsuario);

            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao alterar senha: " + erro.getMessage());
        }
    }

    public void excluir(String nome) {

        String sql = """
                DELETE FROM usuario
                WHERE nome  = ?
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, nome);
            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao excluir usuario: " + erro.getMessage());
        }
    }
    public boolean codigoRecuperacaoExiste(String codigo) {

        String sql = """
            SELECT id_usuario
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
    public void atualizarCodigoRecuperacao(String login, String codigoRecuperacao) {

        String sql = """
            UPDATE usuario
            SET recuperacaoSenha = ?
            WHERE login = ?
            """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, codigoRecuperacao);
            stmt.setString(2, login);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum usuário encontrado para atualizar o código de recuperação.");
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao atualizar código de recuperação: " + erro.getMessage());
        }
    }
}