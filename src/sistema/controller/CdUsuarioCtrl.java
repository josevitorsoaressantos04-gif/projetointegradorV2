package sistema.controller;

import sistema.model.Usuario;
import sistema.service.UsuarioService;

public class CdUsuarioCtrl {

    private UsuarioService cadastroUsuarioService = new UsuarioService();

    public Usuario cadastrarUsuario(String nome,String login, String senha) {
        return cadastroUsuarioService.validar(nome,login, senha);
    }
}

