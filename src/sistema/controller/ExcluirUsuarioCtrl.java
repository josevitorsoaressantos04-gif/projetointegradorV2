package sistema.controller;

import sistema.service.ExcluirUsuarioService;

public class ExcluirUsuarioCtrl {

    private ExcluirUsuarioService excluirUsuarioService = new ExcluirUsuarioService();

    public void excluirUsuario(String nome) {
        excluirUsuarioService.excluirUsuario(nome);
    }
}