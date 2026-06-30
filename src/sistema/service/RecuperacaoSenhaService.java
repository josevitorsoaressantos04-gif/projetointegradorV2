package sistema.service;

import sistema.DAO.RecuperacaoSenhaDAO;

import java.security.SecureRandom;

public class RecuperacaoSenhaService {

    private RecuperacaoSenhaDAO recuperacaoSenhaDAO = new RecuperacaoSenhaDAO();

    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int TAMANHO_CODIGO = 10;

    public String gerarCodigoRecuperacao(String login) {

        if (login == null || login.trim().isEmpty()) {
            throw new RuntimeException("Informe o login do usuário.");
        }

        String loginTratado = login.trim();

        if (recuperacaoSenhaDAO.loginExiste(login.trim())) {
            String codigoReal;

            do {
                codigoReal = gerarCodigoAleatorio();
            } while (recuperacaoSenhaDAO.codigoExiste(codigoReal));
            recuperacaoSenhaDAO.salvarCodigoRecuperacao(loginTratado, codigoReal);

            return codigoReal;
        }
        return gerarCodigoAleatorio();

        /*String codigo;

        do {
            codigo = gerarCodigoAleatorio();
        } while (recuperacaoSenhaDAO.codigoExiste(codigo));

        recuperacaoSenhaDAO.salvarCodigoRecuperacao(login.trim(), codigo);

        return codigoExibido;*/
    }

    public void alterarSenhaComCodigo(
            String codigoRecuperacao,
            String novaSenha,
            String confirmarSenha
    ) {
        if (codigoRecuperacao == null || codigoRecuperacao.trim().isEmpty()) {
            throw new RuntimeException("Informe o código de recuperação.");
        }

        if (codigoRecuperacao.trim().length() > 10) {
            throw new RuntimeException("O código de recuperação deve ter no máximo 10 caracteres.");
        }

        if (novaSenha == null || novaSenha.trim().isEmpty()) {
            throw new RuntimeException("Informe a nova senha.");
        }

        if (confirmarSenha == null || confirmarSenha.trim().isEmpty()) {
            throw new RuntimeException("Confirme a nova senha.");
        }

        if (!novaSenha.equals(confirmarSenha)) {
            throw new RuntimeException("As senhas não conferem.");
        }

        if (!recuperacaoSenhaDAO.validarCodigo(codigoRecuperacao.trim())) {
            throw new RuntimeException("Código de recuperação inválido.");
        }

        String novoCodigo;

        do {
            novoCodigo = gerarCodigoAleatorio();
        } while (recuperacaoSenhaDAO.codigoExiste(novoCodigo));

        recuperacaoSenhaDAO.alterarSenhaPorCodigo(
                codigoRecuperacao.trim(),
                novaSenha,
                novoCodigo
        );
    }

    private String gerarCodigoAleatorio() {

        SecureRandom random = new SecureRandom();
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < TAMANHO_CODIGO; i++) {
            int posicao = random.nextInt(CARACTERES.length());
            codigo.append(CARACTERES.charAt(posicao));
        }

        return codigo.toString();
    }
}