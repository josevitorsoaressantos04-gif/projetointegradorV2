package sistema.service;

import sistema.model.Usuario;
import sistema.DAO.UsuarioDAO;

import java.time.LocalDateTime;

public class UsuarioService {
    private UsuarioDAO usd = new UsuarioDAO();

    public Usuario validar(String nome, String login, String senha) {

        if (nome.trim().isEmpty()) {
            throw new RuntimeException("Informe um nome");
        } else if (nome.length() > 50) {
            throw new RuntimeException("A quantidade de caracteres é maior que limite máximo");
        }
        if (login.trim().isEmpty()) {
            throw new RuntimeException("Informe a credencial de login.");
        }

        if (senha.trim().isEmpty()) {
            throw new RuntimeException("Informe a senha.");
        }

        if (nome.isEmpty() || login.isEmpty() || senha.isEmpty()) {
            throw new RuntimeException("Erro ao cadastrar usuario: Verifique os dados preenchidos");
        }
        Usuario usuario = new Usuario();

        usuario.setNome(nome);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setDataCadastro(LocalDateTime.now());

        usd.cadastrar(usuario);

        return usuario;
    }

    public class ExcluirUsuarioService {

        private UsuarioDAO usuarioDAO = new UsuarioDAO();

        public void excluirUsuario(String nome) {

            if (nome == null || nome.trim().isEmpty()) {
                throw new RuntimeException("Informe o nome do usuário para exclusão.");
            }

            usuarioDAO.excluir(nome.trim());
        }
    }
}