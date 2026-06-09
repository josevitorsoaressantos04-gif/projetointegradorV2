package sistema.DAO;

import sistema.conexao.ConexaoBanco;
import sistema.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario buscarPorLoginESenha(String login, String senha) {

        String sql = """
                SELECT id_usuario, nome, login, senha, data_cadastro
                FROM usuario
                WHERE login = ?
                AND senha = ?
                """;

        try
                (Connection conexao = ConexaoBanco.conectar()) {
            PreparedStatement stmt = conexao.prepareStatement(sql);


            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                Usuario usuario = new Usuario();

                usuario.setIdUsuario(resultado.getInt("id_usuario"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setLogin(resultado.getString("login"));
                usuario.setSenha(resultado.getString("senha"));

                if (resultado.getTimestamp("data_cadastro") != null) {
                    usuario.setDataCadastro(resultado.getTimestamp("data_cadastro").toLocalDateTime());
                }

                return usuario;
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao buscar usuário: " + erro.getMessage());
        }

        return null;
    }

    public void cadastrarUsuario(Usuario usuario) {

        String sql = """
                INSERT INTO usuario (
                    nome,
                    login,
                    senha,
                    data_cadastro
                ) VALUES (?, ?, ?, NOW())
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
            throw new RuntimeException("Erro ao cadastrar usuário: " + erro.getMessage());
        }
    }
}