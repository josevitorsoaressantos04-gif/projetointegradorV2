package sistema.controller;

import sistema.model.Usuario;
import sistema.service.UsuarioService;

public class UsuarioController {

    private UsuarioService usuarioService = new UsuarioService();

    public Usuario cadastrarUsuario(String nome, String login, String senha) {
        return usuarioService.cadastrarUsuario(nome, login, senha);
    }

    public void excluirUsuario(String login) {
        usuarioService.excluirUsuario(login);
    }
}