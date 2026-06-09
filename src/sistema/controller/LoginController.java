package sistema.controller;

import sistema.model.Usuario;
import sistema.service.LoginService;

public class LoginController {

    private LoginService loginService = new LoginService();

    public Usuario realizarLogin(String login, String senha) {
        return loginService.autenticar(login, senha);
    }
}