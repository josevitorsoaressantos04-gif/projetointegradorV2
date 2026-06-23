package sistema.service;

import sistema.model.Usuario;
import sistema.DAO.UsuarioDAO;
import sistema.DAO.RecuperacaoSenhaDAO;

import java.security.SecureRandom;
import java.time.LocalDateTime;

public class UsuarioService {
    private final UsuarioDAO usd = new UsuarioDAO();
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public Usuario cadastrarUsuario(String nome, String login, String senha) {
        if( nome == null || nome.trim().isEmpty()){
            throw new RuntimeException("Informe um nome");
        }

        if( nome.length()> 200){
            throw new RuntimeException("O limite de 200 caracteres foi atingido");
        }

        if (login == null || login.trim().isEmpty()) {
            throw new RuntimeException("Informe a credencial de login.");
        }

        if (senha == null || senha.trim().isEmpty()) {
            throw new RuntimeException("Informe a senha.");
        }

        Usuario usuario = new Usuario();

        usuario.setNome(nome.trim());
        usuario.setLogin(login.trim());
        usuario.setSenha(senha);
        usuario.setDataCadastro(LocalDateTime.now());

        /*
         * Valor temporário apenas para passar no NOT NULL e UNIQUE.
         * Depois do INSERT, será substituído pelo código real.
         */
        usuario.setRecuperacaoSenha(gerarCodigoAleatorio());

        usd.cadastrar(usuario);

        String codigoRecuperacao = gerarCodigoUnico();

        usd.atualizarCodigoRecuperacao(
                usuario.getLogin(),
                codigoRecuperacao
        );

        usuario.setRecuperacaoSenha(codigoRecuperacao);

        return usuario;
    }

    private String gerarCodigoUnico() {
        String codigo;

        do {
            codigo = gerarCodigoAleatorio();
        } while (usd.codigoRecuperacaoExiste(codigo));

        return codigo;
    }

    private String gerarCodigoAleatorio() {

        SecureRandom random = new SecureRandom();
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int posicao = random.nextInt(CARACTERES.length());
            codigo.append(CARACTERES.charAt(posicao));


        }

        return codigo.toString();
    }

    public void excluirUsuario(String login) {

        if (login == null || login.trim().isEmpty()) {
            throw new RuntimeException("Informe o nome do usuário para exclusão.");
        }

        usd.excluir(login.trim());
    }

}