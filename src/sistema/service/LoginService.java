package sistema.service;

import sistema.DAO.UsuarioDAO;
import sistema.model.Usuario;

public class LoginService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario autenticar(String login, String senha) {

        if (login == null || login.trim().isEmpty()) {
            throw new RuntimeException("Informe o login.");
        }

        if (senha == null || senha.trim().isEmpty()) {
            throw new RuntimeException("Informe a senha.");
        }

        Usuario usuario = usuarioDAO.buscarPorLoginESenha(login, senha);

        if (usuario == null) {
            throw new RuntimeException("Login ou senha inválidos.");
        }

        return usuario;
    }
}