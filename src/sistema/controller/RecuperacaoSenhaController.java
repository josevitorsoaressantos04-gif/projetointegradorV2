package sistema.controller;

import sistema.service.RecuperacaoSenhaService;

public class RecuperacaoSenhaController {

    private RecuperacaoSenhaService recuperacaoSenhaService = new RecuperacaoSenhaService();

    public String gerarCodigoRecuperacao(String login) {
        return recuperacaoSenhaService.gerarCodigoRecuperacao(login);
    }

    public void alterarSenhaComCodigo(
            String codigoRecuperacao,
            String novaSenha,
            String confirmarSenha
    ) {
        recuperacaoSenhaService.alterarSenhaComCodigo(
                codigoRecuperacao,
                novaSenha,
                confirmarSenha
        );
    }
}